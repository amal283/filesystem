package com.stc.filesystem.controller;

import com.stc.filesystem.dto.Employee;
import com.stc.filesystem.dto.EmployeeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    private final EmployeeRepository repository;

    ItemController(EmployeeRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/items")
    void one() {

        Employee employee = new Employee();
        employee.setName("Hello");
        repository.save(employee);
    }

    @GetMapping("/item")
    List<Employee> get() {

        return repository.findAll();
    }

}
