package com.example.project.Domain;

public class DeliveryModel {
    private String service;
    private String estimate;
    private int cost;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public DeliveryModel() {
    }
}
