package com.example.zti.service.order.sql;

import com.example.zti.common.problem.DuplicateKeyErrorProblem;
import com.example.zti.common.problem.InternalServerErrorProblem;
import com.example.zti.service.order.OrderMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.zti.common.resource.ResourceManager.readSqlQuery;

@Service
public class OrderSqlService {
    private static final Logger log = LoggerFactory.getLogger(OrderSqlService.class);


    private static final String SELECT_ITEMS_BY_ORDER_ID = readSqlQuery("sql/order/get_item_by_order_id.sql");

    private static final String SELECT_ITEMS_BY_ORDER_ALL = readSqlQuery("sql/order/get_item_by_order_all.sql");

    private static final String INSERT_INTO_ORDERS = readSqlQuery("sql/order/insert_into_orders.sql");

    private final OrderMapper orderMapper;

    private final NamedParameterJdbcOperations jdbcOperations;


    OrderSqlService(NamedParameterJdbcOperations jdbcOperations, ObjectMapper objectMapper) {
        this.jdbcOperations = jdbcOperations;
        this.orderMapper = new OrderMapper(objectMapper);
    }

    public Optional<OrderSqlRow> getOrderById(String id) {
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("id", id);

        return
            jdbcOperations.query(SELECT_ITEMS_BY_ORDER_ID, parameters, orderMapper)
            .stream().findFirst();
    }



    public void createOrder(
            String id,
            String personId,
            Boolean state) {
        MapSqlParameterSource parameterSource =
                insertOrderParameterSource(
                        new MapSqlParameterSource(),
                        id,
                        personId,
                        state);

        try {
            jdbcOperations.update(INSERT_INTO_ORDERS, parameterSource);
        } catch (DuplicateKeyErrorProblem e) {
            log.error("Unable to add track due to duplicate key error message={}", e.getMessage(), e);
            throw new InternalServerErrorProblem();
        } catch (DataAccessException e) {
            log.error("Unable to add track due to an unexpected error message={}", e.getMessage(), e);
            throw new InternalServerErrorProblem();
        }
    }

    private MapSqlParameterSource insertOrderParameterSource(
            MapSqlParameterSource parameterSource,
            String orderId,
            String personId,
            Boolean state) {
        parameterSource.addValue("id", orderId);
        parameterSource.addValue("personId", personId);
        parameterSource.addValue("state", state);

        return parameterSource;
    }

    public Optional<OrderSqlRow> getOrderAll() {
        return
                jdbcOperations.query(SELECT_ITEMS_BY_ORDER_ALL, orderMapper)
                        .stream().findFirst();
    }


}
