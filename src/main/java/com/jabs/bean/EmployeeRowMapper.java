package com.jabs.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Employee> {
	
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException{
        Employee employee = new Employee();
 
        employee.setId(rs.getInt("ID"));
        employee.setFirstName(rs.getString("FIRST_NAME"));
        employee.setLastName(rs.getString("LAST_NAME"));
     
        return employee;
    }
}