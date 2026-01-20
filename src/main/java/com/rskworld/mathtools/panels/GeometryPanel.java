/**
 * Project: Java Basic Math Tools
 * Developer: Molla Samser
 * Designer & Tester: Rima Khatun
 * Website: https://rskworld.in
 * Year: 2026
 */

package com.rskworld.mathtools.panels;

import javax.swing.*;
import java.awt.*;

public class GeometryPanel extends JPanel {
    private JComboBox<String> shapeBox;
    private JTextField input1, input2, resultArea;
    private JLabel lbl1, lbl2;

    public GeometryPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents();
    }

    private void initComponents() {
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.add(new JLabel("Select Shape:"));
        shapeBox = new JComboBox<>(new String[]{"Circle", "Rectangle", "Square", "Triangle", "Sphere", "Cylinder"});
        shapeBox.addActionListener(e -> updateInputs());
        inputPanel.add(shapeBox);

        lbl1 = new JLabel("Radius:");
        inputPanel.add(lbl1);
        input1 = new JTextField();
        inputPanel.add(input1);

        lbl2 = new JLabel("Height (Optional):");
        inputPanel.add(lbl2);
        input2 = new JTextField();
        inputPanel.add(input2);

        JButton calcBtn = new JButton("Calculate Area/Volume");
        calcBtn.addActionListener(e -> calculate());
        inputPanel.add(calcBtn);

        add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextField();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.BOLD, 14));
        resultArea.setBorder(BorderFactory.createTitledBorder("Results"));
        add(resultArea, BorderLayout.CENTER);

        updateInputs();
    }

    private void updateInputs() {
        String shape = (String) shapeBox.getSelectedItem();
        lbl2.setVisible(false);
        input2.setVisible(false);
        
        switch (shape) {
            case "Circle": lbl1.setText("Radius:"); break;
            case "Rectangle": 
                lbl1.setText("Length:"); 
                lbl2.setText("Width:"); 
                lbl2.setVisible(true); 
                input2.setVisible(true); 
                break;
            case "Square": lbl1.setText("Side:"); break;
            case "Triangle": 
                lbl1.setText("Base:"); 
                lbl2.setText("Height:"); 
                lbl2.setVisible(true); 
                input2.setVisible(true); 
                break;
            case "Sphere": lbl1.setText("Radius:"); break;
            case "Cylinder": 
                lbl1.setText("Radius:"); 
                lbl2.setText("Height:"); 
                lbl2.setVisible(true); 
                input2.setVisible(true); 
                break;
        }
        revalidate();
    }

    private void calculate() {
        try {
            double v1 = Double.parseDouble(input1.getText());
            double v2 = input2.isVisible() ? Double.parseDouble(input2.getText()) : 0;
            String shape = (String) shapeBox.getSelectedItem();
            String res = "";

            switch (shape) {
                case "Circle": res = String.format("Area: %.2f | Circumference: %.2f", Math.PI * v1 * v1, 2 * Math.PI * v1); break;
                case "Rectangle": res = String.format("Area: %.2f | Perimeter: %.2f", v1 * v2, 2 * (v1 + v2)); break;
                case "Square": res = String.format("Area: %.2f | Perimeter: %.2f", v1 * v1, 4 * v1); break;
                case "Triangle": res = String.format("Area: %.2f", 0.5 * v1 * v2); break;
                case "Sphere": res = String.format("Volume: %.2f | Surface Area: %.2f", (4.0/3.0) * Math.PI * Math.pow(v1, 3), 4 * Math.PI * v1 * v1); break;
                case "Cylinder": res = String.format("Volume: %.2f | Lateral Surface: %.2f", Math.PI * v1 * v1 * v2, 2 * Math.PI * v1 * v2); break;
            }
            resultArea.setText(res);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Check your inputs!");
        }
    }
}
