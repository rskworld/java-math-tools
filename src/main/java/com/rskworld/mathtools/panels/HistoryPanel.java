/**
 * Project: Java Basic Math Tools
 * Developer: Molla Samser
 * Designer & Tester: Rima Khatun
 * Website: https://rskworld.in
 * Year: 2026
 */

package com.rskworld.mathtools.panels;

import com.rskworld.mathtools.utils.ThemeManager;
import com.rskworld.mathtools.utils.HistoryManager;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HistoryPanel extends JPanel {
    private JList<String> historyList;
    private DefaultListModel<String> listModel;

    public HistoryPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents();
    }

    private void initComponents() {
        listModel = new DefaultListModel<>();
        historyList = new JList<>(listModel);
        historyList.setFont(new Font("Monospaced", Font.PLAIN, 14));
        
        add(new JScrollPane(historyList), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.addActionListener(e -> refreshHistory());
        btnPanel.add(refreshBtn);

        JButton clearBtn = new JButton("Clear History");
        clearBtn.addActionListener(e -> {
            HistoryManager.getInstance().clearHistory();
            refreshHistory();
        });
        btnPanel.add(clearBtn);

        add(btnPanel, BorderLayout.SOUTH);
        
        refreshHistory();
        ThemeManager.applyTheme(this);
    }

    public void refreshHistory() {
        listModel.clear();
        List<String> history = HistoryManager.getInstance().getHistory();
        for (int i = history.size() - 1; i >= 0; i--) {
            listModel.addElement(history.get(i));
        }
    }
}
