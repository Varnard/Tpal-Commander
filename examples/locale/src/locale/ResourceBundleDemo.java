package locale;

import java.awt.GridLayout;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ResourceBundleDemo {

    public static void main(String[] args) {
        Locale locale;
        locale = Locale.getDefault();
        //locale = Locale.ENGLISH;
        //locale = new Locale("EN");
    
        ResourceBundle rb = ResourceBundle.getBundle("MyResources", locale);
    
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("I18N Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2));

        final JLabel label1 = new JLabel(rb.getString("userName"));
        final JLabel label2 = new JLabel(rb.getString("password"));
        final JLabel label3 = new JLabel(rb.getString("login"));

        frame.add(label1);
        frame.add(new JTextField());
        frame.add(label2);
        frame.add(new JPasswordField());
        frame.add(label3);
    
        frame.pack();
        frame.setVisible(true);
    }
}
