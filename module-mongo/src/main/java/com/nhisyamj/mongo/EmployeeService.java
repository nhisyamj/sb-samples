package com.nhisyamj.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDaoImpl empDao;

    @Autowired
    public EmployeeService(EmployeeDaoImpl empDao) {
        this.empDao = empDao;
    }

    public void addEmp(EmployeeVM vm) {
        empDao.addEmp(vm);
    }

    public void updateEmp(String empId, EmployeeVM vm) {
        empDao.updateEmp(empId, vm);
    }

    public EmployeeVM getEmpByEmpId(String empId) {
        return empDao.getEmpByEmpId(empId);
    }

    public List<EmployeeVM> getEmpList() {
        return empDao.getEmpList();
    }

    public void delEmpByEmpId(String empId) {empDao.delEmpbyEmpId(empId);}
}
