/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pwr;

import business_tier.FacadeRemote;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import tlibrary2_client_ejb.Client;

/**
 *
 * @author azochniak
 */
public class Main extends Client {
    FacadeRemote facade;

    public FacadeRemote getFacade() {
        return facade;
    }

    public Main() throws Throwable {
        init();
    }
    
    public static void main(String[] args) {
        
        final Main demo;
        try {
            demo = new Main();
            java.awt.EventQueue.invokeLater(new Runnable() {
            //javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    demo.createAndShowGUI(demo);
                }
            });
        } catch (Throwable ex) {
            if(ex.getMessage().contains("NoClass") && 
                ex.getMessage().contains("com.sun.enterprise.naming.SerialInitContextFactory")
                ) {
                JOptionPane.showInputDialog(null
                        , "add $GLASSFISH_HOME/modules/glassfish-naming.jar to libraries.\nexecute ln -s ~/glassfish-4.0/glassfish ./glassfish in project dir."
                        , "Error"
                        , JOptionPane.ERROR_MESSAGE
                        , null
                        , null
                        , "ln -s `find / -iname glassfish-naming.jar` ./glassfish-naming.jar");
            } if(ex.toString().contains("acquire")) {
                JOptionPane.showInputDialog(null
                        , "add $GLASSFISH_HOME/lib/appserv-gf.jar to libraries.\nexecute ln -s ~/glassfish-4.0/glassfish ./glassfish in project dir for having relative access to glassfish lib"
                        , "Error"
                        , JOptionPane.QUESTION_MESSAGE
                        , null
                        , null
                        , "ln -s `find / -iname appserv-gf.jar` ./appserv-gf.jar");
            } else {
//                pw.write(ex.toString());
                StringWriter writer = new StringWriter();
                PrintWriter pw = new PrintWriter(writer);
                ex.printStackTrace();
                ex.printStackTrace(pw);
                JOptionPane.showMessageDialog(null, writer);
            }
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    private static void createAndShowGUI(Main demo) {
        //Create and set up the window.
        JFrame frame = new JFrame("MenuDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 460);
        //Create and set up the content pane.
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());
        
        JMenu menu = new JMenu("Server");
        menu.setMnemonic(KeyEvent.VK_R);
        
        JMenuItem menuItem = new JMenuItem("Reinitialize connection with remote EJB");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            Main main;

            public ActionListener setMain(Main main) {
                this.main = main;
                return this;
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    main.init();
                } catch (Throwable ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex);
                }
            }
        }.setMain(demo));
        frame.getJMenuBar().add(menu);
        
        //Display the window.
        frame.setVisible(true);
    }
    final public void init() throws Throwable {
            Properties props = new Properties();
            props.setProperty("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            props.setProperty("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

            props.setProperty("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
            // port can be found in server-config>ORB
            props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");

            InitialContext ic = new InitialContext(props);
            facade = (FacadeRemote)ic.lookup("java:global/TLibrary2_EE/TLibrary2_EE-ejb/Facade");
            //facade.remoteBusinessMethod();
    }
}
