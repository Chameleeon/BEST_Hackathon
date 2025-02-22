package org.unibl.etf.userPillar;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "korisnikov_stub")
public class UserPillar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generisanje ID-a
    @Column(name = "IdKorisnikovogStuba")
    private int id;

    @Column(name = "PoslednjaAktivnost")
    @JsonProperty("lastActivity")
    private Timestamp lastActivity;

    @Column(name = "BrojBodova")
    @JsonProperty("points")
    private int points;

    @Column(name = "IdVrste")
    @JsonProperty("pillarId")
    private Integer pillarId;

    @Column(name = "IdKorisnika")
    @JsonProperty("userId")
    private Integer userId;

    @Column(name = "StanjeStuba")
    @JsonProperty("condition")
    private Integer condition;

    public int getId() {
        return id;
    }

    public UserPillar() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Timestamp lastActivity) {
        this.lastActivity = lastActivity;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Integer getPillarId() {
        return pillarId;
    }

    public void setPillarId(Integer pillarId) {
        this.pillarId = pillarId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCondition() {
        return condition;
    }

    public void setCondition(Integer condition) {
        this.condition = condition;
    }

    public UserPillar(int id, Timestamp lastActivity, int points, Integer pillarId, Integer userId, Integer condition) {
        this.id = id;
        this.lastActivity = lastActivity;
        this.points = points;
        this.pillarId = pillarId;
        this.userId = userId;
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", pillarId='" + pillarId + '\'' +
                ", points='" + points + '\'' +
                ", condition='" + condition + '\'' +
                ", lastActivity='" + lastActivity + '\'' +
                '}';
    }
}