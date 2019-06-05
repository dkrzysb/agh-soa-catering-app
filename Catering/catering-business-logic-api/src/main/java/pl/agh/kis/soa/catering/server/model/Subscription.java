package pl.agh.kis.soa.catering.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Subscription")
@Getter
@Setter
public class Subscription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<MenuItem> menuItems;
    @ManyToOne
    private Client client;
    private String daysOfTheWeek;
}




