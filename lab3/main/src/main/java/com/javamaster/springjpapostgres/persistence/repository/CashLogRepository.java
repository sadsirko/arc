package com.javamaster.springjpapostgres.persistence.repository;

import com.javamaster.springjpapostgres.persistence.entity.CashLog;
import com.javamaster.springjpapostgres.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface CashLogRepository extends JpaRepository<CashLog, Integer>, JpaSpecificationExecutor<CashLog> {
    @Query(value = "select creation_date from cash_log where id =( select max(id) from cash_log) ",
            nativeQuery = true)
    Date lastCashDate();
    @Query(value = "insert into cash_log default values ",
            nativeQuery = true)
    void setLogTime();

}

