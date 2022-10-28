package com.employee.fetcher;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao{

    @PersistenceContext
    EntityManager entityManager ;

    @Override
    public void addEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    @Override
    public List<Employee> getAllEmployee(){
        Query query = entityManager.createNativeQuery("Select * from employee",Employee.class);
        List<Employee> employeeList = query.getResultList();
        return employeeList ;
    }

    @Override
    public List<Employee> getEmployeesByFilter(String name) {
        Query query = entityManager.createNativeQuery(
                    "Select * from employee where name LIKE :name OR address LIKE :name OR phone LIKE :name OR salary LIKE :name OR pension LIKE :name",Employee.class);
        query.setParameter("name", name);
        List<Employee> employeeList = query.getResultList();
        return employeeList ;
    }

}
