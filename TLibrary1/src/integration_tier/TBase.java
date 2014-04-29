/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integration_tier;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import sub_business_tier.TFacade;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TTitle_book;

/**
 *
 * @author azochniak
 */
public class TBase {

    private TTitle_bookController titleJpaController;
    private TBookController bookJpaController;
    private TFacade facade;
    private TTitle_book titles[];
    private TBook books[];
    
    public void setEm(Object em) {
        titleJpaController.setEm(em);
        bookJpaController.setEm(em);
    }

    public TBase(TFacade facade_) {
        facade = facade_;
        titleJpaController = new TTitle_bookController();
        bookJpaController = new TBookController();
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
        facade.update_data(titles, books);
    }

    public void update_titles() throws Exception {
        titles = (TTitle_book[]) titleJpaController.getTTitle_books_();
    }

    public void update_books() throws Exception {
        books = (TBook[]) bookJpaController.getTBooks_();;
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

}
