package integration_tier;

import integration_tier.util.EntityManagerProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import sub_business_tier.TFacade;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TTitle_book;

public class TBase {

    private TTitle_bookController titleJpaController;
    private TBookController bookJpaController;
    private TFacade facade;
    private TTitle_book titles[];
    private TBook books[];
    public EntityManager em;

    public TBase(TFacade facade_) {
        facade = facade_;
        EntityManagerProvider emp;
        try {
            bookJpaController = new TBookController();
            titleJpaController = new TTitle_bookController();
            titleJpaController.getTTitle_books();
        } catch (Throwable e) {
            LOG.severe("Due to some problems on linux with RESOURCE_LOCAL, it is needed to uncomment @PersistanceContext annotation in Facade.java. ");
            e.printStackTrace();
            titleJpaController = new TTitle_bookControllerAnnotation(emp=new EntityManagerProvider() {

                @Override
                public EntityManager getEm() {
                    return em;
                }
            });
            bookJpaController = new TBookControllerAnnotation(emp);
        }
        LOG.info("initialized");
        try {
            update_data();
        } catch (Exception e) {
            throw new RuntimeException(e);
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
