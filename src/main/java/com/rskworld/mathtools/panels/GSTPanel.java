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

public class GSTPanel extends JPanel {
    private JTextField amountField, gstRateField, totalField, gstAmountField;

    public GSTPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeManager.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        initComponents();
    }

    private void initComponents() {
        JPanel grid = new JPanel(new GridLayout(4, 2, 10, 10));
        grid.setOpaque(false);

        grid.add(new JLabel("Initial Amount:"));
        amountField = new JTextField();
        grid.add(amountField);

        grid.add(new JLabel("GST Rate (%):"));
        gstRateField = new JTextField("18");
        grid.add(gstRateField);

        grid.add(new JLabel("GST Amount:"));
        gstAmountField = new JTextField();
        gstAmountField.setEditable(false);
        grid.add(gstAmountField);

        grid.add(new JLabel("Total Amount:"));
        totalField = new JTextField();
        totalField.setEditable(false);
        grid.add(totalField);

        add(grid, BorderLayout.CENTER);

        JButton calcBtn = new JButton("Calculate GST");
        calcBtn.addActionListener(e -> calculate());
        add(calcBtn, BorderLayout.SOUTH);

        ThemeManager.applyTheme(this);
    }

    private void calculate() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            double rate = Double.parseDouble(gstRateField.getText());
            double gst = (amount * rate) / 100;
            gstAmountField.setText(String.format("%.2f", gst));
            totalField.setText(String.format("%.2f", amount + gst));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input");
        }
    }
}
