package pl.agh.kis.soa.catering.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Client")
@Getter
@Setter
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @OneToMany(mappedBy = "client", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Order> orders;
    @OneToMany(mappedBy = "client", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Subscription> subscriptions;
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    private UserAccount userAccount;

    public Client() {
    }

    public Client(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
