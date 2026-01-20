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

public class ConstantsPanel extends JPanel {
    public ConstantsPanel() {
        setLayout(new BorderLayout());
        setBackground(ThemeManager.BACKGROUND);
        initComponents();
    }

    private void initComponents() {
        String[] columns = {"Constant", "Symbol", "Value", "Description"};
        Object[][] data = {
            {"Pi", "π", "3.1415926535...", "Ratio of circumference to diameter"},
            {"Euler's Number", "e", "2.718281828...", "Base of natural logarithms"},
            {"Golden Ratio", "φ", "1.618033988...", "Divine proportion"},
            {"Light Speed", "c", "299,792,458 m/s", "Speed of light in vacuum"},
            {"Gravity", "g", "9.80665 m/s²", "Earth's surface gravity"},
            {"Planck's Constant", "h", "6.626e-34 J·s", "Quantum of action"},
            {"Avogadro Number", "Na", "6.022e23 mol⁻¹", "Particles in one mole"}
        };

        JTable table = new JTable(data, columns);
        table.setBackground(ThemeManager.PANEL_BACKGROUND);
        table.setForeground(ThemeManager.TEXT_PRIMARY);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        
        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);
        
        ThemeManager.applyTheme(this);
    }
}
