/**
 * 
 */
package com.example.databaseh2memoryapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Guna Palani
 *
 */
@Entity
@Document(collection = "employees")
public class EmployeeMongo {
	
	@Id
	private String id;
	
	private String first_name;
	private String last_name;
	private String email_id;
	
	public EmployeeMongo(){}
	
	public EmployeeMongo(String first_name, String last_name, String email_id){
		this.first_name = first_name;
		this.last_name = last_name;
		this.email_id = email_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Field("first_name")
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	@Field("last_name")
	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	@Field("email_address")
	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	
	public String toString(){
		return "id : "+id+ " firstName :: "+first_name+ " lastName :: "+last_name+ " emailAddress ::"+email_id;
	}
	
}
