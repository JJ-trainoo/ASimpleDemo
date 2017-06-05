package com.trainoo.aop;

import java.util.ArrayList;
import java.util.List;

public class CustomerManager implements CustomerManagerService {

	private List<String> list = new ArrayList<String>();

    public void addCustomer(String customer){
        if (list.contains(customer)) {
            System.out.println("Customer exists, repeat operation");
         }
        list.add(customer);
    }
    
    @Override
    public String toString() {
        return "customerManager{" +
            "list=" + list +
            '}';
    }
}
