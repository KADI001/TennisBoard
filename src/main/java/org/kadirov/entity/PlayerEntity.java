package org.kadirov.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Players")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public PlayerEntity() {
    }

    public PlayerEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public PlayerEntity(int id) {
        this.id = id;
    }

    public PlayerEntity(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerEntity copy(){
        return new PlayerEntity(id, name);
    }
}
