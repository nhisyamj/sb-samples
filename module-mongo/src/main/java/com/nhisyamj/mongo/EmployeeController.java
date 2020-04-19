package com.nhisyamj.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private final EmployeeService empService;

    @Autowired
    public EmployeeController(EmployeeService empService) {
        this.empService = empService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeVM> addEmployee(@RequestBody EmployeeVM vm) {
        empService.addEmp(vm);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeVM> updateEmployee(@RequestParam("emp_id") String empId, @RequestBody EmployeeVM vm) {
        empService.updateEmp(empId, vm);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<EmployeeVM> getEmpByEmpId(@RequestParam("emp_id") String empId) {
        EmployeeVM vm = empService.getEmpByEmpId(empId);
        return ResponseEntity.ok().body(vm);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<EmployeeVM>> getEmpList() {
        List<EmployeeVM> empList = empService.getEmpList();
        return ResponseEntity.ok(empList);
    }

    @DeleteMapping(path = "/{empId}")
    public ResponseEntity<Void> deleteEmpById(@PathVariable String empId) {
        empService.delEmpByEmpId(empId);
        return ResponseEntity.ok().build();
    }
}
