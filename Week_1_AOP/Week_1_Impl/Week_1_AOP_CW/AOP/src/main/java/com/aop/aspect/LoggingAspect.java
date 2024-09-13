package com.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

//	@Before("execution(* orderPackage(..))")
//	@Before("execution(* com.aop.service.impl.*.orderPackage(..))")
	@Before("execution(* com.aop.service.impl.*.*(..))")
	public void beforeOrderPackage(JoinPoint joinPoint){
		log.info("Before orderPackage called from LoggingAspect kind, {}",joinPoint.getKind());
		log.info("Before orderPackage called from LoggingAspect signature, {}",joinPoint.getSignature());
	}

	@After("myLoggingAndAopMethodsPointCut()")
	public void afterMyLoggingAndAopMethodsPointCut(){
		log.info("After My Logging Annotation calls");
	}

	@Before("within(com.aop..*)")
	public void beforeServiceImplCalls(){
		log.info("Service impl calls");
	}

//	@Before("@annotation(org.springframework.transaction.annotation.Transactional)")
//	@Before("@annotation(com.aop.aspect.MyLogging)")
	@Before("myLoggingAndAopMethodsPointCut()")
	public void beforeTransactionalAnnotationCalls(){
//		log.info("Before Transactional Annotation calls");
		log.info("Before My Logging Annotation calls");
	}

	@Pointcut("@annotation(com.aop.aspect.MyLogging) && within(com.aop..*)")
	public void myLoggingAndAopMethodsPointCut(){
	}
}
