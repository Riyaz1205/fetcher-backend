package com.employee.fetcher;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao ;

    @Override
    public void addEmployee(Employee employee) throws Exception {
        employeeDao.addEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployee();
    }

    @Override
    public List<Employee> getEmployeesByFilter(String name) {
        return employeeDao.getEmployeesByFilter(name);
    };

    @Override
    public List<Employee> computeAndSaveXML() {
        try {
            InputStream ip = new FileInputStream("/Users/zeus/Downloads/fetcher/src/main/resources/1.xml");
            XmlMapper xmlMapper = new XmlMapper();
            List<Employee> employeeList1 = xmlMapper.readValue(ip, new TypeReference<List<Employee>>() {});

            ip = new FileInputStream("/Users/zeus/Downloads/fetcher/src/main/resources/2.xml");
            List<Employee> employeeList2 = xmlMapper.readValue(ip, new TypeReference<List<Employee>>() {});

            List<Employee> employeeList3 = new ArrayList<>();

            for(int i = 0 ; i < employeeList1.size() ; i++) {
                Employee employee = new Employee();
                String name = employeeList1.get(i).getName();
                String address = employeeList1.get(i).getAddress();
                String phone = employeeList1.get(i).getPhone();
                employee.setName(name);
                employee.setAddress(address);
                employee.setPhone(phone);

                Employee filterEmployee =
                        employeeList2.stream().filter(item -> item.getName().equals(name)).collect(Collectors.toList()).get(0);

                employee.setPension(filterEmployee.getPension());
                employee.setSalary(filterEmployee.getSalary());
                employeeList3.add(employee);

            }

            EmployeeCollection employeeCollection = new EmployeeCollection();
            employeeCollection.setEmployee(employeeList3);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            xmlMapper.writeValue(byteArrayOutputStream, employeeCollection);

            FileOutputStream fout = new FileOutputStream("/Users/zeus/Downloads/fetcher/src/main/resources/3.xml",
                    false);

            byte[] array = byteArrayOutputStream.toString().getBytes();
            fout.write(array);
            fout.close();

            employeeList3.forEach(employee -> {
                employeeDao.addEmployee(employee);
            });

            return employeeList3;

        } catch (Exception e) {
            System.out.println(e.getCause() + " " + e.getMessage());
            return null;
        }
    }

}
