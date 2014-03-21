/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library_client_2014;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import library_client_2014.Book_form.MyTableModel;
import library_client_2014.Book_form.RowListener;
import sub_business_tier.TFacade;

/**
 *
 * @author Zofia
 */
public class Card0 extends JPanel{
    MyTableModel model;
    JTable table;
    Client client;
    public TFacade getFacade() {
        return client.getFacade();
    }
    public Card0(Client client) {
        this.client=client;
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JButton button = new JButton("Long-Named Button 4");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        button.addActionListener(new ActionListener() {
            TFacade facade;
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("executed "+new Date());
                Logger.getLogger(Card0.class.getName()).info("executed!"+new Date().toString());
                facade.Print_books();
            }
            ActionListener init(TFacade facade) {
                this.facade=facade;
                return this;
            }
        }.init(client.getFacade()));
        add(button, c);
        
        model = new MyTableModel();
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(new RowListener(new UnaryOperator() {
            
            @Override
            public Object call(Object obj) {
                System.out.println();
                return obj;
            }
        }));
        
//        if(1==1) return;
        add(new JScrollPane(table));
    }
    
}
