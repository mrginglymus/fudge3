/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.entities;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author bill
 */
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    @Column(unique = true)
    private String username;
    
    private String password;
    
    @OneToMany(mappedBy = "owner")
    private List<Thing> things;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public long getId() {
        return id;
    }
    
    public List<Thing> getThings() {
        return things;
    }

    public void setThings(List<Thing> things) {
        this.things = things;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) { return true; }
        if (!(other instanceof User)) { return false; }

        final User user = (User) other;

        return user.getUsername().equals(username);
    }
}
