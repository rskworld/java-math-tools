/**
 * Project: Java Basic Math Tools
 * Developer: Molla Samser
 * Designer & Tester: Rima Khatun
 * Website: https://rskworld.in
 * Year: 2026
 * Description: Main entry point for the Java Math Tools application.
 */

package com.rskworld.mathtools;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Initialize custom theme BEFORE creating any UI components
        com.rskworld.mathtools.utils.ThemeManager.initGlobalTheme();

        SwingUtilities.invokeLater(() -> {
            MathToolsFrame frame = new MathToolsFrame();
            frame.setVisible(true);
        });
    }
}
