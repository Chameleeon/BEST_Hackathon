package org.unibl.etf.challenge;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "izazov")
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generisanje ID-a
    @Column(name = "IdIzazova")
    private int id;

    @Column(name = "Naziv")
    @JsonProperty("name")
    private String name;

    @Column(name = "Opis")
    @JsonProperty("caption")
    private String caption;

    @Column(name = "VrijemeOd")
    @JsonProperty("timeFrom")
    private Timestamp timeFrom;

    @Column(name = "VrijemeDo")
    @JsonProperty("timeUntil")
    private Timestamp timeUntil;

    @Column(name = "BrojBodova")
    @JsonProperty("points")
    private int points;

    public Challenge(){
    }

    public Challenge(String name, String caption, Timestamp timeFrom, Timestamp timeUntil, int points){
        this.name = name;
        this.caption = caption;
        this.timeFrom = timeFrom;
        this.timeUntil = timeUntil;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCaption() {
        return caption;
    }

    public Timestamp getTimeFrom() {
        return timeFrom;
    }

    public Timestamp getTimeUntil() {
        return timeUntil;
    }

    public int getPoints() {
        return points;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setTimeFrom(Timestamp timeFrom) {
        this.timeFrom = timeFrom;
    }

    public void setTimeUntil(Timestamp timeUntil) {
        this.timeUntil = timeUntil;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", caption='" + caption + '\'' +
                ", timeFrom='" + timeFrom + '\'' +
                ", timeUntil='" + timeUntil + '\'' +
                ", points='" + points + '\'' +
                '}';
    }
}