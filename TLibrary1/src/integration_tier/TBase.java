/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integration_tier;

import integration_tier.jpa.TLendJpaController;
import integration_tier.jpa.TUserJpaController;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
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
        bookJpaController = new TBookController();
        titleJpaController = new TTitle_bookController();
        tLendJpaController = new TLendJpaController();
        tUserJpaController = new TUserJpaController();
        try {
            update_data();
        } catch (javax.persistence.PersistenceException e) {
            LOG.warning("Probably EntityManager was not loaded properly.");
        } catch (Exception ex) {
//            throw new RuntimeException(ex);
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
            help3.add(t.getAuthor());
            help3.add(t.getTitle());
            help3.add(t.getISBN());
            help3.add(t.getPublisher());
            help3.add(t.getActor());
            help2.add(help3);
            LOG.info("help3="+help3);
        }
        LOG.info("help2="+help2);
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

    public List<ArrayList<String>> booksByTitle(String[] titleForFactory) {
        
        ArrayList<ArrayList<String>> books = new ArrayList<ArrayList<String>>();
        for (TTitle_book tTitle_book : facade.getmTitle_books()) {
            for (TBook tBook : tTitle_book.getBooks()) {
                String[] book = new String[]{
                  (tBook.getmTitle_book().getActor().length()==0)?"2":"3"
                , tBook.getmTitle_book().getAuthor()
                , tBook.getmTitle_book().getTitle()
                , tBook.getmTitle_book().getISBN()
                , tBook.getmTitle_book().getPublisher()
                , tBook.getmTitle_book().getActor()
                , Integer.toString(tBook.getNumber())
                };
                //https://code.google.com/p/functionaljava/source/browse/artifacts/3.0/demo/1.5/
//                final ArrayList<String> bookList = new ArrayList(Arrays.asList(book));
//                final ArrayList<String> bookList2 = new ArrayList(Arrays.asList(titleForFactory));
//                List<P2<String,String>> ret = list();
//                final fj.data.List<String> l1 = Java.ArrayList_List().f(bookList);
//                final fj.data.List<String> l2 = Java.ArrayList_List().f(bookList2);
//                fj.data.List.zip(
//                        l1, l2, 
//                        ret
//                        );
                if(titleForFactory!=null) {
                    List<Boolean> equals=new LinkedList();
                    for (int i = 0; i < book.length; i++) {
                        if(book[i]!=null) {
    //                        try {
    //                            org.apache.commons.lang3.StringUtils.getLevenshteinDistance(book[i], titleForFactory[i]);
    //                        } catch (Exception e) {
    //                            System.err.println("Probably there is no Apache Commons Lang library, download it at https://commons.apache.org/proper/commons-lang/download_lang.cgi. Currently using java.lang implementatoin");
    //                        }
                            equals.add(book[i].equals(titleForFactory[i]));
                        }
                    }
                    try {
                        if(com.google.common.collect.Iterables.all(equals, com.google.common.base.Predicates.alwaysTrue()))
                            books.add(new java.util.ArrayList(java.util.Arrays.asList(book)));
                    } catch (NoClassDefFoundError ex) {
                        System.err.println("Probably there is no Guava library, using java.lang implementatoin");
                        throw new RuntimeException(ex);
                    }
                } else {
                    books.add(new java.util.ArrayList(java.util.Arrays.asList(book)));
                }
            }
        }
        return books;
    }
    public List<ArrayList<String>> books() {
        final List<ArrayList<String>> books = booksByTitle(null);
        LOG.info("books="+books);
        return books;
    }

    public String[] getTitleByISBN(String ISBN) {
        try {
            for (ArrayList<String> title : titles()) {
                if(title.get(1).equals(ISBN)) {
                    String[] array = title.toArray(new String[0]);
                    ArrayList ret = new ArrayList();
                    ret.add(title.get(4).length()>0?"3":"2");
                    ret.addAll(title);
                    return (String[]) ret.toArray(new String[0][0]);
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    public void delete_borrow(String bookNumber) {
        try {
            tLendJpaController.removeBorrow(bookNumber);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
