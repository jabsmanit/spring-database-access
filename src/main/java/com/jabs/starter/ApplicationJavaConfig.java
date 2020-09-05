package com.jabs.starter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.jabs.bean.Employee;
import com.jabs.bean.EmployeeRowMapper;
import com.jabs.config.SpringJdbcConfig;

public class ApplicationJavaConfig {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(SpringJdbcConfig.class);

		JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
		String sql = "select First_Name from employee";
        String name = jdbcTemplate.queryForObject(sql,new Object[]{},String.class);
        System.out.println("All record: First name is " + name);
        
        
        //Row Mapper
        int id = 1;
        String query = "SELECT * FROM EMPLOYEE WHERE ID = ?";
        Employee employees =  jdbcTemplate.queryForObject(
          query, new Object[] { id }, new EmployeeRowMapper());
        System.out.println("With ID: Employee: " + employees);
        
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = (NamedParameterJdbcTemplate) context.getBean("namedParameterJdbcTemplate");
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", 1);
        String namedQuery = "SELECT * FROM EMPLOYEE WHERE ID = :id";
        Employee namedEmployee =  namedParameterJdbcTemplate.queryForObject(namedQuery, namedParameters, new EmployeeRowMapper());

        System.out.println("Named Employee: " + namedEmployee);
	}
}