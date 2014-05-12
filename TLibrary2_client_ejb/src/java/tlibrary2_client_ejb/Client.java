/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tlibrary2_client_ejb;

import business_tier.FacadeRemote;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class Client implements ActionListener {

    @EJB
    private static FacadeRemote facade;

    JPanel cards;
    final static String LEND = "Borrowing form";
    final static String TITLE = "Title form";
    final static String BOOK = "Book form";
    private String EXAMPLE_DATA = "Fill with example data";
    private String SAVE = "Save to database";
    private String LOAD = "Load from database";
    private String DB_UTILS = "Database commands";

    public Client() {

    }

    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

        menuItem = new JMenuItem(TITLE, KeyEvent.VK_T);
        menuItem.setMnemonic(KeyEvent.VK_T); //used constructor instead
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem(BOOK);
        menuItem.setMnemonic(KeyEvent.VK_B);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem(LEND);
        menuItem.setMnemonic(KeyEvent.VK_O);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menu.addSeparator();

        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem(LEND);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        submenu.add(menuItem);

        menuItem = new JMenuItem(LEND);
        menuItem.addActionListener(this);
        submenu.add(menuItem);

        menu.add(submenu);

        menu = new JMenu(DB_UTILS);
        menu.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener(this);
        menuBar.add(menu);
        
        menuItem = new JMenuItem(LOAD);
        menuItem.setMnemonic(KeyEvent.VK_L);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem(SAVE);
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem(EXAMPLE_DATA);
        menuItem.setMnemonic(KeyEvent.VK_E);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
//        menu.add(submenu);

        return menuBar;
    }

    public FacadeRemote getFacade() {
        return facade;
    }

    public static void setFacade(FacadeRemote facade_) {
        facade = facade_;
    }

    public Container createContentPane() {

        Borrowing_form card0 = new Borrowing_form(this);
        Title_form card1 = new Title_form(this);
        Book_form card2 = new Book_form(this);

        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card0, LEND);
        cards.add(card1, TITLE);
        cards.add(card2, BOOK);

        JPanel p1 = new JPanel();

        p1.add(cards, BorderLayout.CENTER);
        return p1;
    }

    public void actionPerformed(ActionEvent e) {
        try {
        JMenuItem source = (JMenuItem) (e.getSource());
        CardLayout cl = (CardLayout) (cards.getLayout());
        if (source.getText().equals(TITLE)) {
            cl.show(cards, TITLE);
        } else if (source.getText().equals(BOOK)) {
            cl.show(cards, BOOK);
        } else if (source.getText().equals(LEND)) {
            cl.show(cards, LEND);
        } else if (source.getText().equals(EXAMPLE_DATA)) {
            getFacade().exampleData();
        } else if (source.getText().equals(SAVE)) {
            LOG.info("Saving to DB...");
            getFacade().store_data();
            getFacade().update_data();
        } else if (source.getText().equals(LOAD)) {
            LOG.info("Loading from DB...");
            getFacade().update_data();
        }
        } catch (Exception ex) {
            ex.printStackTrace();
//            LOG.log(Level.WARNING, ex.getLocalizedMessage(), ex);
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private static final Logger LOG = Logger.getLogger(Client.class.getName());

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MenuDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 460);
        //Create and set up the content pane.
        Client demo = new Client();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());

        //Display the window.
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        java.awt.EventQueue.invokeLater(new Runnable() {
            //javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
                //java.rmi.MarshalException will be never sent here.
            }
        });
    }
}
