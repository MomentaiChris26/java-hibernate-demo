package com.teiusko;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Many to Many
@Entity
public class HomePlanet {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany
    @JoinColumn(name="homeplanet_id")
    private Collection<Alien> alien = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Collection<Alien> getAlien() {
        return alien;
    }

    public void setAlien(Collection<Alien> alien) {
        this.alien = alien;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
