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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnitConverterPanel extends JPanel {
    private JTextField inputField, outputField;
    private JComboBox<String> categoryCombo, fromUnitCombo, toUnitCombo;

    private final String[][] units = {
        {"Length", "Meters", "Kilometers", "Centimeters", "Millimeters", "Inches", "Feet"},
        {"Weight", "Kilograms", "Grams", "Milligrams", "Pounds", "Ounces"},
        {"Temperature", "Celsius", "Fahrenheit", "Kelvin"},
        {"Volume", "Liters", "Milliliters", "Gallons", "Cups"}
    };

    public UnitConverterPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents();
    }

    private void initComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Category
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Category:"), gbc);
        
        categoryCombo = new JComboBox<>(new String[]{"Length", "Weight", "Temperature", "Volume"});
        categoryCombo.addActionListener(e -> updateUnitCombos());
        gbc.gridx = 1;
        add(categoryCombo, gbc);

        // From
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("From:"), gbc);

        fromUnitCombo = new JComboBox<>();
        gbc.gridx = 1;
        add(fromUnitCombo, gbc);

        inputField = new JTextField(10);
        gbc.gridx = 2;
        add(inputField, gbc);

        // To
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("To:"), gbc);

        toUnitCombo = new JComboBox<>();
        gbc.gridx = 1;
        add(toUnitCombo, gbc);

        outputField = new JTextField(10);
        outputField.setEditable(false);
        gbc.gridx = 2;
        add(outputField, gbc);

        // Convert Button
        JButton convertBtn = new JButton("Convert");
        convertBtn.addActionListener(e -> performConversion());
        gbc.gridx = 1; gbc.gridy = 3;
        add(convertBtn, gbc);

        updateUnitCombos();
        ThemeManager.applyTheme(this);
    }

    private void updateUnitCombos() {
        int index = categoryCombo.getSelectedIndex();
        fromUnitCombo.removeAllItems();
        toUnitCombo.removeAllItems();
        for (int i = 1; i < units[index].length; i++) {
            fromUnitCombo.addItem(units[index][i]);
            toUnitCombo.addItem(units[index][i]);
        }
    }

    private void performConversion() {
        try {
            double val = Double.parseDouble(inputField.getText());
            String category = (String) categoryCombo.getSelectedItem();
            String from = (String) fromUnitCombo.getSelectedItem();
            String to = (String) toUnitCombo.getSelectedItem();
            
            double result = 0;
            if (category.equals("Temperature")) {
                result = convertTemperature(val, from, to);
            } else if (category.equals("Length")) {
                result = convertLength(val, from, to);
            } else if (category.equals("Weight")) {
                result = convertWeight(val, from, to);
            } else {
                result = val; // Placeholder for Volume
            }
            
            outputField.setText(String.format("%.4f", result));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number");
        }
    }

    private double convertTemperature(double val, String from, String to) {
        double celsius = val;
        if (from.equals("Fahrenheit")) celsius = (val - 32) * 5/9;
        else if (from.equals("Kelvin")) celsius = val - 273.15;

        if (to.equals("Celsius")) return celsius;
        else if (to.equals("Fahrenheit")) return celsius * 9/5 + 32;
        else if (to.equals("Kelvin")) return celsius + 273.15;
        return val;
    }

    private double convertLength(double val, String from, String to) {
        // Base Unit: Meters
        double meters = val;
        switch (from) {
            case "Kilometers": meters = val * 1000; break;
            case "Centimeters": meters = val / 100; break;
            case "Millimeters": meters = val / 1000; break;
            case "Inches": meters = val * 0.0254; break;
            case "Feet": meters = val * 0.3048; break;
        }

        switch (to) {
            case "Meters": return meters;
            case "Kilometers": return meters / 1000;
            case "Centimeters": return meters * 100;
            case "Millimeters": return meters * 1000;
            case "Inches": return meters / 0.0254;
            case "Feet": return meters / 0.3048;
            default: return meters;
        }
    }

    private double convertWeight(double val, String from, String to) {
        // Base Unit: Kilograms
        double kgs = val;
        switch (from) {
            case "Grams": kgs = val / 1000; break;
            case "Milligrams": kgs = val / 1000000; break;
            case "Pounds": kgs = val * 0.453592; break;
            case "Ounces": kgs = val * 0.0283495; break;
        }

        switch (to) {
            case "Kilograms": return kgs;
            case "Grams": return kgs * 1000;
            case "Milligrams": return kgs * 1000000;
            case "Pounds": return kgs / 0.453592;
            case "Ounces": return kgs / 0.0283495;
            default: return kgs;
        }
    }
}
