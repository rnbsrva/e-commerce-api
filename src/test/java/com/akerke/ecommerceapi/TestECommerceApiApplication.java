package com.akerke.ecommerceapi;

import org.springframework.boot.SpringApplication;

public class TestECommerceApiApplication {

    public static void main(String[] args) {
        SpringApplication.from(ECommerceApiApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
