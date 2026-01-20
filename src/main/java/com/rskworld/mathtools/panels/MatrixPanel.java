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

public class MatrixPanel extends JPanel {
    private JTextField[][] matrixA = new JTextField[3][3];
    private JTextField[][] matrixB = new JTextField[3][3];
    private JTextField[][] matrixResult = new JTextField[3][3];

    public MatrixPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeManager.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents();
    }

    private void initComponents() {
        JPanel matricesContainer = new JPanel(new GridLayout(1, 3, 20, 0));
        matricesContainer.setOpaque(false);

        matricesContainer.add(createMatrixInputPanel("Matrix A", matrixA));
        matricesContainer.add(createMatrixInputPanel("Matrix B", matrixB));
        matricesContainer.add(createMatrixInputPanel("Result", matrixResult, false));

        add(matricesContainer, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.setOpaque(false);

        String[] ops = {"Add (A+B)", "Sub (A-B)", "Mul (A*B)", "Clear"};
        for (String op : ops) {
            JButton btn = new JButton(op);
            btn.addActionListener(e -> handleOperation(op));
            controlPanel.add(btn);
        }

        add(controlPanel, BorderLayout.SOUTH);
        ThemeManager.applyTheme(this);
    }

    private JPanel createMatrixInputPanel(String title, JTextField[][] fields) {
        return createMatrixInputPanel(title, fields, true);
    }

    private JPanel createMatrixInputPanel(String title, JTextField[][] fields, boolean editable) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        JLabel lbl = new JLabel(title, JLabel.CENTER);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 14));
        p.add(lbl, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(3, 3, 5, 5));
        grid.setOpaque(false);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fields[i][j] = new JTextField("0", 3);
                fields[i][j].setHorizontalAlignment(JTextField.CENTER);
                fields[i][j].setEditable(editable);
                grid.add(fields[i][j]);
            }
        }
        p.add(grid, BorderLayout.CENTER);
        return p;
    }

    private void handleOperation(String op) {
        try {
            double[][] a = getMatrixValues(matrixA);
            double[][] b = getMatrixValues(matrixB);
            double[][] res = new double[3][3];

            if (op.startsWith("Add")) {
                for(int i=0; i<3; i++) for(int j=0; j<3; j++) res[i][j] = a[i][j] + b[i][j];
            } else if (op.startsWith("Sub")) {
                for(int i=0; i<3; i++) for(int j=0; j<3; j++) res[i][j] = a[i][j] - b[i][j];
            } else if (op.startsWith("Mul")) {
                for(int i=0; i<3; i++) {
                    for(int j=0; j<3; j++) {
                        for(int k=0; k<3; k++) res[i][j] += a[i][k] * b[k][j];
                    }
                }
            } else {
                for(int i=0; i<3; i++) for(int j=0; j<3; j++) { matrixA[i][j].setText("0"); matrixB[i][j].setText("0"); matrixResult[i][j].setText("0"); }
                return;
            }
            setMatrixValues(matrixResult, res);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Matrix Input");
        }
    }

    private double[][] getMatrixValues(JTextField[][] fields) {
        double[][] vals = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) vals[i][j] = Double.parseDouble(fields[i][j].getText());
        }
        return vals;
    }

    private void setMatrixValues(JTextField[][] fields, double[][] vals) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) fields[i][j].setText(String.format("%.1f", vals[i][j]));
        }
    }
}
