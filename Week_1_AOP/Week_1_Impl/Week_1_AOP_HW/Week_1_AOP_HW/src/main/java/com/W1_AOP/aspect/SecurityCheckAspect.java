package com.W1_AOP.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class SecurityCheckAspect {

	@Pointcut("within(com.W1_AOP.Service.EmployeeService.*)")
	public void apiCall(){

	}

}
