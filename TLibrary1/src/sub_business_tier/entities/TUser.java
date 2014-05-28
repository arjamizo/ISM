/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sub_business_tier.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import sub_business_tier.TFactory;

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
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private java.util.List<TLend> lends = new java.util.ArrayList();

    public List<TLend> getLends() {
        return lends;
    }

    public void setLends(List<TLend> lends) {
        this.lends = lends;
    }
    
    public boolean borrow_book(TBook book) {
        if (getLends().size()>5) return false;
        TLend lend = find_lend_of_book(book); 
        if(lend!=null) {
            getLends().remove(lend);
            lend.setUser(null);
        }
        lend = new TLend();
        lend.setBook(book);
        lend.setUser(this);
        getLends().add(lend);
        book.setLend(lend);
        book.setPeriod(TFactory.mdays("7")); //by default book is borrowed for 7 days
        return true;
    }
    private static final Logger LOG = Logger.getLogger(TUser.class.getName());
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
        getLends().add(lend);
        return lend;
    }

    public TLend return_book(TBook book) {
        TLend lend = book.getLend();//find_lend_of_book(book);
        if(lend!=null) {
//            book.getLend().setUser(null);
            book.setLend(null);
            book.setPeriod(new Date());
          //  lend.setUser(null);
          getLends().remove(lend);
        } else {
            throw new RuntimeException("no such a lend for book "+book);
        }
        return lend;
    }

    private TLend find_lend_of_book(TBook book) {
        for (TLend tLend : lends) {
            if(tLend.getBook().equals(book)) {
                return tLend;
            }
        }
        return null;
    }
    
}
