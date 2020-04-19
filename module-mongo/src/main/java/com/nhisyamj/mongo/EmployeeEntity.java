package com.nhisyamj.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class EmployeeEntity {
    @Id
    private String id;

    private String firstName;
    private String staffId;
    private String department;
    private String rank;
}
