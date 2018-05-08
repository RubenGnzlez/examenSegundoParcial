package dao.factories.implementations.postgres;

import dao.conection.Conection;
import dao.factories.interphase.CityDaoInterface;
import dao.factories.interphase.PlazasDaoInter;
import dao.models.City;
import dao.models.Plaza;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlazaDao implements PlazasDaoInter{

    private CityDaoInterface cityDaoInterface;



    public PlazaDao(CityDaoInterface city) {
        this.cityDaoInterface = city;
    }

    @Override
    public void create(Plaza obj) {
        try {
            Conection conexion = Conection.getInstance();
            PreparedStatement ps = conexion.getConn().prepareStatement(Plaza.INSERT);
            Integer i =1;
            ps.setLong(i++, obj.getId());
            ps.setString(i++, obj.getCategoria());
            ps.setLong(i++, obj.getCiudad_id().getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {

        }
    }

    @Override
    public List<Plaza> read(String criteria) {
        List<Plaza> paises = new ArrayList<>();
        try {
            Conection conexion = Conection.getInstance();
            Statement st = conexion.getConn().createStatement();
            ResultSet rs = st.executeQuery(String.format("%s %s",Plaza.Q_ALL, criteria));
            while(rs.next()){
                paises.add(makePlaza(rs));
            }
        }catch (ClassNotFoundException | SQLException ex){

        }
        return paises;
    }

    @Override
    public Plaza read(Long id) {
        Plaza plaza = null;
        try {
            Connection conexion = Conection.getInstance().getConn();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(String.format("%s %s",Plaza.Q_BY_ID, id));
            if(rs.next()){
                plaza = makePlaza(rs);
            }
        }catch (ClassNotFoundException | SQLException ex){

        }
        return plaza;
    }

    @Override
    public void update(Plaza obj) {
        try {
            Conection conexion = Conection.getInstance();
            PreparedStatement ps = conexion.getConn()
                    .prepareStatement(String.format("%s %s",Plaza.UPDATE, obj.getId()));
            Integer i =1;
            ps.setLong(i++, obj.getId());
            ps.setString(i++, obj.getCategoria());
            ps.setLong(i++, obj.getCiudad_id().getId());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {

        }
    }

    @Override
    public void delete(Long id) {
        try {
            Conection conexion = Conection.getInstance();
            PreparedStatement ps = conexion.getConn()
                    .prepareStatement(Plaza.DELETE);
            Integer i =1;
            ps.setLong(i++, id);
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {

        }
    }

    private Plaza makePlaza(ResultSet rs) throws SQLException {
        Plaza  country = new Plaza();
        Integer i = 1;
        country.setId(rs.getLong(i++));
        country.setCategoria(rs.getString(i++));
        Long plazaID = rs.getLong(i++);
        country.setCiudad_id(cityDaoInterface.read(plazaID));
        return country;
    }

    public CityDaoInterface getCityDaoInterface() {
        return cityDaoInterface;
    }

    public void setCityDaoInterface(CityDaoInterface cityDaoInterface) {
        this.cityDaoInterface = cityDaoInterface;
    }
}
