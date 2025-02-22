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

    @Column(name = "IdVrste")
    @JsonProperty("pillarTypeId")
    private Integer pillarTypeId;

    @Column(name = "IdKorisnika")
    @JsonProperty("userId")
    private Integer userId;

    @Column(name = "StanjeStuba")
    @JsonProperty("condition")
    private Double condition;

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


    public Integer getPillarTypeId() {
        return pillarTypeId;
    }

    public void setPillarTypeId(Integer pillarTypeId) {
        this.pillarTypeId = pillarTypeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getCondition() {
        return condition;
    }

    public void setCondition(Double condition) {
        this.condition = condition;
    }

    public UserPillar(int id, Timestamp lastActivity, Integer pillarTypeId, Integer userId, Double condition) {
        this.id = id;
        this.lastActivity = lastActivity;
        this.pillarTypeId = pillarTypeId;
        this.userId = userId;
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Challenge{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", pillarId='" + pillarTypeId + '\'' +
                ", condition='" + condition + '\'' +
                ", lastActivity='" + lastActivity + '\'' +
                '}';
    }
}