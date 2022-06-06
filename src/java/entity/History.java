/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author 37255
 */
@Entity
public class History implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Client client;
    @OneToOne
    private Product product;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;
    
    public History(){
        
    }
    
    public History(Client client, Product product, Date date){
        this.client = client;
        this.product = product;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 27 * hash + Objects.hashCode(this.id);
        hash = 27 * hash + Objects.hashCode(this.product);
        hash = 27 * hash + Objects.hashCode(this.client);
        hash = 27 * hash + Objects.hashCode(this.date);
        
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
        final History other = (History) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "entity.History[" + "id=" + id 
                + ", client=" + client.getFirstname()
                + " " + client.getLastname()
                + ", product=" + product.getName()
                + ", product=" + product.getPrice()
                + ", date=" + date
                + " ]";
    }
    
}
