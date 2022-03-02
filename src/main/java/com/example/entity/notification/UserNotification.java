package com.example.entity.notification;

import com.example.entity.user.User;
import com.example.entity.user.Administrator;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity(name = "NOTIFICATIONS")
public class UserNotification {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "BODY")
    private String notificationBody;

    @NotNull
    @Column(name = "DATE_OF_SENDING")
    private Timestamp dateOfSending;

    @NotNull
    @Column(name = "REVISION_RESULT")
    private String revisionResult;

    /**
     * администратор, отправивший уведомление
     **/
    @NotNull
    @ManyToOne
    @JoinColumn(name = "SENDER_ID", referencedColumnName = "ID")
    private Administrator sender;

    /**
     * список пользователей, которым нужно отправить уведомление (получатели)
     **/
    @NotNull
    @ManyToMany
    @JoinTable(
            name = "NOTIFICATIONS_RECIPIENTS",
            joinColumns = @JoinColumn(name = "MESSAGE_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    )
    private List<User> recipients;
}
