/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package library_client_2014;

import sub_business_tier.UnaryOperator;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import library_client_2014.Book_form.MyTableModel;
import plpwr.JComboBoxDemo;
import business_tire.FacadeRemote;
import sub_business_tier.TFactory;
import sub_business_tier.entities.TBook;

/**
 *
 * @author Deift
 */
public class Borrowing_form extends javax.swing.JPanel {

    /**
     * Creates new form Borrowing
     */
    Client client;
    private BorrowingTable model = new BorrowingTable();
    int row;
    JComboBoxDemo extendedComboBox;
    String selectedISBN;
    String selectedNumber;
    String actor=null;
    public static final String NEW_USER = "Create new user...";
    public FacadeRemote getFacade() {
        return client.getFacade();
    }
    public Borrowing_form(Client client) {
        this.client = client;
        initComponents();
        
        jComboBox1.setEditable(true);
            // change the editor's document == override IMPLEMENTATION
        extendedComboBox = new JComboBoxDemo(jComboBox1);
        
        jButton1.addActionListener(new ActionListener() {
                
                private JComboBoxDemo comboBox;
                public void actionPerformed(ActionEvent arg0) {
                    System.out.println("selected"+comboBox.getSelectedText());
                    String client;
                    try {
                        client=comboBox.getSelectedText();
                        if(client.equals(AVAILABLE)) {
                            getFacade().returnBook(new String[]{actor.equals("")?"1":"2",selectedISBN,actor}, new String[]{"1",selectedNumber,"0"});
                        } else {
                            getFacade().borrowBook(new String[]{actor.equals("")?"1":"2",selectedISBN,actor}, new String[]{"1",selectedNumber,"0"}, client);
                        }
                        table_content();
                        model.fireTableCellUpdated(row, 6);
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
        jTable1.getSelectionModel().addListSelectionListener(new Book_form.RowListener(new UnaryOperator() {

            @Override
            public Object call(Object param) {
                    int rowidb=row;
                    row=jTable1.getSelectionModel().getLeadSelectionIndex();
                    if(row<0) row=rowidb;
                    System.out.println("row:"+row);
                    assert model==jTable1.getModel();
                    selectedISBN=model.getISBN(row);
                    selectedNumber=model.getNumber(row);
                    actor=model.getActor(row);
                    jComboBox1.removeAllItems();
                    extendedComboBox.addItem(AVAILABLE, true);
                    List<String> clients=getFacade().getClients();
                    for (String client : clients) {
                        assert jTable1.getModel()==model;
                        extendedComboBox.addItem(client, model.getClient(row).equals(client));
                    }
                    extendedComboBox.addItem(NEW_USER, false);
                    return null;
                }
        }));
    }
    private static final String AVAILABLE = "Available";
    
    static protected class BorrowingTable extends AbstractTableModel {
        
        private List<String> columnNames = Arrays.asList(new String[]{
            "Number", 
            "Publisher",
            "ISBN",
            "Title",
            "Author",
            "Actor",
            "Borrowed by",
            "Borrowed until"});
        private Object[][] data;
        
        private List<String> getColumns() {
            return columnNames;
        }

        public void setData(Object[][] val) {
            data = val;
            fireTableDataChanged();
        }

        public int getColumnCount() {
            return columnNames.size();
        }

        public int getRowCount() {
            if(data==null) return 0;
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames.get(col);
        }

        @Override
        public Object getValueAt(int row, int col) {
            try {
                return data[row][col];
            } catch (Exception ex) {
                return "";
            }
        }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            if (col < 0) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }

        private String getClient(int row) {
            int idx = getColumns().indexOf("Borrowed by");
            return getValueAt(row, idx).toString();
        }

        private String getNumber(int row) {
            int idx = getColumns().indexOf("Number");
            return getValueAt(row, idx).toString();
        }

        private String getISBN(int row) {
            int idx = getColumns().indexOf("ISBN");
            return getValueAt(row, idx).toString();
        }

        private String getActor(int row) {
            int idx = getColumns().indexOf("Actor");
            String actor = getValueAt(row, idx).toString();
            return actor.equals("")?null:actor;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        table_content();
    }
    
    
    void table_content() 
    {
        FacadeRemote facade = client.getFacade();
        Object[][] titles = facade.getBooks(null);
        model.setData(titles);
//        jTable1.tableChanged(jTable1.getModel());
        jTable1.setPreferredScrollableViewportSize(jTable1.getPreferredSize());
        jTable1.setFillsViewportHeight(true);
        jTable1.repaint();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Borrow");

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(273, 273, 273))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
