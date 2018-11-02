package com.albert.orangepiinfo.core.beans.Entities;

import core.sessionmanagment.ISaveable;
import events.ISavableAddEvent;
import events.ISaveableDelateEvent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;


@Entity(name = "User")
public class UserEntity implements ISaveable {

    @Autowired
    @Transient
    private ApplicationEventPublisher applicationEventPublisher;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @Getter
    @Setter
    private Long id;

    @Column(name = "username")
    @Getter
    @Setter
    private String username;

    @Column(name = "password")
    @Getter
    @Setter
    private String password;

    @CreatedDate
    @Column(name = "dateOfCreation")
    @Getter
    @Setter
    private Date date;

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean saveSession() {
        applicationEventPublisher.publishEvent(new ISavableAddEvent(this,this));
        return true;
    }

    @Override
    public boolean delateSession() {
        applicationEventPublisher.publishEvent(new ISaveableDelateEvent(this,this));
        return false;
    }
}
