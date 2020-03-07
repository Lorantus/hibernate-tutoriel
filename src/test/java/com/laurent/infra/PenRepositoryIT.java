package com.laurent.infra;

import com.laurent.model.Pen;
import com.laurent.model.Price;
import org.junit.Before;
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
public class PenRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PenRepository penRepository;

    private Price price;

    @Before
    public void setUp() throws Exception {
        price = new Price(new BigDecimal("100"));
        price.setReduce(new BigDecimal("50"));
        price.setTaux(new BigDecimal("10"));
    }

    @Test
    public void testFindPenByName() {
        Pen pen = new Pen();
        pen.setColor("Red");
        pen.setName("Bic");
        pen.setPrice(price);
        entityManager.persist(pen);

        List<Pen> pens = penRepository.findByName("Bic");

        assertThat(pens).hasSize(1)
                .extracting(
                        Pen::getColor,
                        Pen::getName,
                        prod -> prod.getPrice().getPrice(),
                        prod -> prod.getPrice().getReduce(),
                        prod -> prod.getPrice().getTaux(),
                        prod -> prod.getPrice().getTotal())
                .containsOnly(tuple(
                        "Red",
                        "Bic",
                        new BigDecimal("100"),
                        new BigDecimal("50"),
                        new BigDecimal("10"),
                        new BigDecimal("55")));
    }

    @Test
    public void testFindPenByColor() {
        Pen pen = new Pen();
        pen.setColor("Red");
        pen.setName("Bic");
        pen.setPrice(price);
        entityManager.persist(pen);

        List<Pen> pens = penRepository.findByColor("Red");

        assertThat(pens).hasSize(1)
                .extracting(
                        Pen::getColor,
                        Pen::getName,
                        prod -> prod.getPrice().getPrice(),
                        prod -> prod.getPrice().getReduce(),
                        prod -> prod.getPrice().getTaux(),
                        prod -> prod.getPrice().getTotal())
                .containsOnly(tuple(
                        "Red",
                        "Bic",
                        new BigDecimal("100"),
                        new BigDecimal("50"),
                        new BigDecimal("10"),
                        new BigDecimal("55")));
    }
}