package by.asalalaiko.domain;

import by.asalalaiko.repo.WithId;

import java.math.BigDecimal;

public class Airport implements WithId {

    private Long id;
    private String name;
    private BigDecimal tax;
    private City city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
