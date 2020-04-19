package com.nhisyamj.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmpDao extends MongoRepository<EmployeeEntity,String> {
    EmployeeEntity findByStaffId(String staffId);
}
