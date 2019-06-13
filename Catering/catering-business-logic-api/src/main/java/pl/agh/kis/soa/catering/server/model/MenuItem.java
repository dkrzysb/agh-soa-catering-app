package pl.agh.kis.soa.catering.server.model;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "MenuItem")
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
    private boolean accepted;

    public MenuItem() {}

    public Long getId() { return id; }

    public String getName() { return name; }

    public int getServingSize() { return servingSize; }

    public BigDecimal getPrice() { return price; }

    @XmlTransient
    public MenuCategory getMenuCategory() { return menuCategory; }

    @XmlTransient
    public boolean  getAccepted() { return accepted; }

    @Override
    public String toString() {
        return  name + ',' +
                servingSize + ',' +
                price;
    }
}
