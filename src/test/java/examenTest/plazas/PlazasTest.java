package examenTest.plazas;

import dao.factories.implementations.postgres.CityDao;
import dao.factories.implementations.postgres.PlazaDao;
import dao.factories.interphase.CityDaoInterface;
import dao.factories.interphase.PlazasDaoInter;
import dao.models.City;
import dao.models.Plaza;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PlazasTest {
    public static Long id = new Long("1");
    @Test
    public void aCreate(){
        CityDaoInterface cityDaoInterface = new CityDao();
        PlazasDaoInter plazasDaoInter = new PlazaDao(cityDaoInterface);
        City city = cityDaoInterface.read(new Long("1"));
        Plaza plaza = new Plaza(new Long(id),"Categoria Mexicana:v",city);
        plazasDaoInter.create(plaza);

    }

    @Test
    public void bReadTest(){
        CityDaoInterface cityDaoInterface = new CityDao();
        cityDaoInterface.read(new Long("1"));
        PlazasDaoInter plazasDaoInter = new PlazaDao(cityDaoInterface);
        List<Plaza> plazas = plazasDaoInter.read(String.format("WHERE ciudad_id = %s",id));
        for (Plaza plaza : plazas){
            System.out.println(plaza);
            Assert.assertNotNull(plaza);
        }

    }

    @Test
    public void cReadByIdTest(){
        CityDaoInterface cityDaoInterface = new CityDao();
        cityDaoInterface.read(new Long("1"));
        PlazasDaoInter plazasDaoInter = new PlazaDao(cityDaoInterface);
        Plaza plaza = plazasDaoInter.read(id);
        Assert.assertNotNull(plaza);
        System.out.println(plaza);

    }

    @Test
    public void dUpdateTest(){
        CityDaoInterface cityDaoInterface = new CityDao();
        cityDaoInterface.read(new Long("1"));
        PlazasDaoInter plazasDaoInter = new PlazaDao(cityDaoInterface);
        Plaza plaza = plazasDaoInter.read(id);
        plaza.setCategoria("Categoria fue cambiada");
        plazasDaoInter.update(plaza);
        String plazatoTray= "Categoria fue cambiada";
        Assert.assertEquals(plaza.getCategoria(),plazatoTray);

    }

    @Test
    public void eDeleteTest(){
        CityDaoInterface cityDaoInterface = new CityDao();
        cityDaoInterface.read(new Long("1"));
        PlazasDaoInter plazasDaoInter = new PlazaDao(cityDaoInterface);
        plazasDaoInter.delete(id);
        Plaza plazatoty = plazasDaoInter.read(id);
        Assert.assertNull(plazatoty);
        /*CityDaoInterface cityDao= new CityDao();
        cityDao.delete(id);
        City cityToTr = cityDao.read(id);
        Assert.assertNull(cityToTr);*/
    }

}

