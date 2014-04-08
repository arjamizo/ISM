/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library_client_2014;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import sub_business_tier.FacadeInterface;


public class Book_form extends JPanel implements ActionListener {

    private JTable table;
    private JTextField number;
    private JTextField period;
    private JButton add_book;
    int row;
    Client client;
    MyTableModel model;
    JComboBox books;

    public Book_form(Client client) {
        super();
        this.client = client;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        model = new MyTableModel();
       // Object[][] data = {{"", "", "", "", ""}};
      //  model.setData(data);
        table_content();
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(new RowListener(new UnaryOperator() {
            
            @Override
            public Object call(Object param) {
                row = table.getSelectionModel().getLeadSelectionIndex();
                System.out.println("selected: "+row);
//                if(row>=0) //TODO very strange bug: calling twice function
//                    print_books();
                return null;
            }
        }));

        add(new JScrollPane(table));

        JLabel lnumber = new JLabel("Numeber of a book");
        add(lnumber);
        number = new JTextField();
        add(number);
        JLabel lperiod = new JLabel("Period of a book");
        add(lperiod);
        period = new JTextField();
        add(period);
        add_book = new JButton("Add book");
        add(add_book);
        add_book.addActionListener(this);
        JLabel lbooks = new JLabel("Books");
        add(lbooks);
        books = new JComboBox();
        add(books);
    }

    String[] title() {
        String what, actor;
        actor = (String) model.getValueAt(row, 4);
        if (actor.isEmpty())//what type of title of book
        {
            what = "0";
        } else {
            what = "2";
        }
        String data[] = {what, (String) model.getValueAt(row, 1), actor};
        return data;
    }

    private void list_content(ArrayList<String> col,
            JComboBox list) {
        String s;
        list.removeAllItems();
        Iterator<String> iterator = col.iterator();
        while (iterator.hasNext()) {
            s = iterator.next();
            list.addItem(s);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        table_content();
    }

    void table_content() {
        FacadeInterface facade = client.getFacade();
        Object[][] titles = facade.gettitle_books();
        model.setData(titles);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (!table.getSelectionModel().isSelectionEmpty()) {
            String what_book_type;
            if (number.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "required value");
                return;
            }
            if (period.getText().equals("")) {
                what_book_type = "0";
            } else {
                what_book_type = "1";
            }
            String data2[] = {what_book_type, (String) number.getText(),
                (String) period.getText()};
            ArrayList<String> help3 = client.getFacade().add_book(title(), data2).getbooks();
            if (help3 != null) {
                list_content(help3, books);
            }
        }
    }

    void print_books() {

        ArrayList<String> help3 = client.getFacade().Search_title_books(title());
        if (help3 != null) {
            list_content(help3, books);
        }
    }

    static protected class RowListener implements ListSelectionListener {
        UnaryOperator callback=null;
        public RowListener(UnaryOperator call) {
            this.callback=call;
        }
        @Override
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
            //row = table.getSelectionModel().getLeadSelectionIndex();
            //print_books();
            if(callback!=null) 
                callback.call(this);
        }
    }

    static protected class MyTableModel extends AbstractTableModel {
        boolean withNumber=false;
        public MyTableModel(boolean withNumber) {
            this.withNumber=withNumber;
        }
        public MyTableModel() {
            
        }

        private String[] columnNames = {
            "Number", 
            "Publisher",
            "ISBN",
            "Title",
            "Author",
            "Actor"};
        private Object[][] data;

        public void setData(Object[][] val) {
            data = val;
            fireTableDataChanged();
        }

        public int getColumnCount() {
            return columnNames.length-(withNumber?0:1);
        }

        public int getRowCount() {
            if(data==null) return 0;
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col+(withNumber?0:1)];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        public boolean isCellEditable(int row, int col) {
            if (col < 0) {
                return false;
            } else {
                return true;
            }
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }
}
