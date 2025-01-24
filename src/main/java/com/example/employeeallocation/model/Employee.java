package com.example.employeeallocation.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Document(collection = "employee")
@Data
@Builder
public class Employee {
    @Id
    private String employeeID;
    private int age;
    private String employeeName;
    private CapabilityCentre capabilityCentre;
    private LocalDate dateOfJoining;
    private Designation designation;
    private String primarySkill;
    private String secondarySkill;
    private Double overallExperience;
    private float totalHC;

    public float getTotalHC() {
        return totalHC;
    }

    public void setTotalHC(float totalHC) {
        this.totalHC = totalHC;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    private String projectName;

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeID = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public CapabilityCentre getCapabilityCentre() {
        return capabilityCentre;
    }

    public void setCapabilityCentre(CapabilityCentre capabilityCentre) {
        this.capabilityCentre = capabilityCentre;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public String getPrimarySkill() {
        return primarySkill;
    }

    public void setPrimarySkill(String primarySkill) {
        this.primarySkill = primarySkill;
    }

    public String getSecondarySkill() {
        return secondarySkill;
    }

    public void setSecondarySkill(String secondarySkill) {
        this.secondarySkill = secondarySkill;
    }

    public Double getOverallExperience() {
        return overallExperience;
    }

    public void setOverallExperience(Double overallExperience) {
        this.overallExperience = overallExperience;
    }
}