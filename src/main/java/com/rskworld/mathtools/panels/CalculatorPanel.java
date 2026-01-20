/**
 * Project: Java Basic Math Tools
 * Developer: Molla Samser
 * Designer & Tester: Rima Khatun
 * Website: https://rskworld.in
 * Year: 2026
 */

package com.rskworld.mathtools.panels;

import com.rskworld.mathtools.utils.ThemeManager;
import com.rskworld.mathtools.utils.HistoryManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorPanel extends JPanel {
    private JTextField display;
    private double firstNum = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    public CalculatorPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeManager.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents();
    }

    private void initComponents() {
        display = new JTextField("0");
        display.setFont(new Font("Monospaced", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        display.setPreferredSize(new Dimension(0, 50));
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "√", "x²", "±"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("SansSerif", Font.BOLD, 18));
            btn.addActionListener(new ButtonClickListener());
            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.CENTER);
        ThemeManager.applyTheme(this);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
                if (startNewNumber) {
                    display.setText(command);
                    startNewNumber = false;
                } else {
                    if (command.equals(".") && display.getText().contains(".")) return;
                    display.setText(display.getText() + command);
                }
            } else if (command.equals("C")) {
                display.setText("0");
                firstNum = 0;
                operator = "";
                startNewNumber = true;
            } else if (command.equals("=")) {
                calculate();
                operator = "";
                startNewNumber = true;
            } else if (command.equals("√")) {
                double val = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.sqrt(val)));
                startNewNumber = true;
            } else if (command.equals("x²")) {
                double val = Double.parseDouble(display.getText());
                display.setText(String.valueOf(val * val));
                startNewNumber = true;
            } else if (command.equals("±")) {
                double val = Double.parseDouble(display.getText());
                display.setText(String.valueOf(val * -1));
            } else {
                if (!operator.isEmpty()) {
                    calculate();
                }
                firstNum = Double.parseDouble(display.getText());
                operator = command;
                startNewNumber = true;
            }
        }

        private void calculate() {
            double secondNum = Double.parseDouble(display.getText());
            double result = 0;
            switch (operator) {
                case "+": result = firstNum + secondNum; break;
                case "-": result = firstNum - secondNum; break;
                case "*": result = firstNum * secondNum; break;
                case "/": 
                    if (secondNum != 0) result = firstNum / secondNum;
                    else { display.setText("Error"); return; }
                    break;
                default: result = secondNum;
            }
            String record = String.format("%.2f %s %.2f = %.2f", firstNum, operator, secondNum, result);
            HistoryManager.getInstance().addEntry(record);
            display.setText(String.valueOf(result));
        }
    }
}
