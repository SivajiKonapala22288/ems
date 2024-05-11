package com.example.ems.services;

import com.example.ems.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    EmployeeService employeeService;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeService();
    }

    @Test
    public void testCalculateTotalSalary() throws ParseException {
        // Test with a single employee
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("john.doe@example.com");
        String dateInString = "1-Jan-2024";
        employee.setDateOfJoining(formatter.parse(dateInString));
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("1234567890");
        employee.setPhoneNumbers(phoneNumbers);
        employee.setSalary(10L);
        assertEquals(120L, employeeService.calculateTotalSalary(employee));

        // Test with multiple employees
        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setFirstName("Jane");
        employee2.setLastName("Doe");
        employee2.setEmail("jane.doe@example.com");
        employee2.setDateOfJoining(formatter.parse("7-Jun-2024"));
        List<String> phoneNumbers2 = new ArrayList<>();
        phoneNumbers2.add("9876543210");
        employee2.setPhoneNumbers(phoneNumbers2);
        employee2.setSalary(30L);
        assertEquals(293L, employeeService.calculateTotalSalary(employee2));

        Employee employee3 = new Employee();
        employee3.setId(2L);
        employee3.setFirstName("Jane");
        employee3.setLastName("Doe");
        employee3.setEmail("jane.doe@example.com");
        employee3.setDateOfJoining(formatter.parse("7-Apr-2025"));
        List<String> phoneNumbers3 = new ArrayList<>();
        phoneNumbers2.add("9876543210");
        employee3.setPhoneNumbers(phoneNumbers3);
        employee3.setSalary(30L);
        assertEquals(0L, employeeService.calculateTotalSalary(employee3));

    }

    @Test
    public void testGetTax() {
        assertEquals(0.0, employeeService.getTax(25000L));
        assertEquals(0.0, employeeService.getTax(250000L));
        assertEquals(12500.0, employeeService.getTax(500000L));
        assertEquals(62500.0, employeeService.getTax(1000000L));
        assertEquals(102500.0, employeeService.getTax(1200000L));
    }
}