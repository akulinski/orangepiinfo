package com.albert.orangepiinfo.core.beans.Entities;

import com.albert.orangepiinfo.sessionmanagment.callbacks.OnSessionAddedCallback;
import com.albert.orangepiinfo.sessionmanagment.callbacks.implementations.OnSessionAddedCallbackImpl;
import com.albert.orangepiinfo.sessionmanagment.events.ISavableAddEvent;
import com.albert.orangepiinfo.sessionmanagment.sessionstoring.ISavable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;


@Entity(name = "User")
public class UserEntity implements ISavable {

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
    public String saveSession(ApplicationEventPublisher applicationEventPublisher) {
        OnSessionAddedCallback onSessionAddedCallback = new OnSessionAddedCallbackImpl();
        applicationEventPublisher.publishEvent(new ISavableAddEvent(this,this,onSessionAddedCallback));
        return onSessionAddedCallback.getReturnedId();
    }

    @Override
    public boolean delateSession() {
        return false;
    }
}
