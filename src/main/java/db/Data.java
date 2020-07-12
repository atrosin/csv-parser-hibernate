package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Data {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "A")
    private String firstName;
    @Column(name = "B")
    private String lastName;
    @Column(name = "C")
    private String email;
    @Column(name = "D")
    private String sex;
    @Column(name = "E")
    private String imagePath;
    @Column(name = "F")
    private String payment;
    @Column(name = "G")
    private String amount;
    @Column(name = "H")
    private String isActive;
    @Column(name = "I")
    private String isSuccessful;
    @Column(name = "J")
    private String city;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(String isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
