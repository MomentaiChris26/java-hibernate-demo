package com.teiusko;


import javax.persistence.*;

@Entity
public class Spaceship {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ShipName;

    @OneToOne(mappedBy = "spaceship")
    private Alien alien;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShipName() {
        return ShipName;
    }

    public void setShipName(String shipName) {
        ShipName = shipName;
    }


}
