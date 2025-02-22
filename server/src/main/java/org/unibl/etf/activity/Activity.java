package org.unibl.etf.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "izazov")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generisanje ID-a
    @Column(name = "IdAktivnosti")
    private int id;

    @Column(name = "VrijemeAktivnosti")
    @JsonProperty("time")
    private Timestamp time;

    @Column(name = "BrojBodova")
    @JsonProperty("points")
    private int points;

    @Column(name = "IdVrste")
    @JsonProperty("pillarId")
    private Integer pillarId;

    @Column(name = "IdIzazova")
    @JsonProperty("challengeId")
    private Integer challengeId;

    @Column(name = "IdKorisnika")
    @JsonProperty("userId")
    private Integer userId;

    public Activity(){
    }

    public Activity(int id, Timestamp time, int points, Integer pillarId, Integer challengeId, Integer userId) {
        this.id = id;
        this.time = time;
        this.points = points;
        this.pillarId = pillarId;
        this.challengeId = challengeId;
        this.userId = userId;
    }

    public Activity(int id, Timestamp time, int points, Integer pillarId, Integer userId) {
        this.id = id;
        this.time = time;
        this.points = points;
        this.pillarId = pillarId;
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPillarId(Integer pillarId) {
        this.pillarId = pillarId;
    }

    public void setChallengeId(Integer challengeId) {
        this.challengeId = challengeId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public Timestamp getTime() {
        return time;
    }

    public int getPoints() {
        return points;
    }

    public Integer getPillarId() {
        return pillarId;
    }

    public Integer getChallengeId() {
        return challengeId;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", points='" + points + '\'' +
                ", pillarId='" + pillarId + '\'' +
                ", userId='" + userId + '\'' +
                ", challengeId='" + challengeId + '\'' +
                '}';
    }
}