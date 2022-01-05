package by.asalalaiko.service;

import by.asalalaiko.domain.City;
import by.asalalaiko.domain.Plane;
import by.asalalaiko.repo.CityRepo;
import by.asalalaiko.repo.PlaneRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Collection;

public class PlaneService {

    public static final Logger LOGGER = LoggerFactory.getLogger(PlaneService.class);

    private static PlaneService instance;

    private PlaneService() {

        instance = this;
    }

    public static PlaneService getInstance() {
        if (instance == null) {
            PlaneService.instance = new PlaneService();
        }
        return instance;
    }

    public static Collection<Plane> findAll() {return PlaneRepo.getInstance().findAll();
    }

    public static void deleteById(Long id) {
        PlaneRepo.getInstance().deleteById(id);
    }



    public Plane create(String name, String model, Integer seats, BigDecimal costKM) {
        Plane plane = new Plane();
        plane.setName(name);
        plane.setModel(model);
        plane.setSeats(seats);
        plane.setCostKM(costKM);

        PlaneRepo.getInstance().save(plane);
        return plane;
    }

    public Plane update(Long id, String name, String model, Integer seats, BigDecimal costKM) {
        Plane plane = new Plane();
        plane.setId(id);
        plane.setName(name);
        plane.setModel(model);
        plane.setSeats(seats);
        plane.setCostKM(costKM);
        PlaneRepo.getInstance().save(plane);
        return plane;
    }

    public Plane getById(Long id){
        return PlaneRepo.getInstance().getById(id);
    }
}
