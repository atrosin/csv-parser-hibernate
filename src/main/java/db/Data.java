package db;

import javax.persistence.*;

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
    @Lob
    @Column(name = "E", columnDefinition = "BLOB")
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
    public static  class Builder{
        private String firstName;
        private String lastName;
        private String email;
        private String sex;
        private String imagePath;
        private String payment;
        private String amount;
        private String isActive;
        private String isSuccessful;
        private String city;

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setSex(String sex) {
            this.sex = sex;
            return this;
        }

        public Builder setImagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        public Builder setPayment(String payment) {
            this.payment = payment;
            return this;
        }

        public Builder setAmount(String amount) {
            this.amount = amount;
            return this;
        }

        public Builder setIsActive(String isActive) {
            this.isActive = isActive;
            return this;
        }

        public Builder setIsSuccessful(String isSuccessful) {
            this.isSuccessful = isSuccessful;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }
        public Data build(){
            Data data = new Data();
            data.firstName = this.firstName;
            data.lastName  = this.lastName;
            data.email = this.email;
            data.sex = this.sex;
            data.imagePath = this.imagePath;
            data.payment = this.payment;
            data.amount = this.amount;
            data.isActive = this.isActive;
            data.isSuccessful = this.isSuccessful;
            data.city = this.city;
            return data;
        }
    }
}
