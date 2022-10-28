package com.employee.fetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService ;


    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/addEmployee",method = RequestMethod.POST)
    public ResponseEntity<Object> addEmployee(@RequestBody Employee employee){
        try{
            employeeService.addEmployee(employee);
            return new ResponseEntity<>("Successfully added Employee", HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to add Employee", HttpStatus.BAD_REQUEST);
        }

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/getAllEmployees",method = RequestMethod.GET)
    public ResponseEntity<Object> getAllEmployees(){
        try{
            List<Employee> list = employeeService.getAllEmployees();
            return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to get Employees", HttpStatus.BAD_REQUEST);
        }

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/processXML",method = RequestMethod.GET)
    public ResponseEntity<Object> processXML(){
        try{
            List<Employee> employeeList = employeeService.computeAndSaveXML();
            return new ResponseEntity<>(employeeList, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to get Employees", HttpStatus.BAD_REQUEST);
        }

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/fetchByFilters",method = RequestMethod.GET)
    public ResponseEntity<Object> getEmployeesByFilter(@RequestParam String name){
        try{
            List<Employee> list = employeeService.getEmployeesByFilter(name);
            return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to get Employees", HttpStatus.BAD_REQUEST);
        }

    }

}
