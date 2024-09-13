package com.W1_AOP.service.impl;

import com.W1_AOP.Service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class EmployeeServiceImplTest {

	@Autowired
	private EmployeeService employeeService;

}
