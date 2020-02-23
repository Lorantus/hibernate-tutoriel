package com.laurent.infra;

import com.laurent.model.Customer;
import com.laurent.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Order.OrderPK> {
    List<Order> findByNumero(String numero);
    List<Order> findByCustomer(Customer customer);
}