package com.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspectV2 {

	@Before("allServiceMethodsPointCut()")
	public void beforeServiceMethodCalls(JoinPoint joinPoint){
		log.info("Before advice method call, {}", joinPoint.getSignature());
	}

//	@After("allServiceMethodsPointCut()")
//	@AfterReturning(value = "allServiceMethodsPointCut()", returning = "returnedObj")
	public void afterServiceMethodCalls(JoinPoint joinPoint, Object returnedObj){
		log.info("After advice method call, {}", joinPoint.getSignature());
		log.info("After returning returned value, {}", returnedObj);
	}

	@AfterThrowing(value = "allServiceMethodsPointCut()")
	public void afterServiceMethodCallsThrows(JoinPoint joinPoint){
		log.info("After advice method call, {}", joinPoint.getSignature());
	}

	@Around("allServiceMethodsPointCut()")
	public Object logExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Long startTime = System.currentTimeMillis();
		Object returnedValue = proceedingJoinPoint.proceed();
		Long endTime = System.currentTimeMillis();

		Long diff = endTime - startTime;
		log.info("Time taken for {} is {}",proceedingJoinPoint.getSignature(), diff);
		return returnedValue;
	}

	@Pointcut("execution(* com.aop.service.impl.*.*(..))")
	public void allServiceMethodsPointCut(){

	}

}
