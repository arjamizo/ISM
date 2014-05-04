/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation_tier;

import business_tier.FacadeRemote;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
    private List<SelectItem> selectTitles;
    
    String ISBN,actor,publisher,title,author,number;

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
    
    public String add_title() {
        String t1[] = {actor.length()>0?"3":"1", "Author1", "Title1", ISBN, "Publisher1", actor};
        String title = facade.addTitleBook(t1);
        LOG.info("title: "+title);
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
}
