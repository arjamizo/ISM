/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library_client_2014;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import library_client_2014.Book_form.MyTableModel;
import library_client_2014.Book_form.RowListener;

/**
 *
 * @author Zofia
 */
public class Card0 extends JPanel{
    MyTableModel model;
    JTable table;
    public Card0() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JButton button = new JButton("Long-Named Button 4");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
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
