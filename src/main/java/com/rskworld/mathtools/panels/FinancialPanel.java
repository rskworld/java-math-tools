/**
 * Project: Java Basic Math Tools
 * Developer: Molla Samser
 * Designer & Tester: Rima Khatun
 * Website: https://rskworld.in
 * Year: 2026
 */

package com.rskworld.mathtools.panels;

import com.rskworld.mathtools.utils.ThemeManager;
import javax.swing.*;
import java.awt.*;

public class FinancialPanel extends JPanel {
    private JTextField principalField, rateField, timeField, resultArea;
    private JComboBox<String> toolType;

    public FinancialPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents();
    }

    private void initComponents() {
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        
        inputPanel.add(new JLabel("Calculation Type:"));
        toolType = new JComboBox<>(new String[]{"EMI Calculator", "Simple Interest", "Compound Interest"});
        inputPanel.add(toolType);

        inputPanel.add(new JLabel("Principal Amount:"));
        principalField = new JTextField();
        inputPanel.add(principalField);

        inputPanel.add(new JLabel("Annual Interest Rate (%):"));
        rateField = new JTextField();
        inputPanel.add(rateField);

        inputPanel.add(new JLabel("Time Period (Years):"));
        timeField = new JTextField();
        inputPanel.add(timeField);

        JButton calcBtn = new JButton("Calculate");
        calcBtn.addActionListener(e -> calculate());
        inputPanel.add(calcBtn);

        add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextField();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.BOLD, 16));
        resultArea.setBorder(BorderFactory.createTitledBorder("Result Breakdown"));
        add(resultArea, BorderLayout.CENTER);
        ThemeManager.applyTheme(this);
    }

    private void calculate() {
        try {
            double p = Double.parseDouble(principalField.getText());
            double r = Double.parseDouble(rateField.getText());
            double t = Double.parseDouble(timeField.getText());
            String type = (String) toolType.getSelectedItem();

            if (type.equals("EMI Calculator")) {
                double monthlyRate = r / (12 * 100);
                double months = t * 12;
                double emi = (p * monthlyRate * Math.pow(1 + monthlyRate, months)) / (Math.pow(1 + monthlyRate, months) - 1);
                double totalPayment = emi * months;
                resultArea.setText(String.format("Monthly EMI: %.2f | Total Pay: %.2f", emi, totalPayment));
            } else if (type.equals("Simple Interest")) {
                double interest = (p * r * t) / 100;
                resultArea.setText(String.format("Interest: %.2f | Total Amount: %.2f", interest, p + interest));
            } else {
                double amount = p * Math.pow(1 + (r / 100), t);
                resultArea.setText(String.format("Compound Interest: %.2f | Total Amount: %.2f", amount - p, amount));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input");
        }
    }
}
