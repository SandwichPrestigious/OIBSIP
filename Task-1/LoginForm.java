//packages imported for the form
import javax.swing.*;
import java.awt.*;//for gui
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//creating a class for the form
public class LoginForm extends JFrame {
        private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginForm() {
        setTitle("LoginForm");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);// basic properties of the form

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));//creating a panel for the form

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");//the labels for the form.

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);//the username and passwords are in the form of textfields.

        JButton loginButton = new JButton("Login");//creating the logun button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform user authentication here
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty space
        panel.add(loginButton);

        add(panel);
        setVisible(true);// adding all panels to the form.
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm());
    }
}
