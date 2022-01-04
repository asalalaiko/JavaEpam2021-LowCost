package by.asalalaiko.domain;

import by.asalalaiko.repo.WithId;

import java.math.BigDecimal;

public class Plane implements WithId {

    private Long id;
    private String name;
    private String model;
    private Integer seats;
    private BigDecimal costKM;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public BigDecimal getCostKM() {
        return costKM;
    }

    public void setCostKM(BigDecimal costKM) {
        this.costKM = costKM;
    }
}
