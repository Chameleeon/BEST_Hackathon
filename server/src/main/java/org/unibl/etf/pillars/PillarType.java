package org.unibl.etf.pillars;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "vrsta_stuba")
public class PillarType{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generisanje ID-a
    @Column(name = "IdVrste")
    private int id;

    @Column(name = "NazivVrste")
    @JsonProperty("typeName")
    private String typeName;

    @Column(name = "IdGrckogBoga")
    @JsonProperty("greekGodId")
    private int greekGodId;

    public PillarType(){
    }

    public PillarType(String typeName, int greekGodId){
        this.typeName = typeName;
        this.greekGodId = greekGodId;
    }

    public int getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGreekGodId() {
        return greekGodId;
    }

    public void setGreekGodId(int greekGodId) {
        this.greekGodId = greekGodId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "PillarType{" +
                "id='" + id + '\'' +
                ", typeName='" + typeName + '\'' +
                ", greekGod='" + greekGodId + '\'' +
                '}';
    }
}