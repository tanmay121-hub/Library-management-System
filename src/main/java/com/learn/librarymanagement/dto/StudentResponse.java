package com.learn.librarymanagement.dto;

import com.learn.librarymanagement.enums.Department;
import com.learn.librarymanagement.enums.Status;
import jakarta.persistence.*;

import java.util.Date;

public class StudentResponse {

    // Student Info
    private int id;
    private String name;
    private String mail;
    private Department department;
    private String mobNo;
    private int age;

    // Card Info
    private int cardId;
    private Status cardStatus;
    private Date cardExpiryDate;

    public StudentResponse() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public Status getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(Status cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Date getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(Date cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }
}
