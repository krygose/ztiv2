package com.example.zti.service.order.sql;

import com.example.zti.common.problem.InternalServerErrorProblem;
import com.example.zti.service.order.OrdersMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.zti.common.resource.ResourceManager.readSqlQuery;
import static java.sql.JDBCType.VARCHAR;

@Service
public class OrdersSqlService {
    private static final Logger log = LoggerFactory.getLogger(OrderSqlService.class);


    private static final String SELECT_ORDERS = readSqlQuery("sql/order/select_orders.sql");

    private static final String SELECT_ORDERS_BY_ID = readSqlQuery("sql/order/select_orders_by_id.sql");
    private static final String MODIFY_ORDER_STATE = readSqlQuery("sql/order/modify_order_state.sql");
    ;

    private final OrdersMapper ordersMapper;

    private final NamedParameterJdbcOperations jdbcOperations;


    OrdersSqlService(NamedParameterJdbcOperations jdbcOperations, ObjectMapper objectMapper) {
        this.jdbcOperations = jdbcOperations;
        this.ordersMapper = new OrdersMapper(objectMapper);
    }

    public List<OrdersSqlRow> getOrders() {
        try {
            return jdbcOperations.query(SELECT_ORDERS, DataClassRowMapper.newInstance(OrdersSqlRow.class));
        } catch (DataAccessException e) {
            log.error(
                    "Unable to retrieve tracks due to an unexpected error message={}", e.getMessage(), e);
            throw new InternalServerErrorProblem();
        }
    }

    public Optional<OrdersSqlRow> getOrdersById(String ordersId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("id", ordersId);
        try {
            return
                    jdbcOperations.query(SELECT_ORDERS_BY_ID, parameters, DataClassRowMapper.newInstance(OrdersSqlRow.class))
                            .stream().findFirst();
        } catch (DataAccessException e) {
            log.error(
                    "Unable to retrieve tracks due to an unexpected error message={}", e.getMessage(), e);
            throw new InternalServerErrorProblem();
        }
    }

    public void modifyState(String id) {

        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("id", id, VARCHAR.getVendorTypeNumber());
        try {
            jdbcOperations.update(MODIFY_ORDER_STATE, parameters);
        } catch (DataAccessException e) {
            log.error(
                    "Unable to retrieve tracks due to an unexpected error message={}", e.getMessage(), e);
            throw new InternalServerErrorProblem();
        }
    }
}
