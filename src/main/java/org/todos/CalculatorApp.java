package org.todos;
import java.awt.*;
import javax.swing.*;

public class CalculatorApp {

    public static void main(String[] args) {
        CalcLogic calcLogic = new CalcLogic();


        JFrame frame = new JFrame("Calculator"); // Create a frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Make sure frame can be closed
        frame.setSize(500, 500); // Frame size
        JTextField display = new JTextField(); // Create display text field
        display.setEditable(false);
        display.setPreferredSize(new Dimension(0, 50)); // Sets the size of the text field for expression
        display.setFont(new Font("Arial", Font.PLAIN, 22)); // Make text visible
        frame.add(display, BorderLayout.NORTH); // Adds the display, and puts

        // Creates a new panel
        // Then sets it to a grid layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,4));

        String[] buttons = {"C","^","<-","%",
                            "7", "8","9","+",
                            "4","5","6","-",
                            "1","2","3","*",
                            "0",",","=","/"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));

            button.addActionListener(e ->{
                String cmd = e.getActionCommand();
                boolean empty = display.getText().isEmpty();
                display.setText(display.getText() + cmd);

                if(empty && (cmd.equals("*") || cmd.equals("/") || cmd.equals("+") || cmd.equals("-") || cmd.equals("^")) ) {
                    display.setText("ERR CAN'T OPEN HAVE OPERATOR FIRST!");
                    Timer timer = new Timer(3000, event -> display.setText(""));
                    timer.setRepeats(false);
                    timer.start();
                }
                if(cmd.equals("=")) {
                    double result = calcLogic.evaluate(display.getText());
                    if (result == (int) result) {
                        display.setText(Integer.toString((int) result));
                    }
                    else {
                        display.setText(Double.toString(result));
                    }
                }
                if (cmd.equals("C")) {
                    display.setText("");
                }
                if(cmd.equals("<-")) {
                    String currentText = display.getText();
                    if (empty) {
                        display.setText("");
                    } else if(!currentText.isEmpty()) {
                        display.setText(currentText.substring(0, currentText.length() - 3));
                    }
                }

            });
            panel.add(button);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}

