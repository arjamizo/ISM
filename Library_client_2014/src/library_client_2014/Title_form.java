/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package library_client_2014;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Title_form extends JPanel implements ActionListener {

    Client client;
    JLabel ltitle = new JLabel("Title");
    JTextField title = new JTextField(30);
    JLabel lauthor = new JLabel("Author");
    JTextField author = new JTextField(30);
    JLabel lISBN = new JLabel("ISBN");
    JTextField ISBN = new JTextField(30);
    JLabel lpublisher = new JLabel("Publisher");
    JTextField publisher = new JTextField(30);
    JLabel lactor = new JLabel("Actor");
    JTextField actor = new JTextField(30);
    JButton add_title = new JButton("Add title");

    Title_form(Client client) {
        this.client = client;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(ltitle);
        add(title);
        add(lauthor);
        add(author);
        add(lISBN);
        add(ISBN);
        add(lpublisher);
        add(publisher);
        add(lactor);
        add(actor);
        add_title.addActionListener(this);
        add(add_title);

    }

    public void actionPerformed(ActionEvent evt) {
        String[] data = form_title();
        if (data == null) {
            return;
        }
        client.getFacade().add_title_book(data);
    }

    public String[] form_title() {
        if (content_validate(title) == null) {
            return null;
        }
        if (content_validate(ISBN) == null) {
            return null;
        }
        if (content_validate(publisher) == null) {
            return null;
        }
        if (content_validate(author) == null) {
            return null;
        }
        String what_title_book_type;
        if (actor.getText().equals("")) {
            what_title_book_type = "1";
        } else {
            what_title_book_type = "3";
        }
        String data[] = {what_title_book_type, (String) author.getText(),
            (String) title.getText(), (String) ISBN.getText(),
            (String) publisher.getText(), (String) actor.getText()};
        return data;
    }

    public String content_validate(JTextField val) {
        String s = val.getText();
        if (s.equals("")) {
            JOptionPane.showMessageDialog(this, "required value");
            return null;
        } else {
            s = s.replaceAll(" ", "_");
            title.setText(s);
            return s;
        }
    }
}
