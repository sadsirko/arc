package com.javamaster.springjpapostgres.repository;

import com.javamaster.springjpapostgres.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodRepository extends JpaRepository<Good, Integer>, JpaSpecificationExecutor<Good> {
    List<Good> findAllByName(String name);
    Good findById(Long id);
    @Query(value = "select name,price from good", nativeQuery = true)
    List<Object> findNamePriceAll();

}
