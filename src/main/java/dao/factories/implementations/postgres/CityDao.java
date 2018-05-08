package dao.factories.implementations.postgres;

import dao.conection.Conection;
import dao.factories.interphase.CityDaoInterface;
import dao.models.City;
import dao.models.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDao implements CityDaoInterface {


    @Override
    public void create(City obj) {
        try {
            Conection conexion = Conection.getInstance();
            PreparedStatement ps = conexion.getConn().prepareStatement(City.INSERT);
            Integer i =1;
            ps.setLong(i++, obj.getId());
            ps.setString(i++, obj.getNombre());
            ps.setBoolean(i++, obj.getActivo());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {

        }
    }

    @Override
    public List<City> read(String criteria) {
        List<City> regiones = new ArrayList<>();
        try {
            Conection conexion = Conection.getInstance();
            Statement st = conexion.getConn().createStatement();
            ResultSet rs = st.executeQuery(String.format("%s %s",City.Q_ALL, criteria));
            while(rs.next()){
                regiones.add(makeCity(rs));
            }
        }catch (ClassNotFoundException | SQLException ex){

        }
        return regiones;
    }

    @Override
    public City read(Long id) {
        City city = null;
        try {
            Connection conexion = Conection.getInstance().getConn();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(String.format("%s %s",City.Q_BY_ID, id));
            if(rs.next()){
                city = makeCity(rs);
            }
        }catch (ClassNotFoundException | SQLException ex){

        }
        return city;
    }

    @Override
    public void update(City obj) {
        try {
            Conection conexion = Conection.getInstance();
            PreparedStatement ps = conexion.getConn()
                    .prepareStatement(String.format("%s %s",City.UPDATE, obj.getId()));
            Integer i =1;
            ps.setLong(i++, obj.getId());
            ps.setString(i++, obj.getNombre());
            ps.setBoolean(i++, obj.getActivo());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {

        }
    }

    @Override
    public void delete(Long id) {
        try {
            Conection conexion = Conection.getInstance();
            PreparedStatement ps = conexion.getConn()
                    .prepareStatement(City.DELETE);
            Integer i =1;
            ps.setLong(i++, id);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {

        }
    }
    private City makeCity(ResultSet rs) throws SQLException {
        City city = new City();
        Integer i = 1;
        city.setId(rs.getLong(i++));
        city.setNombre(rs.getString(i++));
        city.setActivo(rs.getBoolean(i++));
        return city;
    }
}
