package com.employee.fetcher;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List ;
import java.util.UUID;

@Repository
public interface EmployeeDao {

    void addEmployee(Employee employee);
//    void updateEmployee(int id ,Employee employee);
//    void deleteEmployee(int id);
    List<Employee> getAllEmployee();
    List<Employee> getEmployeesByFilter(String name);
//    Employee getEmployeeById(UUID id);
}
