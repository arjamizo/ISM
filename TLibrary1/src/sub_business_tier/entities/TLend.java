/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sub_business_tier.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *  Note fluent interface in setters. 
 * @author artur
 */
@Entity
@Table(name = "LEND")
public class TLend implements Serializable {
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @ManyToOne
    private TUser user;
    @ManyToOne
    private TBook book;

    public TBook getBook() {
        return book;
    }

    public TLend setBook(TBook book) {
        this.book = book;
        return this;
    }

    public TUser getUser() {
        return user;
    }

    public TLend setUser(TUser user) {
        this.user = user;
        return this;
    }

    @Override
    public String toString() {
        return "TLend{" + "id=" + id + ", user=" + user + ", book=" + book + '}';
    }

}
