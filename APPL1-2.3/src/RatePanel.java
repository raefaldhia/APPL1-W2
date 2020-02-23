// ******************************************************************
// RatePanel.java
//
// Panel for a program that converts different currencies to
// U.S. Dollars
// ******************************************************************
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RatePanel extends JPanel {
    private double[] rate; // exchange rates

    private String[] currencyName;

    private JLabel result;

    private JTextField input;

    private JComboBox comboBox;

    // ------------------------------------------------------------
    // Sets up a panel to convert cost from one of 6 currencies
    // into U.S. Dollars. The panel contains a heading, a text
    // field for the cost of the item, a combo box for selecting
    // the currency, and a label to display the result.
    // ------------------------------------------------------------
    public RatePanel() {
        JLabel title = new JLabel("How much is that in dollars?");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Helvetica", Font.BOLD, 20));

        // Set up the arrays for the currency conversions
        currencyName = new String[]{"Select the currency..",
                "European Euro", "Canadian Dollar",
                "Japanese Yen", "Australian Dollar",
                "Indian Rupee", "Mexican Peso"};

        rate = new double[]{0.0, 1.2103, 0.7351,
                0.0091, 0.6969,
                0.0222, 0.0880};

        input = new JTextField(5);

        result = new JLabel(" ------------ ");

        comboBox = new JComboBox(currencyName);

        comboBox.addActionListener(new ComboListener());

        input.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                comboBox.setSelectedIndex(0);
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {
                comboBox.setSelectedIndex(0);

            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {
                comboBox.setSelectedIndex(0);
            }
        });

        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));

        listPane.add(title);

        JPanel controlPane = new JPanel();
        controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.LINE_AXIS));
        controlPane.add(input);
        controlPane.add(result);
        controlPane.add(comboBox);

        listPane.add(controlPane);

        add(listPane);
    }

    // ******************************************************
    // Represents an action listener for the combo box.
    // ******************************************************
    private class ComboListener implements ActionListener {
        // --------------------------------------------------
        // Determines which currency has been selected and
        // the value in that currency then computes and
        // displays the value in U.S. Dollars.
        // --------------------------------------------------
        public void actionPerformed(ActionEvent event) {
            if (input.getText().length() > 0) {
                int index = comboBox.getSelectedIndex();

                double value = Double.parseDouble(input.getText());

                result.setText(currencyName[index] +
                        " = " + value * rate[index] + " U.S. Dollars");
            } else {
                result.setText(" ------------ ");
            }
        }
    }
}