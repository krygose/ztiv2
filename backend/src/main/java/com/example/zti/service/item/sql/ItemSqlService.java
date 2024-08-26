package com.example.zti.service.item.sql;

import com.example.zti.common.problem.DuplicateKeyErrorProblem;
import com.example.zti.common.problem.InternalServerErrorProblem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.zti.common.resource.ResourceManager.readSqlQuery;


@Service
public class ItemSqlService {
    private static final Logger log = LoggerFactory.getLogger(ItemSqlService.class);

    private static final String SELECT_ITEMS = readSqlQuery("sql/item/select_items.sql");
    private static final String INSERT_INTO_ITEMS = readSqlQuery("sql/item/insert_into_items.sql");
    private static final String MODIFY_ITEM_QUANTITY = readSqlQuery("sql/item/modify_item_quantity.sql");
    ;
    private static final String SELECT_ITEMS_EXIST = readSqlQuery("sql/item/select_items_exist.sql");
    ;

    private final NamedParameterJdbcOperations jdbcOperations;

    ItemSqlService(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;

    }

    public List<ItemSqlRow> getItems() {
        try {
            return jdbcOperations.query(SELECT_ITEMS, DataClassRowMapper.newInstance(ItemSqlRow.class));
        } catch (DataAccessException e) {
            log.error(
                    "Unable to retrieve tracks due to an unexpected error message={}", e.getMessage(), e);
            throw new InternalServerErrorProblem();
        }
    }

    public void addItem(
            String itemId,
            String name,
            Double price,
            Integer quantity) {
        MapSqlParameterSource parameterSource =
                insertItemParameterSource(
                        new MapSqlParameterSource(),
                        itemId,
                        name,
                        price,
                        quantity);

        try {
            jdbcOperations.update(INSERT_INTO_ITEMS, parameterSource);
        } catch (DuplicateKeyErrorProblem e) {
            log.error("Unable to add track due to duplicate key error message={}", e.getMessage(), e);
            throw new InternalServerErrorProblem();
        } catch (DataAccessException e) {
            log.error("Unable to add track due to an unexpected error message={}", e.getMessage(), e);
            throw new InternalServerErrorProblem();
        }
    }


    private MapSqlParameterSource insertItemParameterSource(
            MapSqlParameterSource parameterSource,
            String itemId,
            String name,
            Double price,
            Integer quantity) {
        parameterSource.addValue("id", itemId);
        parameterSource.addValue("name", name);
        parameterSource.addValue("price", price);
        parameterSource.addValue("quantity", quantity);

        return parameterSource;
    }

    public void modifyItem(String id, Integer quantity) {
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("id", id);
        parameters.addValue("quantity", quantity);
        try {
            jdbcOperations.update(MODIFY_ITEM_QUANTITY, parameters);
        } catch (DataAccessException e) {
            log.error(
                    "Unable to retrieve tracks due to an unexpected error message={}", e.getMessage(), e);
            throw new InternalServerErrorProblem();
        }
    }

    public List<ItemSqlRow> getItemsExist() {
        try {
            return jdbcOperations.query(SELECT_ITEMS_EXIST, DataClassRowMapper.newInstance(ItemSqlRow.class));
        } catch (DataAccessException e) {
            log.error(
                    "Unable to retrieve tracks due to an unexpected error message={}", e.getMessage(), e);
            throw new InternalServerErrorProblem();
        }
    }
}
