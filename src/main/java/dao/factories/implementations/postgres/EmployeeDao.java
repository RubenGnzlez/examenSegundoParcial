package dao.factories.implementations.postgres;

import dao.conection.Conection;
import dao.factories.interphase.EmployeeDaoInterface;
import dao.factories.interphase.PlazasDaoInter;
import dao.models.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements EmployeeDaoInterface{

    private EmployeeDaoInterface employeeDaoInterface;

    private PlazasDaoInter plazasDaoInter;

    public EmployeeDao(EmployeeDaoInterface employee, PlazasDaoInter plazasDaoInter) {
        this.employeeDaoInterface = employee;
        this.plazasDaoInter = plazasDaoInter;
    }

    @Override
    public void create(Employee obj) {

        try {
            Conection conexion = Conection.getInstance();
            PreparedStatement ps = conexion.getConn().prepareStatement(Employee.INSERT);
            Integer i =1;
            ps.setLong(i++, obj.getId());
            ps.setString(i++, obj.getName());
            ps.setString(i++,obj.getLast_name());
            ps.setString(i++,obj.getBirth_date());
            ps.setLong(i++,obj.getEmployee().getId());
            ps.setLong(i++,obj.getPlaza_id().getId());

            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {

        }

    }

    @Override
    public List<Employee> read(String criteria) {

        List<Employee> paises = new ArrayList<>();
        try {
            Conection conexion = Conection.getInstance();
            Statement st = conexion.getConn().createStatement();
            ResultSet rs = st.executeQuery(String.format("%s %s",Employee.Q_ALL, criteria));
            while(rs.next()){
                paises.add(makeEmployee(rs));
            }
        }catch (ClassNotFoundException | SQLException ex){

        }
        return paises;
    }

    @Override
    public Employee read(Long id) {
        Employee employee = null;
        try {
            Connection conexion = Conection.getInstance().getConn();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(String.format("%s %s",Employee.Q_BY_ID, id));
            if(rs.next()){
                employee = makeEmployee(rs);
            }
        }catch (ClassNotFoundException | SQLException ex){

        }
        return employee;
    }

    @Override
    public void update(Employee obj) {
        try {
            Conection conexion = Conection.getInstance();
            PreparedStatement ps = conexion.getConn()
                    .prepareStatement(String.format("%s %s",Employee.UPDATE, obj.getId()));
            Integer i =1;
            /*
        ps.setLong(i++, obj.getId());
            ps.setString(i++, obj.getName());
            ps.setString(i++,obj.getLast_name());
            ps.setString(i++,obj.getBirth_date());
            ps.setLong(i++,obj.getEmployee().getId());
            ps.setLong(i++,obj.getPlaza_id());
         */
            ps.setLong(i++, obj.getId());
            ps.setString(i++, obj.getName());
            ps.setString(i++, obj.getLast_name());
            ps.setString(i++, obj.getBirth_date());
            ps.setLong(i++, obj.getManager_id().getId());
            ps.setLong(i++, obj.getPlaza_id().getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {

        }
    }

    @Override
    public void delete(Long id) {
        try {
            Conection conexion = Conection.getInstance();
            PreparedStatement ps = conexion.getConn()
                    .prepareStatement(Employee.DELETE);
            Integer i =1;
            ps.setLong(i++, id);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {

        }

    }

    private Employee makeEmployee(ResultSet rs) throws SQLException {

        Employee  employee = new Employee();
        Integer i = 1;
        employee.setId(rs.getLong(i++));
        employee.setName(rs.getString(i++));
        employee.setLast_name(rs.getString(i++));
        employee.setBirth_date(rs.getString(i++));
        Long id_manager = rs.getLong(i++);
        employee.setManager_id(employeeDaoInterface.read(id_manager));
        Long id_plaza = rs.getLong(i++);
        employee.setPlaza_id(plazasDaoInter.read(id_plaza));
        return employee;
    }

    public EmployeeDaoInterface getEmployeeDaoInterface() {
        return employeeDaoInterface;
    }

    public void setEmployeeDaoInterface(EmployeeDaoInterface employeeDaoInterface) {
        this.employeeDaoInterface = employeeDaoInterface;
    }

    public PlazasDaoInter getPlazasDaoInter() {
        return plazasDaoInter;
    }

    public void setPlazasDaoInter(PlazasDaoInter plazasDaoInter) {
        this.plazasDaoInter = plazasDaoInter;
    }
}
