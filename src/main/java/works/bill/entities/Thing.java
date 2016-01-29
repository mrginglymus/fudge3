/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.entities;

import javax.persistence.*;

/**
 *
 * @author bill
 */
@Entity
public class Thing {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long Id;
    
    private String secretText;

    private long Count = 0;
    
    @ManyToOne(fetch = javax.persistence.FetchType.EAGER)
    private User owner;

    public long getId() {
        return Id;
    }

    public String getSecretText() {
        return secretText;
    }

    public void setSecretText(String secretText) {
        this.secretText = secretText;
    }
    
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public long getCount() {
        return Count;
    }

    public void setCount(long count) {
        Count = count;
    }
}
