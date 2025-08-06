package com.example.ferreteria.Repository;

import com.example.ferreteria.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<Employee,Long> {
}
