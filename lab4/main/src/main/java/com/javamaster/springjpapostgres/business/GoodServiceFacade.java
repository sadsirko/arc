package com.javamaster.springjpapostgres.business;

import com.javamaster.springjpapostgres.persistence.entity.Good;

import java.util.Date;
import java.util.List;

public interface GoodServiceFacade {
    List<Good> search(String query);

    void create(Good good);
    List<Good> read();
    void update(Good good);
    void delete(Long id);

    void cashData();
    List<Good> getFullSearch(String query);
    List<Good> getFullAll();

}
