package com.learn.librarymanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learn.librarymanagement.enums.Department;
import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stdId;

    private String name;
    private int age;
    private String mail;

    @Enumerated(EnumType.STRING)
    private Department department;

    private String mobNo;

    @JsonIgnore
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    Card card;

    public Student(int stdId, String name, int age, Department department, String mobNo, String mail) {
        this.stdId = stdId;
        this.name = name;
        this.age = age;
        this.department = department;
        this.mobNo = mobNo;
        this.mail = mail;
    }
    public Student(){

    }

    public int getStdId() {
        return stdId;
    }

    public void setStdId(int stdId) {
        this.stdId = stdId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
