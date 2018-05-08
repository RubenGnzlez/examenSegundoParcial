package dao.factories.interphase;

import dao.models.Plaza;

import java.util.List;

public interface PlazasDaoInter {
    public void create(Plaza obj);

    public List<Plaza> read(String criteria);

    public Plaza read(Long id);

    public void update(Plaza obj);

    public void delete(Long id);
}
