package by.asalalaiko.service;

import by.asalalaiko.domain.Airport;
import by.asalalaiko.domain.City;
import by.asalalaiko.repo.AirportRepo;
import by.asalalaiko.repo.CityRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Collection;

public class AirportService {

    public static final Logger LOGGER = LoggerFactory.getLogger(AirportService.class);

    private static AirportService instance;

    private AirportService() {

        instance = this;
    }

    public static AirportService getInstance() {
        if (instance == null) {
            AirportService.instance = new AirportService();
        }
        return instance;
    }

    public static Collection<Airport> findAll() {return AirportRepo.getInstance().findAll();
    }

    public static void deleteById(Long id) {
        AirportRepo.getInstance().deleteById(id);
    }

    public Airport create(String name, BigDecimal tax, City city) {
        Airport airport = new Airport();
        airport.setName(name);
        airport.setTax(tax);
        airport.setCity(city);
        AirportRepo.getInstance().save(airport);
        return airport;
    }

    public Airport update(Long id, String name,BigDecimal tax, City city) {
        Airport airport = new Airport();
        airport.setId(id);
        airport.setName(name);
        airport.setTax(tax);
        airport.setCity(city);
        AirportRepo.getInstance().save(airport);
        return airport;
    }

    public Airport getById(Long id){
        return AirportRepo.getInstance().getById(id);
    }
}
