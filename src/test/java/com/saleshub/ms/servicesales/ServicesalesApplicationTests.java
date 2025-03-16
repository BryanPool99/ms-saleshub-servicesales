package com.saleshub.ms.servicesales;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ServicesalesApplication.class)
public class ServicesalesApplicationTests {

    @Autowired
    private ServicesalesApplication servicesalesApplication;

    @Test
    void contextLoads() {
        assertThat(servicesalesApplication).isNotNull();
    }
}
