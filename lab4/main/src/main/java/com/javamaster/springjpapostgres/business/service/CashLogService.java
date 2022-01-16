package com.javamaster.springjpapostgres.business.service;

import com.javamaster.springjpapostgres.persistence.entity.CashLog;
import com.javamaster.springjpapostgres.persistence.entity.Good;
import com.javamaster.springjpapostgres.persistence.entity.GoodCash;
import com.javamaster.springjpapostgres.persistence.repository.CashLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CashLogService {
    @Autowired
    private final CashLogRepository cashLogRepository;
    private final int CASH_COOLDOWN = 24;

    public CashLogService(CashLogRepository cashLogRepository) {
        this.cashLogRepository = cashLogRepository;
    }

    public List<CashLog> findAll(){return cashLogRepository.findAll();};

    public Date getLastDate(){
        return cashLogRepository.lastCashDate();};

    public double hoursAfterLog() {
        Date now = new Date();
        Date date = getLastDate();
        double diff = now.getTime() - date.getTime();
        double hours = diff / 3600000;
        return hours;
    }

    public boolean isCooldownGone() {
        System.out.println(hoursAfterLog());
        boolean isGone = hoursAfterLog() > CASH_COOLDOWN;
        return isGone;
    }

    public void setCashDate(){cashLogRepository.setLogTime();}

}
