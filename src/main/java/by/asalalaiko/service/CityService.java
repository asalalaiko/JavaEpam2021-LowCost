package by.asalalaiko.service;

import by.asalalaiko.domain.City;
import by.asalalaiko.domain.User;
import by.asalalaiko.repo.CityRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class CityService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CityService.class);

    private static CityService instance;

    private CityService() {

        instance = this;
    }

    public static CityService getInstance() {
        if (instance == null) {
            CityService.instance = new CityService();
        }
        return instance;
    }

    public static Collection<City> findAll() {return CityRepo.getInstance().findAll();
    }

    public static void deleteById(Long cityId) {
        CityRepo.getInstance().deleteById(cityId);
    }

    public City create(String name) {
        City city = new City();
        city.setName(name);
        CityRepo.getInstance().save(city);
        return city;
    }

    public City update(Long id, String name) {
        City city = new City();
        city.setId(id);
        city.setName(name);
        CityRepo.getInstance().save(city);
        return city;
    }

    public City getById(Long id){
        return CityRepo.getInstance().getById(id);
    }
}
