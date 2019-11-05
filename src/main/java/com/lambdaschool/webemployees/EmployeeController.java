package com.lambdaschool.webemployees;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

//Java Spring Bean, Managed by spring
@RestController
//means all the end points will start with data
@RequestMapping("/data")
public class EmployeeController {

    //localhost:2019/data/allemployees
    //This will return a list of all employees sorted by first name
    //GetMapping says when you reach this endpoint do this java method
    @GetMapping(value = "/allemployees", produces = {"application/json"})
    public ResponseEntity<?> getAllEmployees()

    {

        WebEmployeesApplication.ourEmpList.empList.sort((e1, e2) -> e1.getFname().compareToIgnoreCase(e2.getFname()));
        return new ResponseEntity<>(WebEmployeesApplication.ourEmpList.empList, HttpStatus.OK);
    }
    //localhost:2019/data/employee/2
    // GetMapping says when you reach this endpoint do this java method
    @GetMapping(value = "/employee/{empid}", produces = {"application/json"})//{empid} means this is a variable and could change

    public ResponseEntity<?> getEmpDetail(@PathVariable long empid)
    {
        Employee rtnEmp = WebEmployeesApplication.ourEmpList.findEmployee(e -> (e.getId() == empid));
        return new ResponseEntity<>(rtnEmp, HttpStatus.OK);

    }


    //Finds employees whose name starts with a letter S and then sorts by their salary
    //localhost:2019/data/employees/s
    @GetMapping(value = "/employees/{letter}", produces = {"application/json"})
    public ResponseEntity<?> getSortedEmployees(@PathVariable char letter){
        ArrayList<Employee> rtnEmps = WebEmployeesApplication.ourEmpList.findEmployees(
                e -> e.getFname().toUpperCase().charAt(0) == Character.toUpperCase(letter));

        rtnEmps.sort((e1, e2) -> ((int) (e1.getSalary() - e1.getSalary())));

        return new ResponseEntity<>(rtnEmps, HttpStatus.OK);

    }
}
