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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StatisticsPanel extends JPanel {
    private JTextArea inputArea, outputArea;

    public StatisticsPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents();
    }

    private void initComponents() {
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter numbers (separated by comma or space)"));
        inputArea = new JTextArea(5, 20);
        inputPanel.add(new JScrollPane(inputArea), BorderLayout.CENTER);
        
        JButton calcBtn = new JButton("Calculate Statistics");
        calcBtn.addActionListener(e -> calculateStats());
        inputPanel.add(calcBtn, BorderLayout.SOUTH);

        add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setBorder(BorderFactory.createTitledBorder("Statistical Analysis"));
        add(new JScrollPane(outputArea), BorderLayout.CENTER);
        ThemeManager.applyTheme(this);
    }

    private void calculateStats() {
        try {
            String text = inputArea.getText().replaceAll("[^0-9.\\s,-]", "");
            String[] tokens = text.split("[,\\s]+");
            List<Double> data = new ArrayList<>();
            for (String s : tokens) {
                if (!s.isEmpty()) data.add(Double.parseDouble(s));
            }

            if (data.isEmpty()) {
                outputArea.setText("No data provided.");
                return;
            }

            Collections.sort(data);
            double sum = 0;
            for (double d : data) sum += d;
            double mean = sum / data.size();

            double median;
            int mid = data.size() / 2;
            if (data.size() % 2 == 0) {
                median = (data.get(mid - 1) + data.get(mid)) / 2.0;
            } else {
                median = data.get(mid);
            }

            double sqSum = 0;
            for (double d : data) sqSum += Math.pow(d - mean, 2);
            double variance = sqSum / data.size();
            double stdDev = Math.sqrt(variance);

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Count: %d\n", data.size()));
            sb.append(String.format("Sorted: %s\n", data.toString()));
            sb.append(String.format("Min: %.2f\n", data.get(0)));
            sb.append(String.format("Max: %.2f\n", data.get(data.size() - 1)));
            sb.append(String.format("Sum: %.2f\n", sum));
            sb.append(String.format("Mean: %.2f\n", mean));
            sb.append(String.format("Median: %.2f\n", median));
            sb.append(String.format("Variance: %.2f\n", variance));
            sb.append(String.format("Std Deviation: %.2f\n", stdDev));

            outputArea.setText(sb.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error processing data. Check format.");
        }
    }
}
