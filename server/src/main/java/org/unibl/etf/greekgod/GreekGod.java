package org.unibl.etf.greekgod;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "grcki_bog")
public class GreekGod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generisanje ID-a
    @Column(name = "IdGrckogBoga")
    private int id;

    @Column(name = "Ime")
    @JsonProperty("name")
    private String name;

    @Column(name = "Opis")
    @JsonProperty("caption")
    private String caption;

    public GreekGod(){
    }

    public GreekGod(int id, String name, String caption) {
        this.id = id;
        this.name = name;
        this.caption = caption;
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", caption='" + caption + '\'' +
                '}';
    }
}