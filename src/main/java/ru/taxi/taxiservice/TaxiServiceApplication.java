package ru.taxi.taxiservice;

import lombok.extern.slf4j.Slf4j;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import ru.taxi.taxiservice.config.ApplicationConfiguration;

@SpringBootApplication
@Import(ApplicationConfiguration.class)
@MapperScan("ru.taxi.taxiservice.mapper")
@Slf4j
public class TaxiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaxiServiceApplication.class, args);
    }

}
