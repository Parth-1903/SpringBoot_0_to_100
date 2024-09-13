package com.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ValidationAspect {


	@Around("allServiceMethodPointCut()")
	public Object validateOrderId(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Object args[] = proceedingJoinPoint.getArgs();

		Long orderId = (Long) args[0];

		if(orderId > 0){
			return proceedingJoinPoint.proceed();
		}
		return "Cannot called with negative order id";
	}

	@Pointcut("execution(* com.aop.service.impl.*.*(..))")
	public void allServiceMethodPointCut(){

	}

}
