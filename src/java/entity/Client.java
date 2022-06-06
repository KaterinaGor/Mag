/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author 37255
 */
@Entity
public class Client implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String phone;
    private int money;
    
    public Client(){
        
    }
    
    public Client(String firstname, String lastname, String phone, int money){
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.money = money;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 21 * hash + Objects.hashCode(this.id);
        hash = 21 * hash + Objects.hashCode(this.firstname);
        hash = 21 * hash + Objects.hashCode(this.lastname);
        hash = 21 * hash + Objects.hashCode(this.phone);
        hash = 21 * hash + this.money;
        return hash;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        
        if (this.money != other.money) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        return true;
    }  


    @Override
    public String toString() {
        return "Client[" + "id=" + id 
                + ", firstname=" + firstname
                + ", lastname=, " + lastname
                + ", phone=" + phone
                + ", money=" + money
                + " ]";
    }

    
    
}
