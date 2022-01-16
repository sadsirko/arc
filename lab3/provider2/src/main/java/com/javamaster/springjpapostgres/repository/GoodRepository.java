package com.javamaster.springjpapostgres.repository;

import com.javamaster.springjpapostgres.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface GoodRepository extends JpaRepository<Good, Integer>  {

    Good findById(Long id);

    @Query(value = "select count(*) from good", nativeQuery = true)
    Integer findAmount();

    @Query(value = "SELECT * FROM good\n" +
            " WHERE id >= ?1                             \n" +
            " ORDER BY id ASC\n" +
            "  LIMIT ?2", nativeQuery = true)
    List<Good> getByPage(int start,int limit);

    @Query(value = "SELECT * FROM good\n" +
            " ORDER BY id ASC\n" +
            "  LIMIT ?1", nativeQuery = true)
    List<Good> firstPage(int lim);

    @Query(value = "WITH top AS(SELECT * FROM good\n" +
            " ORDER BY id ASC                           \n" +
            "  LIMIT ?1)\n" +
            "select id from top order by id desc limit 1;",nativeQuery = true)
    int firstTop(int limit);

    @Query(value = "select id,name,price from good", nativeQuery = true)
    List<Object> findNamePriceAll();

}
