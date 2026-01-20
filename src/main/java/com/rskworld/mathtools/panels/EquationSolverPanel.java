/**
 * Project: Java Basic Math Tools
 * Developer: Molla Samser
 * Designer & Tester: Rima Khatun
 * Website: https://rskworld.in
 * Year: 2026
 */

package com.rskworld.mathtools.panels;

import com.rskworld.mathtools.utils.HistoryManager;
import com.rskworld.mathtools.utils.ThemeManager;
import javax.swing.*;
import java.awt.*;

public class EquationSolverPanel extends JPanel {
    private JTextField aField, bField, cField, resultArea;

    public EquationSolverPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents();
    }

    private void initComponents() {
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Quadratic Equation: ax² + bx + c = 0"));

        inputPanel.add(new JLabel("Value of a:"));
        aField = new JTextField();
        inputPanel.add(aField);

        inputPanel.add(new JLabel("Value of b:"));
        bField = new JTextField();
        inputPanel.add(bField);

        inputPanel.add(new JLabel("Value of c:"));
        cField = new JTextField();
        inputPanel.add(cField);

        JButton solveBtn = new JButton("Solve Equation");
        solveBtn.addActionListener(e -> solveQuadratic());
        inputPanel.add(solveBtn);

        add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextField();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.BOLD, 16));
        resultArea.setBorder(BorderFactory.createTitledBorder("Result"));
        add(resultArea, BorderLayout.CENTER);
        ThemeManager.applyTheme(this);
    }

    private void solveQuadratic() {
        try {
            double a = Double.parseDouble(aField.getText());
            double b = Double.parseDouble(bField.getText());
            double c = Double.parseDouble(cField.getText());

            if (a == 0) {
                // Linear equation: bx + c = 0
                if (b == 0) {
                    resultArea.setText(c == 0 ? "Infinite solutions" : "No solution");
                } else {
                    resultArea.setText("Linear Root: x = " + (-c / b));
                }
                return;
            }

            double discriminant = b * b - 4 * a * c;
            if (discriminant > 0) {
                double r1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                double r2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                resultArea.setText(String.format("Two real roots: x1 = %.2f, x2 = %.2f", r1, r2));
            } else if (discriminant == 0) {
                double r = -b / (2 * a);
                resultArea.setText(String.format("One real root: x = %.2f", r));
            } else {
                double realPart = -b / (2 * a);
                double imagPart = Math.sqrt(-discriminant) / (2 * a);
                resultArea.setText(String.format("Complex roots: %.2f ± %.2fi", realPart, imagPart));
            }
            HistoryManager.getInstance().addEntry("Solved Quadratic: " + a + "x^2 + " + b + "x + " + c + " = 0 -> " + resultArea.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers");
        }
    }
}
