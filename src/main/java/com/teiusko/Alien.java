package com.teiusko;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
// @Entity(name="alien_table") When you want to add the data to a different table name
//@Table(name="alien_table") // Change the table name
public class Alien {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Embedded
    private AlienName Name;

    @Column(name="alien_color") // change column name
    private String Color;

    @Transient // prevents Race string from being stored
    private String Race;

    @OneToOne
    private Spaceship spaceship;

    @ManyToOne
    @JoinColumn(name="homeplanet_id",insertable = false,updatable = false)
    private HomePlanet homePlanet;

    @ManyToMany
    private Collection<CountriesVisited> countriesVisited = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AlienName getName() {
        return Name;
    }

    public void setName(AlienName name) {
        Name = name;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getRace() {
        return Race;
    }

    public void setRace(String race) {
        Race = race;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    public HomePlanet getHomePlanet() {
        return homePlanet;
    }

    public void setHomePlanet(HomePlanet homePlanet) {
        this.homePlanet = homePlanet;
    }

    public Collection<CountriesVisited> getCountriesVisited() {
        return countriesVisited;
    }

    public void setCountriesVisited(Collection<CountriesVisited> countriesVisited) {
        this.countriesVisited = countriesVisited;
    }

    @Override
    public String toString() {
        return "Alien{" +
                "id=" + id +
                ", Name=" + Name +
                ", Color='" + Color + '\'' +
                ", Race='" + Race + '\'' +
                ", spaceship=" + spaceship +
                ", homePlanet=" + homePlanet +
                ", countriesVisited=" + countriesVisited +
                '}';
    }
}
