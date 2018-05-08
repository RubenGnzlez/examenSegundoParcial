package dao.factories.interphase;

import dao.models.City;
import dao.models.Employee;

import java.util.List;

public interface CityDaoInterface {
    public void create(City obj);

    public List<City> read(String criteria);

    public City read(Long id);

    public void update(City obj);

    public void delete(Long id);
}
