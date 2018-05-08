package examenTest.city;

import dao.factories.implementations.postgres.CityDao;
import dao.factories.interphase.CityDaoInterface;
import dao.models.City;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import org.junit.FixMethodOrder;
import org.junit.Test;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CityTest {
    public static Long id = new Long("1");
    @Test
    public void aCreateTest(){
        CityDaoInterface cityDao= new CityDao();
        City region = new City(id, "Chihuahua", true);
        cityDao.create(region);
        City cityToTry = cityDao.read(id);
        Assert.assertNotNull(cityToTry);
    }

    @Test
    public void bReadTest(){
        CityDaoInterface cityDao= new CityDao();
        List<City> cities = cityDao.read(String.format("WHERE id = %s", id));
        Assert.assertNotNull(cities);
        for (City city : cities){
            System.out.println(city);
            Assert.assertNotNull(city);
        }

    }

    @Test
    public void cReadByIdTest(){
        CityDaoInterface cityDao= new CityDao();
        City city = cityDao.read(id);
        Assert.assertNotNull(city);
        System.out.println(city);
    }

    @Test
    public void dUpdateTest(){
        CityDaoInterface cityDao= new CityDao();
        City city = cityDao.read(id);
        city.setNombre("Oceania");
        cityDao.update(city);
        String cityToTr = "Oceania";
        Assert.assertEquals(city.getNombre(), cityToTr);
    }

    @Test
    public void eDeleteTest(){
        CityDaoInterface cityDao= new CityDao();
        cityDao.delete(id);
        City cityToTr = cityDao.read(id);
        Assert.assertNull(cityToTr);
    }
}
