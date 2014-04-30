/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tlibrary2_client_ejb;

import business_tier.FacadeRemote;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import plpwr.JComboBoxDemo;

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
        table.setModel(model);
        jButton1.addActionListener(new ActionListener() {
                
                private JComboBoxDemo comboBox;
                public void actionPerformed(ActionEvent arg0) {
                    System.out.println("selected"+comboBox.getSelectedText());
                    String client;
                    try {
                        client=comboBox.getSelectedText();
                        if(client.equals(AVAILABLE)) {
                            String[] title = new String[]{(actor == null || actor.equals(""))?"0":"2",selectedISBN,actor};
                            FacadeRemote facade = getFacade();
                            facade.returnBook(title, new String[]{"1",selectedNumber,"0"});
                        } else {
                            getFacade().add_client(client);
                            getFacade().borrowBook(
                                    new String[]{(actor == null || actor.equals(""))?"0":"2",selectedISBN,actor}
                                    , new String[]{"1",selectedNumber,"0"}
                                    , client);
                        }
                        table_content();
//                        model.fireTableCellUpdated(row, 6);
                        model.fireTableDataChanged();
                    } catch (Exception e) {
                        System.err.println("some excep, probably is not a number");
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(jComboBox1, e, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                public ActionListener init(JComboBoxDemo combo) {
                    this.comboBox=combo;
                    return this;
                }
            }.init(extendedComboBox));
        
        table.getSelectionModel().addListSelectionListener(new RowListener(new UnaryOperator() {

            @Override
            public Object call(Object param) {
                    int rowidb=row;
                    row=table.getSelectionModel().getLeadSelectionIndex();
                    if(row<0) row=rowidb;
                    assert model==table.getModel();
                    System.out.println("row:"+row);
                    selectedISBN=model.getISBN(row);
                    selectedNumber=model.getNumber(row);
                    actor=model.getActor(row);
                    jComboBox1.removeAllItems();
                    extendedComboBox.addItem(AVAILABLE, true);
                    List<String> clients=getFacade().getClients();
                    for (String client : clients) {
                        assert table.getModel()==model;
                        extendedComboBox.addItem(client, model.getClient(row).equals(client));
                    }
                    extendedComboBox.addItem(NEW_USER, false);
                    return null;
                }
        }));
    }
    private static final String AVAILABLE = "Available";
    
    private class RowListener implements ListSelectionListener {

        private RowListener(UnaryOperator unaryOperator) {
            unaryOperator2 = unaryOperator;
        }
        private final UnaryOperator unaryOperator2;

        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
            unaryOperator2.call(row);
            table_content();
        }
    }
    
    static protected interface UnaryOperator {

            public Object call(Object param);
    }
    
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
        Object[][] titles = facade.getBooksWithBorrower();
        model.setData(titles);
//        jTable1.tableChanged(jTable1.getModel());
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        table.repaint();
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
        table = new javax.swing.JTable();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Borrow");

        table.setAutoCreateRowSorter(true);
        table.setModel(model);
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(288, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(273, 273, 273))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
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
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
