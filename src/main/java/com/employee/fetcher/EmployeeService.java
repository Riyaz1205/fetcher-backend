package com.employee.fetcher;

import java.util.HashMap;
import java.util.List;

public interface EmployeeService {

    void addEmployee(Employee employee) throws Exception;
    List<Employee> getAllEmployees();

    List<Employee> getEmployeesByFilter(String name);

    List<Employee> computeAndSaveXML();

}
