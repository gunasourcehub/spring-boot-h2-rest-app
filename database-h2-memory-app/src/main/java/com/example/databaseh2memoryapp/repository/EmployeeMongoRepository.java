/**
 * 
 */
package com.example.databaseh2memoryapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.databaseh2memoryapp.model.EmployeeMongo;


/**
 * @author Guna Palani
 *
 */
@Repository
public interface EmployeeMongoRepository extends MongoRepository<EmployeeMongo, Long>{

}
