package com.example.demo.Filehandler.models;

public class Calculated {
    private int employee1Id;
    private int employee2Id;
    private int projectId;
    private int daysWorked;

    public Calculated(int employee1Id, int employee2Id, int projectId, int daysWorked) {
        this.employee1Id = employee1Id;
        this.employee2Id = employee2Id;
        this.projectId = projectId;
        this.daysWorked = daysWorked;
    }

    public int getEmployee1Id() {
        return this.employee1Id;
    }

    public void setEmployee1Id(int employee1Id) {
        this.employee1Id = employee1Id;
    }

    public int getEmployee2Id() {
        return this.employee2Id;
    }

    public void setEmployee2Id(int employee2Id) {
        this.employee2Id = employee2Id;
    }

    public int getProjectId() {
        return this.projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getDaysWorked() {
        return this.daysWorked;
    }

    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
    }

    @Override
    public String toString() {
        return "{" +
            " employee1Id='" + getEmployee1Id() + "'" +
            ", employee2Id='" + getEmployee2Id() + "'" +
            ", projectId='" + getProjectId() + "'" +
            ", daysWorked='" + getDaysWorked() + "'" +
            "}";
    }

}
