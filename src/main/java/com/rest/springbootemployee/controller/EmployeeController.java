package com.rest.springbootemployee.controller;


import com.rest.springbootemployee.enities.Employee;
import com.rest.springbootemployee.mapper.EmployeeRepository;
import com.rest.springbootemployee.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(params = {"gender"})
    public List<Employee> queryGender(@RequestParam("gender") String gender) {
        return employeeRepository.queryByGender(gender);
    }

    @GetMapping("/{id}")
    public Employee queryEmployeeById(@PathVariable String id)   {
        return employeeRepository.queryEmployeeById(id);
    }

    @GetMapping
    public List<Employee> queryAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> findByPage(@RequestParam("page") int page,
                                     @RequestParam("pageSize") int pageSize) {
        return employeeRepository.findByPage(page, pageSize);
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public String addAnEmployee(@RequestBody Employee employee) {
        employeeRepository.insert(employee);
        return Constant.ADD_EMPLOYEE_SUCCESS;
    }

    @PutMapping("/{id}")
    public String updateAnEmployee(@PathVariable("id") String employeeId,
                                   @RequestBody Employee employee) {
        return employeeRepository.updateAnEmployee(employeeId,employee)?
                Constant.UPDATE_EMPLOYEE_SUCCESS:
                Constant.EMPLOYEE_IS_NOT_EXIST;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAnEmployee(@PathVariable String id) {
        employeeRepository.deleteEmployee(id);
    }

}
