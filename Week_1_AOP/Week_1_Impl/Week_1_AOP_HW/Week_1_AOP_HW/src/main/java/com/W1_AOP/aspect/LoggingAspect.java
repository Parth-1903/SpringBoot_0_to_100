package com.W1_AOP.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

	@Before("apiCall()")
	public void beforeMethodCall(){
		log.info("Before any method call");
	}

	@After("apiCall()")
	public void afterMethodCall(){
		log.info("After any method call");
	}

	@Around("apiCall()")
	public Object aroundTimeToMethodCall(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		Long startTime = System.currentTimeMillis();
		Object returnedValue =proceedingJoinPoint.proceed();
		Long endTime = System.currentTimeMillis();

		Long diff = endTime - startTime;
		log.info("Time taken for {} is a {}",proceedingJoinPoint.getSignature(),diff);
		return returnedValue;
	}

	@Pointcut("within(com.W1_AOP.Service..*)")
	public void apiCall(){

	}

}
