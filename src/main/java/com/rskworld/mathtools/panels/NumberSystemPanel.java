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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.math.BigInteger;

public class NumberSystemPanel extends JPanel {
    private JTextField decimalField, binaryField, hexField, octalField;
    private boolean isUpdating = false;

    public NumberSystemPanel() {
        setLayout(new GridLayout(4, 2, 20, 20));
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        initComponents();
    }

    private void initComponents() {
        add(new JLabel("Decimal (Base 10):"));
        decimalField = new JTextField();
        add(decimalField);

        add(new JLabel("Binary (Base 2):"));
        binaryField = new JTextField();
        add(binaryField);

        add(new JLabel("Hexadecimal (Base 16):"));
        hexField = new JTextField();
        add(hexField);

        add(new JLabel("Octal (Base 8):"));
        octalField = new JTextField();
        add(octalField);

        addDocumentListeners();
        ThemeManager.applyTheme(this);
    }

    private void addDocumentListeners() {
        decimalField.getDocument().addDocumentListener(new BaseDocumentListener(10, decimalField));
        binaryField.getDocument().addDocumentListener(new BaseDocumentListener(2, binaryField));
        hexField.getDocument().addDocumentListener(new BaseDocumentListener(16, hexField));
        octalField.getDocument().addDocumentListener(new BaseDocumentListener(8, octalField));
    }

    private void updateAll(String value, int fromBase) {
        if (isUpdating || value.isEmpty()) return;
        isUpdating = true;
        try {
            BigInteger num = new BigInteger(value, fromBase);
            if (fromBase != 10) decimalField.setText(num.toString(10));
            if (fromBase != 2) binaryField.setText(num.toString(2));
            if (fromBase != 16) hexField.setText(num.toString(16).toUpperCase());
            if (fromBase != 8) octalField.setText(num.toString(8));
        } catch (NumberFormatException e) {
            // Ignore partial/invalid input during typing
        }
        isUpdating = false;
    }

    private class BaseDocumentListener implements DocumentListener {
        private int base;
        private JTextField field;

        public BaseDocumentListener(int base, JTextField field) {
            this.base = base;
            this.field = field;
        }

        @Override public void insertUpdate(DocumentEvent e) { update(); }
        @Override public void removeUpdate(DocumentEvent e) { update(); }
        @Override public void changedUpdate(DocumentEvent e) { update(); }

        private void update() {
            SwingUtilities.invokeLater(() -> updateAll(field.getText(), base));
        }
    }
}
