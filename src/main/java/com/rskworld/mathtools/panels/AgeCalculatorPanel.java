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
import java.time.LocalDate;
import java.time.Period;

public class AgeCalculatorPanel extends JPanel {
    private JComboBox<Integer> day, month, year;
    private JTextArea resultArea;

    public AgeCalculatorPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeManager.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        initComponents();
    }

    private void initComponents() {
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setOpaque(false);

        day = new JComboBox<>();
        for(int i=1; i<=31; i++) day.addItem(i);
        
        month = new JComboBox<>();
        for(int i=1; i<=12; i++) month.addItem(i);

        year = new JComboBox<>();
        int currentYear = LocalDate.now().getYear();
        for(int i=currentYear; i>=1900; i--) year.addItem(i);

        inputPanel.add(new JLabel("Day:"));
        inputPanel.add(day);
        inputPanel.add(new JLabel("Month:"));
        inputPanel.add(month);
        inputPanel.add(new JLabel("Year:"));
        inputPanel.add(year);

        JButton calcBtn = new JButton("Calculate Age");
        calcBtn.addActionListener(e -> calculate());
        inputPanel.add(calcBtn);

        add(inputPanel, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        ThemeManager.applyTheme(this);
    }

    private void calculate() {
        try {
            LocalDate birthDate = LocalDate.of((Integer)year.getSelectedItem(), (Integer)month.getSelectedItem(), (Integer)day.getSelectedItem());
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(birthDate, currentDate);

            resultArea.setText(String.format("Age: %d Years, %d Months, %d Days", 
                period.getYears(), period.getMonths(), period.getDays()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Date Selected");
        }
    }
}
