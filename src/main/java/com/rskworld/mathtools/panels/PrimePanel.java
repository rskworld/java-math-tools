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
import java.util.List;

public class PrimePanel extends JPanel {
    private JTextField numberField;
    private JTextArea resultArea;

    public PrimePanel() {
        setLayout(new BorderLayout(10, 10));
        setBackground(ThemeManager.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents();
    }

    private void initComponents() {
        JPanel top = new JPanel(new FlowLayout());
        top.setOpaque(false);
        top.add(new JLabel("Enter Number:"));
        numberField = new JTextField(10);
        top.add(numberField);

        JButton checkBtn = new JButton("Check Prime & Factorize");
        checkBtn.addActionListener(e -> process());
        top.add(checkBtn);

        add(top, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        ThemeManager.applyTheme(this);
    }

    private void process() {
        try {
            long n = Long.parseLong(numberField.getText());
            if (n < 2) { resultArea.setText("Not a prime number (must be >= 2)"); return; }

            boolean isPrime = isPrime(n);
            List<Long> factors = getPrimeFactors(n);

            StringBuilder sb = new StringBuilder();
            sb.append("Number: ").append(n).append("\n");
            sb.append("Is Prime: ").append(isPrime ? "YES" : "NO").append("\n");
            sb.append("Prime Factors: ").append(factors.toString()).append("\n");
            
            if (n < 1000) {
                sb.append("\nPrimes up to ").append(n).append(":\n");
                sb.append(getPrimesUpTo((int)n).toString());
            }

            resultArea.setText(sb.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer");
        }
    }

    private boolean isPrime(long n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (long i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    private List<Long> getPrimeFactors(long n) {
        List<Long> factors = new ArrayList<>();
        while (n % 2 == 0) { factors.add(2L); n /= 2; }
        for (long i = 3; i * i <= n; i += 2) {
            while (n % i == 0) { factors.add(i); n /= i; }
        }
        if (n > 1) factors.add(n);
        return factors;
    }

    private List<Integer> getPrimesUpTo(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        java.util.Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int p = 2; p * p <= limit; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= limit; i += p) isPrime[i] = false;
            }
        }
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) if (isPrime[i]) primes.add(i);
        return primes;
    }
}
