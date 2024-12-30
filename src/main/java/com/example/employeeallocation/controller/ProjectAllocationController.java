package com.example.employeeallocation.controller;

import com.example.employeeallocation.model.Employee;
import com.example.employeeallocation.model.Project;
import com.example.employeeallocation.model.ProjectAllocation;
import com.example.employeeallocation.service.ProjectAllocationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/allocations")
@SecurityRequirements({@SecurityRequirement(name = "bearer-auth")})
public class ProjectAllocationController {
     private final ProjectAllocationService service;

    public ProjectAllocationController(ProjectAllocationService service) {
        this.service = service;
    }

    @PostMapping("/{employeeId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<Employee>> addAllocation(
            @PathVariable String employeeId,
            @RequestBody @Valid Project project) {
        return service.addProjectAllocation(employeeId, project)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/projects/{projectId}/second-most-experienced")
    public Mono<ResponseEntity<Employee>> getSecondMostExperienced(@PathVariable String projectId) {
        return service.getSecondMostExperiencedPerson(projectId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/employees/skills")
    public Flux<Employee> findBySkills(
            @RequestParam String primarySkill,
            @RequestParam String secondarySkill) {
        return service.findEmployeesBySkills(primarySkill, secondarySkill);
    }

    @GetMapping("/employees/unallocated")
    public Flux<Employee> findUnallocatedBySkill(@RequestParam String primarySkill) {
        return service.findUnallocatedEmployeesBySkill(primarySkill);
    }
}

