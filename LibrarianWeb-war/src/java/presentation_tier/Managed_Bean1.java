/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation_tier;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import business_tire.FacadeRemote;

/**
 *
 * @author azochniak
 */
@ManagedBean
@RequestScoped
public class Managed_Bean1 {

    @EJB
    private FacadeRemote facade;

    private DataModel items;

    /**
     * Creates a new instance of Managed_Bean1
     */
    public Managed_Bean1() {
    }

    public String store_data() {
        try {
//            facade.add_titles();
//            facade.add_books();
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
}
