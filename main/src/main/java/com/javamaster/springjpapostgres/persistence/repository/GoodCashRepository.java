package com.javamaster.springjpapostgres.persistence.repository;

import com.javamaster.springjpapostgres.persistence.entity.Good;
import com.javamaster.springjpapostgres.persistence.entity.GoodCash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodCashRepository extends JpaRepository<GoodCash, Integer>, JpaSpecificationExecutor<GoodCash> {
    @Modifying
    @Query(value = "delete from good_cash g where g.id > 0 ", nativeQuery = true)
    void deleteAll();
    @Modifying
    GoodCash save(GoodCash good);

}
