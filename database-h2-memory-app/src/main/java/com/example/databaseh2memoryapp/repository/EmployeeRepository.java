package com.example.databaseh2memoryapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.databaseh2memoryapp.model.Employee;
import com.example.databaseh2memoryapp.model.EmployeeMongo;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
