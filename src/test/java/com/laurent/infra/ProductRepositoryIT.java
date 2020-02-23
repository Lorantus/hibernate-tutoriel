package com.laurent.infra;

import com.laurent.model.Price;
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
import static org.assertj.core.api.Assertions.tuple;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = false)
public class ProductRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testFindByName() {
        Price price = new Price(new BigDecimal("100"));
        price.setReduce(new BigDecimal("50"));
        price.setTaux(new BigDecimal("10"));

        entityManager.persist(new Product("Bratislava", price));

        List<Product> customers = productRepository.findByName("Bratislava");

        assertThat(customers).hasSize(1)
                .extracting(Product::getName,
                        prod -> prod.getPrice().getPrice(),
                        prod -> prod.getPrice().getReduce(),
                        prod -> prod.getPrice().getTaux(),
                        prod -> prod.getPrice().getTotal())
                .containsOnly(tuple("Bratislava",
                        new BigDecimal("100"),
                        new BigDecimal("50"),
                        new BigDecimal("10"),
                        new BigDecimal("55")));
    }
}