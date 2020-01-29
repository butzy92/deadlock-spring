package com.example.async.deadlock.demo;

import com.example.async.deadlock.demo.service.AService;
import com.example.async.deadlock.demo.service.BService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.logging.Level;


@SpringBootApplication
@EnableAsync
public class Application {
	 private Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) throws ExecutionException, InterruptedException {

		ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
		AService a = run.getBean(AService.class);

		System.out.println("Before");
		String s = a.m1().get();
		System.out.println(s);
		System.out.println("After");
		ThreadPoolTaskExecutor executor = run.getBean(ThreadPoolTaskExecutor.class);
		executor.shutdown();
	}

	@Bean
	@Primary
	Executor executor(){
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(1);
		executor.setThreadNamePrefix("deadlock");
		return executor;
	}
}
