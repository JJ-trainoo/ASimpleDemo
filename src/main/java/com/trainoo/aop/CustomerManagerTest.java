package com.trainoo.aop;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomerManagerTest {
	
    private ApplicationContext applicationContext = null;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring/spring-context.xml");
    }

    @Test
    public void should_print_normal_log_info_when_add_a_customer() throws Exception {
        CustomerManagerService customerManagerService = (CustomerManagerService) applicationContext.getBean("customerManagerService");
        customerManagerService.addCustomer("Mengya");
    }

    //@Test
    public void should_print_warn_log_info_when_add_a_same_customer_twice() throws Exception {
        CustomerManagerService customerManagerService = (CustomerManagerService) applicationContext.getBean("customerManagerService");
        customerManagerService.addCustomer("Mengya");
        customerManagerService.addCustomer("Mengya");
    }
}