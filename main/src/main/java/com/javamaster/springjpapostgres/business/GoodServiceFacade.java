package com.javamaster.springjpapostgres.business;

import com.javamaster.springjpapostgres.persistence.entity.Good;

import java.util.List;

public interface GoodServiceFacade {
    List<Good> search(String query);

    void create(Good good);
    List<Good> read();
    void update(Good good);
    void delete(Long id);

    List<Good> fullSearch(String query);
    List<Good> getFullAll();


}
