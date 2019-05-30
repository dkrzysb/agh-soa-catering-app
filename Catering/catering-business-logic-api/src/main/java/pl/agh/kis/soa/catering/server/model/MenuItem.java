package pl.agh.kis.soa.catering.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "MenuItem")
@Getter
@Setter
@XmlRootElement
public class MenuItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int servingSize;
    private BigDecimal price;
    @ManyToOne
    private MenuCategory menuCategory;

    public MenuItem() {}
}
