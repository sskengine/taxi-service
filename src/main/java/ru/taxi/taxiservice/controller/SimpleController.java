package ru.taxi.taxiservice.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.taxi.taxiservice.mapper.TaxiInfoMapper;
import ru.taxi.taxiservice.model.OrderDetails;
import ru.taxi.taxiservice.model.TaxiDriverInfoModel;

@RestController
@Slf4j
public class SimpleController {

    @Autowired
    private TaxiInfoMapper taxiInfoMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @PostMapping("/order-taxi")
    public ResponseEntity<String> receive(@RequestBody OrderDetails orderDetails) {
        log.info("Received message from postman" + orderDetails);

        List<TaxiDriverInfoModel> allDrivers1 = taxiInfoMapper.getAllDrivers();

        String message = null;
        try {
            message = objectMapper.writeValueAsString(orderDetails);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        amqpTemplate.convertAndSend("order", message);

        System.out.println("Count: " + allDrivers1.size());

        return ResponseEntity.ok("Request to place order has been send");
    }

    @PostMapping("/trip-over")
    public ResponseEntity<String> rideFinished(){
        return ResponseEntity.ok("Trip is over, cost is: ... ");

    }
}
