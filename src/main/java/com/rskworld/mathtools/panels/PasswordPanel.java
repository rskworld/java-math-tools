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
import java.security.SecureRandom;

public class PasswordPanel extends JPanel {
    private JTextField lengthField, passwordField;
    private JCheckBox upperCheck, lowerCheck, digitCheck, specialCheck;

    public PasswordPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeManager.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        initComponents();
    }

    private void initComponents() {
        JPanel configPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        configPanel.setOpaque(false);

        configPanel.add(new JLabel("Password Length:"));
        lengthField = new JTextField("12");
        configPanel.add(lengthField);

        upperCheck = new JCheckBox("Include Uppercase", true);
        lowerCheck = new JCheckBox("Include Lowercase", true);
        digitCheck = new JCheckBox("Include Digits", true);
        specialCheck = new JCheckBox("Include Special Chars", true);

        configPanel.add(upperCheck);
        configPanel.add(lowerCheck);
        configPanel.add(digitCheck);
        configPanel.add(specialCheck);

        JButton genBtn = new JButton("Generate Password");
        genBtn.addActionListener(e -> generate());
        configPanel.add(genBtn);

        add(configPanel, BorderLayout.NORTH);

        passwordField = new JTextField();
        passwordField.setEditable(false);
        passwordField.setFont(new Font("Monospaced", Font.BOLD, 16));
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setBorder(BorderFactory.createTitledBorder("Generated Result"));
        add(passwordField, BorderLayout.CENTER);

        ThemeManager.applyTheme(this);
    }

    private void generate() {
        try {
            int len = Integer.parseInt(lengthField.getText());
            String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String lower = "abcdefghijklmnopqrstuvwxyz";
            String digits = "01234567839";
            String special = "!@#$%^&*()-_=+[]{}";
            
            StringBuilder pool = new StringBuilder();
            if (upperCheck.isSelected()) pool.append(upper);
            if (lowerCheck.isSelected()) pool.append(lower);
            if (digitCheck.isSelected()) pool.append(digits);
            if (specialCheck.isSelected()) pool.append(special);

            if (pool.length() == 0) {
                JOptionPane.showMessageDialog(this, "Select at least one option");
                return;
            }

            SecureRandom random = new SecureRandom();
            StringBuilder pass = new StringBuilder();
            for (int i = 0; i < len; i++) {
                pass.append(pool.charAt(random.nextInt(pool.length())));
            }
            passwordField.setText(pass.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid length");
        }
    }
}
