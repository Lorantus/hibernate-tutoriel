package com.laurent.infra;

import com.laurent.model.Customer;
import com.laurent.model.Order;
import com.laurent.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Arrays.asList;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = false)
public class OrderRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testFindByName() {
        Customer customer = new Customer("Bratislava", "City");
        customer = entityManager.persist(customer);

        Product product = new Product("Bratislava", new BigDecimal("100"));
        product = entityManager.persist(product);

        Order order = new Order("numero", customer);
        order.getProducts().add(product);
        entityManager.persist(order);

        List<Order> orders = orderRepository.findByNumero("numero");

        assertThat(orders).hasSize(1)
                .first()
                .extracting(Order::getCustomer).isEqualTo(customer);

        assertThat(orders)
                .extracting(Order::getProducts)
                .isEqualTo(asList(product));
    }
}