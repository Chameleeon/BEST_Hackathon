package net.etfbl.server;
import jakarta.persistence.*;

@Entity
@Table(name = "citizen")
public class User{
    @Id
    @Column(name = "Username")
    private String username;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "Address")
    private String address;
    @Column(name = "City")
    private String city;
    @Column(name = "HashPassword")
    private String password;
    @Column(name = "Email")
    private String email;
    @Column(name = "PhoneNumber")
    private String phoneNumber;

    public User(){

    }

    public User(String username, String firstName, String lastName, String address, String city){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;

    }

    public String getUsername() {return username;
    }

    public void setUsername(String username) {this.username = username;}


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {this.address = address;}

    public String getCity() {
        return this.city;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}