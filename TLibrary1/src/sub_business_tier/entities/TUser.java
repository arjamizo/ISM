/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sub_business_tier.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author artur
 */

@Entity
@Table(name = "USERS")
public class TUser implements Serializable {

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
    
    @Column (name = "login")
    private String login;
    /*
    @OneToMany(cascade = CascadeType.PERSIST)
    private java.util.List<TLend> lends;

    public List<TLend> getLends() {
        return lends;
    }

    public void setLends(List<TLend> lends) {
        this.lends = lends;
    }
    */
    public String getLogin() {
        return login;
    }

    public TUser setLogin(String login) {
        this.login = login;
        return this;
    }

    @Override
    public String toString() {
        return "TUser{" + "id=" + id + ", login=" + login + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TUser other = (TUser) obj;
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        return true;
    }

    public TLend borrow(TBook book) {
        TLend lend = new TLend();
        lend.setUser(this);
        lend.setBook(book);
        return lend;
    }
    
    
    
}
