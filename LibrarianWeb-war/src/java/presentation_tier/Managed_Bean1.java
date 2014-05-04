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
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

/**
 *
 * @author azochniak
 */
@ManagedBean
@RequestScoped
public class Managed_Bean1 {

    @EJB
    private FacadeRemote facade;

    private DataModel items,books;
    private ArrayList<ArrayList<String>> titles;
    
    String ISBN,actor,publisher,title,author,number;
    @PostConstruct
    public void init() {
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
    
    public List<SelectItem> getSelectTitles() throws Exception {
        List<SelectItem> list = new ArrayList<SelectItem>();
        for (ArrayList<String> arrayList : facade.titles()) {
            list.add(new SelectItem(arrayList.get(1), "ISBN: "+arrayList.get(1)));
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
    private static final Logger LOG = Logger.getLogger(Managed_Bean1.class.getName());

    public String store_data() {
        try {
            facade.add_titles();
            facade.add_books();
        } catch (Exception e) {
        }
        return "/faces/index2";
    }

    public String show_data() {
        create_DataModel();
        return "/faces/presentation_tier_view/Show_data";
    }

    public DataModel create_DataModel() {
        try {
            return new ListDataModel(facade.titles());
//            return new ListDataModel(new ArrayList<>());
        } catch (Exception e) {
            System.out.println("Blad "+e.getMessage());
            return null;
        }
    }
    
    public DataModel getItems() {
        if (items == null) {
            System.out.println("Model");
            items = create_DataModel();
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
