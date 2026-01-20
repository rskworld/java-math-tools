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

public class BMIPanel extends JPanel {
    private JTextField heightField, weightField, resultLabel;

    public BMIPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeManager.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        initComponents();
    }

    private void initComponents() {
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setOpaque(false);

        inputPanel.add(new JLabel("Weight (kg):"));
        weightField = new JTextField();
        inputPanel.add(weightField);

        inputPanel.add(new JLabel("Height (cm):"));
        heightField = new JTextField();
        inputPanel.add(heightField);

        JButton calcBtn = new JButton("Calculate BMI");
        calcBtn.addActionListener(e -> calculateBMI());
        inputPanel.add(calcBtn);

        add(inputPanel, BorderLayout.NORTH);

        resultLabel = new JTextField();
        resultLabel.setEditable(false);
        resultLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        resultLabel.setHorizontalAlignment(JTextField.CENTER);
        resultLabel.setBorder(BorderFactory.createTitledBorder("Your BMI Status"));
        add(resultLabel, BorderLayout.CENTER);

        ThemeManager.applyTheme(this);
    }

    private void calculateBMI() {
        try {
            double w = Double.parseDouble(weightField.getText());
            double h = Double.parseDouble(heightField.getText()) / 100.0;
            double bmi = w / (h * h);
            
            String status;
            if (bmi < 18.5) status = "Underweight";
            else if (bmi < 25) status = "Normal";
            else if (bmi < 30) status = "Overweight";
            else status = "Obese";

            resultLabel.setText(String.format("BMI: %.1f (%s)", bmi, status));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers");
        }
    }
}
