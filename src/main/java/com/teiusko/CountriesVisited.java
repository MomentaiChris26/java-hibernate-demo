package com.teiusko;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class CountriesVisited {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String countryName;

    @ManyToMany(mappedBy = "countriesVisited")
    private Collection<Alien> alien = new ArrayList<>();


    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Collection<Alien> getAlien() {
        return alien;
    }

    public void setAlien(Collection<Alien> alien) {
        this.alien = alien;
    }

    @Override
    public String toString() {
        return "CountriesVi sited{" +
                "Id=" + Id +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
