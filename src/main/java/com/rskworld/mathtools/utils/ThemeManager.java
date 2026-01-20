/**
 * Project: Java Basic Math Tools
 * Developer: Molla Samser
 * Designer & Tester: Rima Khatun
 * Website: https://rskworld.in
 * Year: 2026
 */

package com.rskworld.mathtools.utils;

import java.awt.*;
import javax.swing.JComponent;
import javax.swing.UIManager;

public class ThemeManager {
    // Primary Colors - Professional Dark Theme with Red Accents (rskworld.in style)
    public static final Color BACKGROUND = new Color(20, 20, 20); // Darker
    public static final Color PANEL_BACKGROUND = new Color(30, 30, 30);
    public static final Color ACCENT_RED = new Color(255, 50, 50); // Brighter Red
    public static final Color TEXT_PRIMARY = Color.WHITE; // Absolute White
    public static final Color INPUT_BG = new Color(45, 45, 45);
    public static final Color BUTTON_BG = new Color(60, 60, 60);

    public static void initGlobalTheme() {
        UIManager.put("Panel.background", BACKGROUND);
        UIManager.put("TabbedPane.background", PANEL_BACKGROUND);
        UIManager.put("TabbedPane.foreground", TEXT_PRIMARY);
        UIManager.put("TabbedPane.selected", ACCENT_RED);
        UIManager.put("TextField.background", INPUT_BG);
        UIManager.put("TextField.foreground", TEXT_PRIMARY);
        UIManager.put("TextArea.background", INPUT_BG);
        UIManager.put("TextArea.foreground", TEXT_PRIMARY);
        UIManager.put("Label.foreground", TEXT_PRIMARY);
        UIManager.put("Button.background", BUTTON_BG);
        UIManager.put("Button.foreground", TEXT_PRIMARY);
        UIManager.put("ComboBox.background", INPUT_BG);
        UIManager.put("ComboBox.foreground", TEXT_PRIMARY);
        UIManager.put("ScrollPane.background", BACKGROUND);
        UIManager.put("Viewport.background", BACKGROUND);
    }

    public static void applyTheme(Container container) {
        // No recursion! This was causing the hangs.
        // rely on UIManager globals and simple direct styling.
        container.setBackground(BACKGROUND);
        if (container instanceof JComponent) {
            ((JComponent) container).setOpaque(true);
        }

        for (Component c : container.getComponents()) {
            c.setBackground(BACKGROUND);
            c.setForeground(TEXT_PRIMARY);
            
            if (c instanceof JComponent) {
                ((JComponent) c).setOpaque(true);
            }

            if (c instanceof javax.swing.JButton) {
                javax.swing.JButton btn = (javax.swing.JButton) c;
                btn.setBackground(BUTTON_BG);
                btn.setBorder(javax.swing.BorderFactory.createLineBorder(ACCENT_RED, 1));
            } else if (c instanceof javax.swing.text.JTextComponent) {
                c.setBackground(INPUT_BG);
            } else if (c instanceof javax.swing.JComboBox || c instanceof javax.swing.JCheckBox) {
                c.setBackground(INPUT_BG);
            }
        }
    }
}
