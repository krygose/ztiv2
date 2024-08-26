package com.example.zti.service.listItem.sql;

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
public class ListItemSqlService {

    private static final Logger log = LoggerFactory.getLogger(ListItemSqlService.class);

    private static final String SELECT_LISTITEMS = readSqlQuery("sql/listitem/select_listitems.sql");
    private static final String INSERT_INTO_LISTITEMS = readSqlQuery("sql/listitem/insert_into_listitems.sql");

    private final NamedParameterJdbcOperations jdbcOperations;


    ListItemSqlService(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;

    }

    public List<ListItemSqlRow> getListItems() {
        try {
            return jdbcOperations.query(SELECT_LISTITEMS, DataClassRowMapper.newInstance(ListItemSqlRow.class));
        } catch (DataAccessException e) {
            log.error(
                    "Unable to retrieve tracks due to an unexpected error message={}", e.getMessage(), e);
            throw new InternalServerErrorProblem();
        }
    }

    public void addItem(
            String itemId,
            String personId,
            Integer quantity) {
        MapSqlParameterSource parameterSource =
                insertListItemParameterSource(
                        new MapSqlParameterSource(),
                        itemId,
                        personId,
                        quantity);

        try {
            jdbcOperations.update(INSERT_INTO_LISTITEMS, parameterSource);
        } catch (DuplicateKeyErrorProblem e) {
            log.error("Unable to add track due to duplicate key error message={}", e.getMessage(), e);
            throw new InternalServerErrorProblem();
        } catch (DataAccessException e) {
            log.error("Unable to add track due to an unexpected error message={}", e.getMessage(), e);
            throw new InternalServerErrorProblem();
        }
    }



    private MapSqlParameterSource insertListItemParameterSource(
            MapSqlParameterSource parameterSource,
            String itemId,
            String personId,
            Integer quantity) {
        parameterSource.addValue("id", itemId);
        parameterSource.addValue("personId", personId);
        parameterSource.addValue("quantity", quantity);

        return parameterSource;
    }
}
