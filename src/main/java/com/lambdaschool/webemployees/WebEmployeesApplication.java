package com.lambdaschool.webemployees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebEmployeesApplication {

    static EmpList ourEmpList;
    public static void main(String[] args) {
        ourEmpList = new EmpList();
        SpringApplication.run(WebEmployeesApplication.class, args);
    }

}
