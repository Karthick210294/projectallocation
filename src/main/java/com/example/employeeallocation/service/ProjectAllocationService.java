package com.example.employeeallocation.service;

import com.example.employeeallocation.config.NotificationEvent;
import com.example.employeeallocation.model.Employee;
import com.example.employeeallocation.model.Project;
import com.example.employeeallocation.model.ProjectAllocation;
import com.example.employeeallocation.repository.EmployeeRepository;
import com.example.employeeallocation.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ProjectAllocationService {
    private static Logger logger = LoggerFactory.getLogger(ProjectAllocationService.class);

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;
    private final NotificationService notificationService;

    @Autowired
    public ProjectAllocationService(
            EmployeeRepository employeeRepository,
            ProjectRepository projectRepository,
            NotificationService notificationService) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
        this.notificationService = notificationService;
    }

    @Cacheable(value = "projectAllocations", key = "#employeeId")
    public Mono<Employee> addProjectAllocation(String employeeId, Project project) {
        Mono<Employee> employeeMono = employeeRepository.findByEmployeeID(employeeId);
                employeeMono.flatMap(employee -> {
                    employee.setProjectName(project.getProjectName());
                    float HC = employee.getTotalHC();
                    employee.setTotalHC((float) (HC + 0.1));
                    // Save employee and send notification
                    return employeeRepository.save(employee)
                            .doOnSuccess(savedEmployee -> {
                                notificationService.sendProjectAllocationNotification(
                                        employeeId,
                                        project.getProjectName()
                                );
                                logger.info("Employee {} updated with project {}",
                                        employeeId,
                                        project.getProjectName());
                            })
                            .doOnError(error ->
                                    logger.error("Error updating employee {}: {}",
                                            employeeId,
                                            error.getMessage())
                            );

                });
//                .flatMap(employee -> {
////                    Project project = projectRepository.findById(allocation.getEmployeeId())
////                            .o(() -> new IllegalArgumentException("Project not found"));
////
////                    if (project.getAllocations().size() >= 3) {
////                        return Mono.error(new IllegalStateException("Project already has 3 allocations"));
////                    }
//
////                    if (isAllocationLimitExceeded(project, employeeId)) {
////                        return Mono.error(new IllegalStateException("Employee already allocated to 3 projects"));
////                    }
//                            ));
//                });
        return employeeMono;
    }

    public Mono<Employee> getSecondMostExperiencedPerson(String projectId) {
//        return projectRepository.findById(projectId)
//                .flatMapMany(project -> Flux.fromIterable(project.getAllocations()))
//                .flatMap(allocation -> employeeRepository.findById(allocation.getEmployeeId()))
//                .sort((e1, e2) -> e2.getOverallExperience().compareTo(e1.getOverallExperience()))
//                .skip(1)
//                .next();
        return null;
    }

    public Flux<Employee> findEmployeesBySkills(String primarySkill, String secondarySkill) {
        return employeeRepository.findByPrimarySkillAndSecondarySkill(primarySkill, secondarySkill);
    }

    public Flux<Employee> findUnallocatedEmployeesBySkill(String primarySkill) {
//        return projectRepository.findAll()
//                .flatMapIterable(Project::getAllocations)
//                .map(ProjectAllocation::getEmployeeId)
//                .collectList()
//                .flatMapMany(allocatedIds ->
//                        employeeRepository.findByPrimarySkillAndEmployeeIdNotIn(primarySkill, allocatedIds));
        return null;
    }

}
