/**
 * Project: Java Basic Math Tools
 * Developer: Molla Samser
 * Designer & Tester: Rima Khatun
 * Website: https://rskworld.in
 * Year: 2026
 * Description: Main frame for the application containing the tabbed navigation.
 */

package com.rskworld.mathtools;

import com.rskworld.mathtools.panels.*;
import javax.swing.*;
import java.awt.*;

public class MathToolsFrame extends JFrame {
    
    public MathToolsFrame() {
        setTitle("Java Basic Math Tools - rskworld.in");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
    }
    
    private void initComponents() {
        JTabbedPane tabbedPane = new JTabbedPane();
        
        tabbedPane.setTabPlacement(JTabbedPane.TOP);
        
        tabbedPane.addTab("Calculator", null, new CalculatorPanel());
        tabbedPane.addTab("Unit Conv", null, new UnitConverterPanel());
        tabbedPane.addTab("Number Sys", null, new NumberSystemPanel());
        tabbedPane.addTab("Equations", null, new EquationSolverPanel());
        tabbedPane.addTab("Statistics", null, new StatisticsPanel());
        tabbedPane.addTab("Financial", null, new FinancialPanel());
        tabbedPane.addTab("Geometry", null, new GeometryPanel());
        tabbedPane.addTab("Matrix", null, new MatrixPanel());
        tabbedPane.addTab("Prime Tools", null, new PrimePanel());
        tabbedPane.addTab("BMI Calc", null, new BMIPanel());
        tabbedPane.addTab("Pass Gen", null, new PasswordPanel());
        tabbedPane.addTab("Age Calc", null, new AgeCalculatorPanel());
        tabbedPane.addTab("GST Calc", null, new GSTPanel());
        tabbedPane.addTab("Plotter", null, new PlotterPanel());
        
        HistoryPanel historyPanel = new HistoryPanel();
        tabbedPane.addTab("History", null, historyPanel);

        tabbedPane.addChangeListener(e -> {
            // Updated index: History is now 14
            if (tabbedPane.getSelectedIndex() == 14) { 
                historyPanel.refreshHistory();
            }
        });

        // Add Keyboard Shortcuts
        setupShortcuts(tabbedPane);
        
        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);
        
        // Status Bar
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        JLabel statusLabel = new JLabel(" Ready | Developed by Molla Samser (rskworld.in)");
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        statusBar.add(statusLabel, BorderLayout.WEST);
        
        JLabel yearLabel = new JLabel("2026 ");
        yearLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        statusBar.add(yearLabel, BorderLayout.EAST);
        
        add(statusBar, BorderLayout.SOUTH);

        // We already have initGlobalTheme in Main.java
        // No need to deeply re-apply theme on every frame creation
    }
    
    private void setupShortcuts(JTabbedPane tabbedPane) {
        InputMap inputMap = tabbedPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = tabbedPane.getActionMap();

        for (int i = 0; i < 15; i++) {
            final int index = i;
            String key = "F" + (i + 1);
            // Handle F10, F11, F12 specifically for KeyStroke if needed, 
            // but standard strings often work. Adding explicit mapping for safety.
            if (i >= 9) key = "F" + (i + 1); 
            inputMap.put(KeyStroke.getKeyStroke(key), key);
            actionMap.put(key, new AbstractAction() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    tabbedPane.setSelectedIndex(index);
                }
            });
        }
    }

    private Icon createIcon(String name) {
        // Placeholder for icons, can be expanded with real images later
        return null;
    }
}
