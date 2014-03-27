/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package library_client_2014;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import library_client_2014.Book_form.MyTableModel;
import plpwr.JComboBoxDemo;
import sub_business_tier.FacadeInterface;

/**
 *
 * @author Deift
 */
public class Borrowing_form extends javax.swing.JPanel {

    /**
     * Creates new form Borrowing
     */
    Client client;
    private MyTableModel model = new MyTableModel();
    
    
    public Borrowing_form(Client client) {
        this.client = client;
        initComponents();
        //jTable1.setModel(model);
        jComboBox1.setEditable(true);
            // change the editor's document == override IMPLEMENTATION
        JComboBoxDemo extendedComboBox = new JComboBoxDemo(jComboBox1);
        
        jButton1.addActionListener(new ActionListener() {
                
                private JComboBoxDemo comboBox;
                public void actionPerformed(ActionEvent arg0) {
                    System.out.println("selected"+comboBox.getSelectedText());
                    try{
                    String value=comboBox.getSelectedText();
                    comboBox.removeLastItem(); //or removeItem("string")
                    comboBox.addItem(value, true);
                    System.out.println("removed "+value+" and added "+(value+1));
                    }catch(Exception e) {
                        System.err.println("some excep, probably is not a number");
                        e.printStackTrace();
                    }
                }
                public ActionListener init(JComboBoxDemo combo) {
                    this.comboBox=combo;
                    return this;
                }
            }.init(extendedComboBox));
    }

        @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        table_content();
    }
    
    
    void table_content() 
    {
        FacadeInterface facade = client.getFacade();
        Object[][] titles = facade.gettitle_books();
        System.out.println(titles.toString());
        model.setData(titles);
        jTable1.getParent().repaint();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Borrow");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
