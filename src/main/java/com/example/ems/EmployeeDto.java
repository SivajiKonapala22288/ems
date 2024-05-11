package com.example.ems;

import java.io.Serializable;

public class EmployeeDto implements Serializable {

    public EmployeeDto(Long id, String firstName, String lastName, Double taxAmount, Long totalSalary, Double cess) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.taxAmount = taxAmount;
        this.totalSalary = totalSalary;
        this.cess = cess;
    }

    private Long id;
    private String firstName;
    private String lastName;
    private Double taxAmount;
    private Long totalSalary;
    private Double cess;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public Long getTotalSalary() {
        return totalSalary;
    }

    public Double getCess() {
        return cess;
    }
}
