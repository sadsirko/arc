package com.javamaster.springjpapostgres.persistence.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name  = "cash_log")

public class CashLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "creation_date")
    Date creationDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "CashLog{" +
                "id=" + id +
                ", lastLog=" + creationDate +
                '}';
    }
}