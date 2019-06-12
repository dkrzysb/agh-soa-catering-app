package pl.agh.kis.soa.catering.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "OfferOfTheDay")
@Getter
@Setter
public class OfferOfTheDay implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    MenuItem menuItem;
    private BigDecimal price;
}
