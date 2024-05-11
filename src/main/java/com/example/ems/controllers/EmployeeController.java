package com.example.ems.controllers;

import com.example.ems.EmployeeDto;
import com.example.ems.domain.Employee;
import com.example.ems.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("employee")
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody Employee employee) {
        Employee createdUser = employeeService.create(employee);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("employee")
    public ResponseEntity<List<EmployeeDto>> listEmployee() {
        List<Employee> employeeList = employeeService.getAll();
        List<EmployeeDto> employeeDetails = employeeList.stream().map((a) ->
                new EmployeeDto(a.getId(), a.getFirstName(),a.getLastName(),a.getTaxAmount(),a.getTotalSalary(),a.getCess())
        )
                .collect(Collectors.toList());
        return new ResponseEntity<>(employeeDetails, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
