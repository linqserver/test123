package com.howtodoinjava.rest.controller;

import com.datastax.driver.core.Session;
import lombok.extern.log4j.Log4j2;
import com.datastax.driver.core.Cluster;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/atlas/test")
@Log4j2
@PropertySource("classpath:application.properties")

public class EmployeeController 
{

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    @Value("${keyspace}")
    private String keyspace;

    @Value("${serverIp}")
    private String serverIp;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @GetMapping(path="/anu", produces = "application/json")
    public void check ()
    {

        logger.info("Key Space Name = " +keyspace);
        logger.info("Server Ip = " +serverIp);
        logger.info("User Name = " +username);
        logger.info("Password = " +password);


        Cluster cluster = Cluster.builder()
          .addContactPoints(serverIp).withCredentials(username, password)
          .build();

        Session session = cluster.connect(keyspace);

        logger.info("End point = " +session);


    }

}
