package com.learn.librarymanagement.model;

import com.learn.librarymanagement.enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;

    @CreationTimestamp
    private Date issueDate;

    @UpdateTimestamp
    private Date updateOn;

    private Date expiryDate;

    @Enumerated(EnumType.STRING)
    private Status cardstatus;

    @OneToOne
    @JoinColumn
    Student student;

    public Card(int cardId, Date issueDate, Date updateOn, Date expiryDate, Status cardstatus, Student student) {
        this.cardId = cardId;
        this.issueDate = issueDate;
        this.updateOn = updateOn;
        this.expiryDate = expiryDate;
        this.cardstatus = cardstatus;
        this.student = student;
    }

    public Card() {
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(Date updateOn) {
        this.updateOn = updateOn;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Status getCardstatus() {
        return cardstatus;
    }

    public void setCardstatus(Status cardstatus) {
        this.cardstatus = cardstatus;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
