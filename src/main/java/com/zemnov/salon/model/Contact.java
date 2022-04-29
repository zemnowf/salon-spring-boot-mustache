package com.zemnov.salon.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity

public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clientName;
    private String number;
    private String mail;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "user_id")
    private User client;

    public Contact() {
    }

    public Contact(String clientName, String number, String mail, User client) {
        this.clientName = clientName;
        this.number = number;
        this.mail = mail;
        this.client = client;
    }
}
