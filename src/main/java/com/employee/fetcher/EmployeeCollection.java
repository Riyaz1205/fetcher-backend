package com.employee.fetcher;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;

public class EmployeeCollection {

    @JacksonXmlElementWrapper(useWrapping = false)
    List<Employee> employee;

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employeeCollection) {
        this.employee = employeeCollection;
    }
}
