/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library_client_2014;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import sub_business_tier.FacadeInterface;
import sub_business_tier.TFacade;

/**
 *
 * @author Zofia
 */
public class Client implements ActionListener {
    private static FacadeInterface facadeStatic;

    public static void setFacadeStatic(FacadeInterface facadeStatic) {
        Client.facadeStatic = facadeStatic;
    }

    private static FacadeInterface getStaticFacade() {
        return facadeStatic;
    }

    JPanel cards; //a panel that uses CardLayout
    final static String SEARCH = "Search books";
    final static String TITLE = "Title form";
    final static String BOOK = "Book form";
    final static String LOAN = "Loan book";
    final static String BORROWING = "Borrowing books";
    FacadeInterface facade = null;

    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        menu = new JMenu("A Menu");
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

        menuItem = new JMenuItem(LOAN);
        menuItem.setMnemonic(KeyEvent.VK_L);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menuItem = new JMenuItem(SEARCH);
        menuItem.setMnemonic(KeyEvent.VK_E);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menuItem = new JMenuItem(BORROWING);
        menuItem.setMnemonic(KeyEvent.VK_E);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        

        menu.addSeparator();

        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        menuItem = new JMenuItem(SEARCH);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        submenu.add(menuItem);

        menuItem = new JMenuItem(SEARCH);
        menuItem.addActionListener(this);
        submenu.add(menuItem);
        
      
        
        
        
        menu.add(submenu);

        //Build second menu in the menu bar.
        menu = new JMenu("Another Menu");
        menu.setMnemonic(KeyEvent.VK_N);
        menuBar.add(menu);

        return menuBar;
    }

    public FacadeInterface getFacade() {
        return facade;
    }

    public void setFacade(FacadeInterface facade) {
        this.facade = facade;
    }

    public Container createContentPane() {
        //Create the content-pane-to-be.

        Card0 card0 = new Card0(this);
        Title_form card1 = new Title_form(this);
        Book_form card2 = new Book_form(this);
        Loan_form card3 = new Loan_form(this);
        Borrowing_form card4 = new Borrowing_form(this);
        
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(card0, SEARCH);
        cards.add(card1, TITLE);
        cards.add(card2, BOOK);
        cards.add(card3, LOAN);
        cards.add(card4, BORROWING);

        JPanel p1 = new JPanel();

        p1.add(cards, BorderLayout.CENTER);
        return p1;
    }

    public void actionPerformed(ActionEvent e) {

        JMenuItem source = (JMenuItem) (e.getSource());
        CardLayout cl = (CardLayout) (cards.getLayout());
        if (source.getText().equals(TITLE)) {
            cl.show(cards, TITLE);
        } else if (source.getText().equals(BOOK)) {
            //((Book_form)(cards.getComponent(2))).table_content(); //Actually only here could be called table_content, but this depends how often we refresh data. 
            cl.show(cards, BOOK);
        } else if (source.getText().equals(SEARCH)) {
            cl.show(cards, SEARCH);
        } else if (source.getText().equals(LOAN)) {
            cl.show(cards, LOAN);
        } else if (source.getText().equals(BORROWING)) {
            cl.show(cards, BORROWING);

        }
    }
    

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
        if(getStaticFacade()==null) {
            setFacadeStatic(new TFacade());
        }
        Client demo = new Client();
        demo.setFacade(getStaticFacade());
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
            }
        });
    }
}
