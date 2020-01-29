package com.example.async.deadlock.demo.service;

import com.example.async.deadlock.demo.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AService {
    private Logger logger = LoggerFactory.getLogger(AService.class);

    @Autowired
    private BService bService;
    @Async
    public CompletableFuture<String> m1(){
        logger.error("ASERVICE");
        return bService.m2().thenApply(result -> result + " | ASERVICE");
    }
}
