/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tlib_client_se;

import business_tier.FacadeRemote;
import javax.swing.JFrame;
import sub_business_tier.TFacade;
import tlibrary2_client_ejb.Client;

/**
 *
 * @author artur
 */
public class Main extends Client {

    TFacade facade = new TFacade();

    @Override
    public FacadeRemote getFacade() {
        return new FacadeAdapter(facade);
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            //javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().createAndShowGUI();
            }
        });
    }
        
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("MenuDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 460);
        //Create and set up the content pane.
        Client demo = new Main();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());

        //Display the window.
        frame.setVisible(true);
    }
    
}
