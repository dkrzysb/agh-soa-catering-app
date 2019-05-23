package pl.agh.kis.soa.catering.server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "MenuCategory")
@Getter
@Setter
public class MenuCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "menuCategory", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<MenuItem> items;
}
