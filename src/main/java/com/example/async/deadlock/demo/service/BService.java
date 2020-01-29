package com.example.async.deadlock.demo.service;

import com.example.async.deadlock.demo.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class BService {
    private Logger logger = LoggerFactory.getLogger(BService.class);

    @Async
    public CompletableFuture<String> m2(){
        logger.error("BSERVICE");
        return CompletableFuture.completedFuture("BService");
    }
}
