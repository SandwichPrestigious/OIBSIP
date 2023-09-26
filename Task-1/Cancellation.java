//importing necessary packages
import javax.swing.*;
import java.awt.*;//gui layout
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//class for cancellation form
public class CancellationForm extends JFrame {
    private JTextField reservationIdField;

    public CancellationForm() {
        setTitle("Cancellation Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);// basic properties of the form

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));//creating a panel for the form.

        JLabel reservationIdLabel = new JLabel("Reservation ID:");
        reservationIdField = new JTextField(20);//the reservation id is in the form of textfield.

        JButton cancelButton = new JButton("Cancel Reservation");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the reservation ID from the reservationIdField
                String reservationId = reservationIdField.getText();

                // Perform cancellation logic here (e.g., delete the reservation from the database)
                if (isValidReservationId(reservationId)) {
                    // Delete the reservation with the given ID
                    // Display a confirmation message
                    JOptionPane.showMessageDialog(null, "Reservation canceled successfully!");
                } else {
                    // Display an error message if the reservation ID is invalid
                    JOptionPane.showMessageDialog(null, "Invalid Reservation ID. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //adding all panels to the form.    
        panel.add(reservationIdLabel);
        panel.add(reservationIdField);
        panel.add(new JLabel()); // Empty space
        panel.add(cancelButton);

        add(panel);
        setVisible(true);
    }

    private boolean isValidReservationId(String reservationId) {
        // Implement validation logic here (e.g., check if the ID exists in the database)
        // Return true if valid, false otherwise
        return false; // Replace with your validation code
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CancellationForm());
    }
}
