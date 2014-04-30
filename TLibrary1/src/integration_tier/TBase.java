/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integration_tier;

import integration_tier.jpa.TLendJpaController;
import integration_tier.jpa.TUserJpaController;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import sub_business_tier.TFacade;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TLend;
import sub_business_tier.entities.TTitle_book;
import sub_business_tier.entities.TUser;


public class TBase {

    private TTitle_bookController titleJpaController;
    private TBookController bookJpaController;
    private TLendJpaController tLendJpaController;
    private TUserJpaController tUserJpaController;
    private TFacade facade;
    private TTitle_book titles[];
    private TBook books[];
    private TLend borrows[];
    private TUser users[];
    
    public void setEm(Object em) {
        titleJpaController.setEm(em);
        bookJpaController.setEm(em);
        tLendJpaController.setEm(em);
        tUserJpaController.setEm(em);
    }

    public TBase(TFacade facade_) {
        facade = facade_;
        titleJpaController = new TTitle_bookController();
        bookJpaController = new TBookController();
        tLendJpaController = new TLendJpaController();
        tUserJpaController = new TUserJpaController();
        try {
            update_data();
        } catch (javax.persistence.PersistenceException e) {
            LOG.warning("Probably EntityManager was not loaded properly.");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    private static final Logger LOG = Logger.getLogger(TBase.class.getName());

    public void update_data() throws Exception {
        update_titles();
        update_books();

        try {
            update_users();
            update_borrows();
        } catch (Exception e) {
            e.printStackTrace();
            LOG.warning("something wrong with TLend or TUser JPA ctrls.");
        }
        facade.update_data(titles, books, borrows, users);
    }

    public void update_titles() throws Exception {
        titles = (TTitle_book[]) titleJpaController.getTTitle_books_();
    }

    public void update_books() throws Exception {
        books = (TBook[]) bookJpaController.getTBooks_();
    }

    private void update_users() {
        users = tUserJpaController.getTUsers_();
    }

    private void update_borrows() {
        borrows = tLendJpaController.getTLends_();
    }

    public void add_titles() throws Exception {
        try {
            titleJpaController.addTTitle_books(facade.getmTitle_books());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void add_books() throws Exception {
        try {
            bookJpaController.addTBooks(facade.getmTitle_books());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<ArrayList<String>> titles() throws Exception {
        List<TTitle_book> help1 = titleJpaController.getTTitle_books();
        ArrayList<ArrayList<String>> help2 = new ArrayList();
        for (TTitle_book t : help1) {
            ArrayList<String> help3 = new ArrayList();
            help3.add(t.getPublisher());
            help3.add(t.getISBN());
            help3.add(t.getTitle());
            help3.add(t.getAuthor());
            help3.add(t.getActor());
            help2.add(help3);
        }
        return help2;
    }
    
    public void add_users() throws Exception {
        try {
            tUserJpaController.addUsers(facade.getUsers());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void add_lends() throws Exception {
        try {
            tLendJpaController.addLends(facade.getBorrows());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
