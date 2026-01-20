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

public class PlotterPanel extends JPanel {
    private JTextField aField, bField, cField;
    private GraphCanvas canvas;

    public PlotterPanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeManager.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initComponents();
    }

    private void initComponents() {
        JPanel control = new JPanel(new FlowLayout());
        control.setOpaque(false);
        control.add(new JLabel("y = "));
        aField = new JTextField("1", 3);
        control.add(aField);
        control.add(new JLabel("x² + "));
        bField = new JTextField("0", 3);
        control.add(bField);
        control.add(new JLabel("x + "));
        cField = new JTextField("0", 3);
        control.add(cField);

        JButton plotBtn = new JButton("Plot Graph");
        plotBtn.addActionListener(e -> canvas.repaint());
        control.add(plotBtn);

        add(control, BorderLayout.NORTH);

        canvas = new GraphCanvas();
        add(canvas, BorderLayout.CENTER);

        ThemeManager.applyTheme(this);
    }

    private class GraphCanvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int w = getWidth();
            int h = getHeight();
            int centerX = w / 2;
            int centerY = h / 2;
            int scale = 20;

            // Draw Axis
            g2.setColor(Color.GRAY);
            g2.drawLine(0, centerY, w, centerY);
            g2.drawLine(centerX, 0, centerX, h);

            // Draw Parabola
            try {
                double a = Double.parseDouble(aField.getText());
                double b = Double.parseDouble(bField.getText());
                double c = Double.parseDouble(cField.getText());

                g2.setColor(ThemeManager.ACCENT_RED);
                g2.setStroke(new BasicStroke(2));

                int prevX = -1, prevY = -1;
                for (int screenX = 0; screenX < w; screenX++) {
                    double x = (double) (screenX - centerX) / scale;
                    double y = a * x * x + b * x + c;
                    int screenY = centerY - (int) (y * scale);

                    if (prevX != -1 && screenY >= 0 && screenY <= h) {
                        g2.drawLine(prevX, prevY, screenX, screenY);
                    }
                    prevX = screenX;
                    prevY = screenY;
                }
            } catch (Exception e) {}
        }
    }
}
