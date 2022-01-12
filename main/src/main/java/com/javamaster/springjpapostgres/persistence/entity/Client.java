package com.javamaster.springjpapostgres.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name  = "client")

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name="name_surname")
    String nameSurname;
    @Column
    String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nameSurname='" + nameSurname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
