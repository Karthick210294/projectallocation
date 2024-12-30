package com.example.employeeallocation.repository;

import com.example.employeeallocation.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {
    Flux<Employee> findByPrimarySkillAndSecondarySkill(String primarySkill, String secondarySkill);
    Flux<Employee> findByPrimarySkillAndEmployeeIdNotIn(String primarySkill, List<String> allocatedEmployeeIds);
    Mono<Employee> findByEmployeeID(String employeeId);
}
