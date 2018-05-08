package examenTest.empleados;

import dao.factories.implementations.postgres.CityDao;
import dao.factories.implementations.postgres.EmployeeDao;
import dao.factories.implementations.postgres.PlazaDao;
import dao.factories.interphase.CityDaoInterface;
import dao.factories.interphase.EmployeeDaoInterface;
import dao.factories.interphase.PlazasDaoInter;
import dao.models.City;
import dao.models.Employee;
import dao.models.Plaza;
import org.junit.Test;

public class EmpleadosTest {
    public static Long id = new Long("1");
    public static Long id2 = new Long("2");
    @Test
    public void aCreate(){
        CityDaoInterface cityDaoInterface = new CityDao();
        PlazasDaoInter plazasDaoInter = new PlazaDao(cityDaoInterface);
        City city = cityDaoInterface.read(new Long("1"));
        Plaza plaza = new Plaza(new Long(id),"Categoria Mexicana:v",city);
        EmployeeDaoInterface employeeDaoInterface = null;
        employeeDaoInterface.read(id);
        EmployeeDaoInterface empleado2 = new EmployeeDao(employeeDaoInterface,plazasDaoInter);
        Employee employee = new Employee(id2,"Alfredo","Gonzalez","01-NOVIEMBRE-1119",empleado2,plaza);

    }
}
