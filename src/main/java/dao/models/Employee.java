package dao.models;

public class Employee extends Model{
    public static final String FIELDS = "employee_id, name, last_name, birth_date, manager_id, plaza_id";
    public static final String TABLE = "empleados";
    public static final String Q_ALL = String.format("SELECT %s FROM %s", FIELDS, TABLE);
    public static final String Q_BY_ID = String.format("%s WHERE employee_id = ", Q_ALL);
    public static final String INSERT = String.format("INSERT INTO %s (%s) VALUES%s", TABLE, FIELDS, fieldsToInsert(6));
    public static final String UPDATE = String.format("UPDATE %s SET employee_id = ?, name = ?, last_name = ?, birth_date = ?, manager_id = ?, plaza_id = ? WHERE employee_id =", TABLE);
    public static final String DELETE = String.format("DELETE FROM %s WHERE employee_id = ?", TABLE);
    private Long id;
    private String name;
    private String last_name;
    private String birth_date;
    private Employee manager_id;
    private Plaza plaza_id;


    public Employee() {
    }

    public Employee(Long id, String name, String last_name, String birth_date, Employee manager_id, Plaza plaza_id) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.manager_id = manager_id;
        this.plaza_id = plaza_id;
    }

    public Employee getEmployee() {
        return manager_id;
    }

    public static String getFIELDS() {
        return FIELDS;
    }

    public static String getTABLE() {
        return TABLE;
    }

    public static String getqAll() {
        return Q_ALL;
    }

    public static String getqById() {
        return Q_BY_ID;
    }

    public static String getINSERT() {
        return INSERT;
    }

    public static String getUPDATE() {
        return UPDATE;
    }

    public static String getDELETE() {
        return DELETE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public Employee getManager_id() {
        return manager_id;
    }

    public void setManager_id(Employee manager_id) {
        this.manager_id = manager_id;
    }

    public Plaza getPlaza_id() {
        return plaza_id;
    }

    public void setPlaza_id(Plaza plaza_id) {
        this.plaza_id = plaza_id;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s - %s - %s - %s", this.getId(), this.getName(),this.getLast_name(), this.getBirth_date(),this.getManager_id(),this.getPlaza_id());
    }

}
