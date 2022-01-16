package by.asalalaiko.domain;

import by.asalalaiko.repo.WithId;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Flight implements WithId {

    private Long id;
    private LocalDateTime start;
    private LocalDateTime finish;
    private Integer km;
    private Airport startAirport;
    private Airport finishAirport;
    private Plane plane;
    private BigDecimal cost;
    private BigDecimal costBaggage;
    private BigDecimal costPriority;



    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public Airport getStartAirport() {
        return startAirport;
    }

    public void setStartAirport(Airport startAirport) {
        this.startAirport = startAirport;
    }

    public Airport getFinishAirport() {
        return finishAirport;
    }

    public void setFinishAirport(Airport finishAirport) {
        this.finishAirport = finishAirport;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal minCost) {
        this.cost = minCost;
    }

    public BigDecimal getCostBaggage() {
        return costBaggage;
    }

    public void setCostBaggage(BigDecimal costBaggage) {
        this.costBaggage = costBaggage;
    }

    public BigDecimal getCostPriority() {
        return costPriority;
    }

    public void setCostPriority(BigDecimal costPriority) {
        this.costPriority = costPriority;
    }
}
