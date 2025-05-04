package org.todos;
import java.awt.*;
import javax.swing.*;

//TODO: Fix double math
//TODO: Fix make text field bigger
//TODO: Add delete button for single character deletions
//TODO:

public class CalculatorApp {

    public static void main(String[] args) {
        CalcLogic calcLogic = new CalcLogic();


        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        JTextField display = new JTextField();
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,4));

        String[] buttons = { "7", "8","9","+","C",
                             "4","5","6","-",
                             "1","2","3","*",
                             "0",".","=","/"};
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(e ->{
                String cmd = e.getActionCommand();
                boolean empty = display.getText().isEmpty();
                display.setText(display.getText() + " " + cmd);

                if(empty && (cmd.equals("*") || cmd.equals("/") || cmd.equals("+") || cmd.equals("-")) ) {
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
                else if (cmd.equals("C")) {
                    display.setText("");
                }
            });
            panel.add(button);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
