package com.example.ems.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
public class Employee {

    @Id
    @NotNull(message = "Employee id should not be empty")
    @Digits(integer = 5, fraction = 0, message = "Employee id should not cross 5 digits")
    @Positive(message = "Enter valid Employee id")
    private Long id;

    @NotBlank(message = "First Name should not be empty")
    @Size(min = 2, max = 100, message = "The length of first name must be between 2 and 100 characters.")
    private String firstName;

    @NotBlank(message = "Last Name should not be empty")
    @Size(min = 1, max = 100, message = "The length of last name must be between 1 and 100 characters.")
    private String lastName;

    @NotBlank(message = "Email should not be empty")
    @Email(message = "Enter valid email")
    private String email;

    @NotNull(message = "Date of joining should not be empty")
    @PastOrPresent(message = "Date of joining should be past or current date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,pattern = "yyyy-MM-dd")
    private Date dateOfJoining;

    @NotEmpty(message = "At least one phone number is required")

    private List<@NotBlank(message = "Please enter a valid phone number")
            @Pattern(regexp = "(^$|[5-9]{1}[0-9]{9})", message = "Please enter a valid phone number") String> phoneNumbers;

    @NotNull(message = "Salary should not be empty")
    @Positive(message = "Salary should be grater than zero")
    private Long salary;

    private Double taxAmount;
    private Long totalSalary;
    private Double cess;

    public Double getTaxAmount() {
        return taxAmount;
    }

    public Long getTotalSalary() {
        return totalSalary;
    }

    public Double getCess() {
        return cess;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public void setTotalSalary(Long totalSalary) {
        this.totalSalary = totalSalary;
    }

    public void setCess(Double cess) {
        this.cess = cess;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public Long getSalary() {
        return salary;
    }
}
