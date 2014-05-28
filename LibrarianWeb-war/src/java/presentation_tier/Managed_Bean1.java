/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation_tier;

import business_tier.FacadeRemote;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.faces.view.facelets.FaceletException;
import javax.inject.Inject;

/**
 *
 * @author azochniak
 */
@ManagedBean
@RequestScoped
public class Managed_Bean1 {

    @EJB//(lookup = "java:global/TLibrary2_EE/TLibrary2_EE-ejb/Facade")
    private FacadeRemote facade;

    private DataModel items,books;
    private ArrayList<ArrayList<String>> titles;
    
    String ISBN,actor,publisher,title,author,number;

    public void setNumber(String number) {
        this.number = number;
    }
    
    //how to fill: https://stackoverflow.com/tags/selectonemenu/info
    String selectedISBN;
    

    boolean borrowable;

    public boolean isBorrowable() {
        return borrowable;
    }

    public void setBorrowable(boolean borrowable) {
        this.borrowable = borrowable;
    }

    public String getSelectedISBN() {
        return selectedISBN;
    }

    public void setSelectedISBN(String selectedISBN) {
        this.selectedISBN = selectedISBN;
    }
    @PostConstruct
    public void init() {
        System.gc(); //Solution to class A cannot be cast to class A; SEVERE:   The web application [/faces] created a ThreadLocal with key of type [org.glassfish.pfl.dynamic.codegen.impl.CurrentClassLoader$1] (value [org.glassfish.pfl.dynamic.codegen.impl.CurrentClassLoader$1@249ea63a]) and a value of type [org.glassfish.web.loader.WebappClassLoader] (value [WebappClassLoader (delegate=true; repositories=WEB-INF/classes/)]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
        ISBN=Integer.toString((int) (new Random().nextFloat()*Integer.MAX_VALUE));
        actor=new String[]{"Keanu Reevs", "Jason Statham", "Arnold Achwarzeneger", "Anna Gacek", "Jaroslaw Boberek", "", "", "", ""}[new Random().nextInt(5+4)];
        publisher=new String[]{"PWM", "Helion", "Gdanski Klub Fantastyki", "OPERON", "Wydawnictwo Solaris"}[new Random().nextInt(5)];
        title=new String[]{"Czysty Kod", "Folwark Zierzecy", "Sztuka podstepu. Lamalem ludzi, nie hasla", "Orwell 1984", "Haker. Prawdziwa historia przywodcy cybermafii"}[new Random().nextInt(5)];
        author=new String[]{"Kevin Poulsen", "Kevin Mytnick", "Robert C. Martin", "Geirge Orwell", "Stefan Zeromski"}[new Random().nextInt(5)];
        number=Integer.toString((int) (new Random().nextFloat()*Integer.MAX_VALUE));
    }

    public String getNumber() {
        return number;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ArrayList<ArrayList<String>> getTitles() throws Exception {
        titles=facade.titles();
        return titles;
    }
    
    public List<SelectItem> getTitlesArr() throws Exception {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (ArrayList<String> arrayList : facade.titles()) {
            list.add(new SelectItem(arrayList.get(2)+","+arrayList.get(4), "ISBN: "+arrayList.get(2)+" "+arrayList));
            LOG.info("parsing "+arrayList+" titles"+list.listIterator().next().getValue());
        }
        return list;
    }
    

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    /**
     * Creates a new instance of Managed_Bean1
     */
    public Managed_Bean1() {
        
    }
    
    public String add_title() throws Exception {
        String t1[] = {actor.length()>0?"3":"1", author, title, ISBN, publisher, actor};
        String title = facade.addTitleBook(t1);
        LOG.info("title: "+title);
        facade.add_titles();
        return "/faces/presentation_tier_view/Show_data";
    }
    public String add_book() throws Throwable {
        LOG.info("Selected ISBN="+selectedISBN);
        assert selectedISBN!=null;
        String[] split = selectedISBN.split(",");
        String ISBN = split[0];
        String actor = split.length>1?split[1]:"";
        
        facade.addBook(new String[]{actor.length()==0?"0":"2", ISBN, actor}, new String[]{borrowable?"1":"0", number, "0"});
        try {
            facade.add_books();
        } catch (Throwable e) {
            throw new FacesException(e.getMessage());
        }
        LOG.warning("list of books.size()="+facade.getBooksWithBorrower().length);
        return "/faces/presentation_tier_view/Add_data";
    }
    
    private static final Logger LOG = Logger.getLogger(Managed_Bean1.class.getName());

    public String store_data() {
        try {
            facade.store_data();
        } catch (Exception ex) {
            Logger.getLogger(Managed_Bean1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/faces/index";
    }
    public String refresh_data() {
        try {
            facade.update_data();
        } catch (Exception ex) {
            Logger.getLogger(Managed_Bean1.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return "/faces/presentation_tier_view/Show_data";
    }
    

    public String show_data() {
        create_DataModel();
        return "/faces/presentation_tier_view/Show_data";
    }

    public DataModel create_DataModel() {
        try {
            facade.update_data();
            final ListDataModel titles = new ListDataModel(facade.titles());
            LOG.info("facadetitles="+facade.titles()+" titles="+titles.getRowData());
//            return new ListDataModel(new ArrayList<>());
            return titles;
//            return new ListDataModel(new ArrayList<>());
        } catch (IllegalArgumentException e) {
            return new ListDataModel();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public DataModel getItems() {
        if (items == null) {
            System.out.println("Model");
            items = create_DataModel();
            LOG.info("items="+items);
        }
        return items;
    }

    public void setItems(
            DataModel items) {
        this.items = items;
    }
    public DataModel getBooks() {
        if (books == null) {
            System.out.println("Model");
            books = new ListDataModel(facade.books());
        }
        return books;
    }

    public void setBooks(
            DataModel items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Managed_Bean1{" + "facade=" + facade + ", items=" + items + ", books=" + books + ", titles=" + titles + ", ISBN=" + ISBN + ", actor=" + actor + ", publisher=" + publisher + ", title=" + title + ", author=" + author + ", number=" + number + '}';
    }
    
    
}
