package pl.agh.kis.soa.catering.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ClientOrder")
@Getter
@Setter
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Client client;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<MenuItem> menuItems;
    private Date date;
    private BigDecimal price;
    private Boolean confirmed;
    private Boolean shipPending;
    private Boolean shipped;
    private String street;
    private String city;
    private String postalCode;

    public Order(List<MenuItem> orderedMenuItems, Date date, BigDecimal orderPrice, String street, String city, String postalCode) {
        this.menuItems = orderedMenuItems;
        this.date = date;
        this.price = orderPrice;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Order(){}
}
