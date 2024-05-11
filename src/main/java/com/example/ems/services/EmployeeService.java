package com.example.ems.services;

import com.example.ems.domain.Employee;
import com.example.ems.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class EmployeeService {

    static Logger LOG = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee create(Employee emp) {
        emp.setTotalSalary(calculateTotalSalary(emp));
        emp.setTaxAmount(getTax(emp.getTotalSalary()));
        emp.setCess(getCess(emp.getTotalSalary()));
        return employeeRepository.save(emp);
    }

    public List<Employee> getAll() {

        return (List<Employee>) employeeRepository.findAll();
    }

    public Long calculateTotalSalary(Employee emp) {
        Long totalSalary = 0L;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.MONTH, 3);
        Date financialStartDate = cal.getTime();
        cal.add(Calendar.MONTH, 12);
        Date financialEndDate = cal.getTime();
        LOG.info("start - {}, end - {}", financialStartDate, financialEndDate);
        if (emp.getDateOfJoining().after(financialStartDate)) {
            long diffInMillies = Math.abs(emp.getDateOfJoining().getTime() - financialStartDate.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            LOG.info("days difference - {}", diff);
            if (diff < 360) {
                totalSalary = (360 - diff) * (emp.getSalary() / 30);
            }
        } else if (emp.getDateOfJoining().before(financialStartDate)) {
            totalSalary = emp.getSalary() * 12;
        }
        LOG.info("Total salary of employee - {}", totalSalary);
        return totalSalary;
    }

    public Double getTax(Long totalSalary) {
        Double totalTax = 0.0;
        if (totalSalary > 1000000) {
            totalTax = (totalSalary - 1000000) * 0.2;
            totalTax += getTax(1000000L);
        } else if (totalSalary > 500000) {
            totalTax = (totalSalary - 500000) * 0.1;
            totalTax += getTax(500000L);
        } else if (totalSalary > 250000) {
            totalTax = (totalSalary - 250000) * 0.05;
            totalTax += getTax(250000L);
        }
        return totalTax;
    }

    public Double getCess(Long totalSalary) {
        Double cess = 0.0;
        if (totalSalary > 2500000) {
            cess = (totalSalary - 2500000) * 0.02;
        }
        return cess;
    }
}
