package org.unibl.etf.users;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "korisnik")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generisanje ID-a
    @Column(name = "IdKorisnika")
    private int id;

    @Column(name = "Ime")
    @JsonProperty("name")
    private String name;

    @Column(name = "Prezime")
    @JsonProperty("surname")
    private String surname;

    @Column(name = "KorisnickoIme")
    @JsonProperty("username")
    private String username;

    @Column(name = "UkupnoBodova")
    @JsonProperty("points")
    private int points;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    @Column(name = "SaltedHashLozinka")
    @JsonProperty("password")
    private String password;

    @Column(name = "Svijet")
    @JsonProperty("world")  // Eksplicitno mapiranje JSON polja "world" na polje "Svijet"
    private String world;

    @Column(name = "Karakter")
    @JsonProperty("character")
    private String character;

    public User(){

    }

    public String getCharacter() {
        return character;
    }

    public User(String username, String firstName, String lastName, String world, String character, String password){
        this.username = username;
        this.name = firstName;
        this.surname = lastName;
        this.world = world;
        this.character = character;
        this.password = password;
    }

    public String getUsername() {return username; }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setUsername(String username) {this.username = username;}


    public String getName() {
        return name;
    }

    public void setName(String firstName) {this.name = firstName;}
    public String getSurname() {return surname;}
    public void setSurname(String lastName) {this.surname = lastName;}

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", points='" + points + '\'' +
                ", password='" + password + '\'' +
                ", world='" + world + '\'' +
                ", character='" + character + '\'' +
                '}';
    }
}