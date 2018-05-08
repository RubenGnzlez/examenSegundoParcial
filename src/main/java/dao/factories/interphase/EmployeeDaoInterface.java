package dao.factories.interphase;

import dao.models.Employee;

import java.util.List;

public interface EmployeeDaoInterface {
    public void create(Employee obj);

    public List<Employee> read(String criteria);

    public Employee read(Long id);

    public void update(Employee obj);

    public void delete(Long id);
}
