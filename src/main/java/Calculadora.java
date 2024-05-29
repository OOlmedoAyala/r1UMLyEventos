/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP-TRABAJO
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {
    private JTextField textField;
    private double num1, num2, resultado;
    private char operador;

    public Calculadora() {
        setTitle("Calculadora");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(0, 50));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        add(textField, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 4, 5, 5));

        String[] botones = {"1", "2", "3", "+",
                            "4", "5", "6", "-",
                            "7", "8", "9", "*",
                            "C", "0", "=", "/"};

        for (String boton : botones) {
            JButton button = new JButton(boton);
            button.setPreferredSize(new Dimension(80, 80));
            button.addActionListener(this);
            panelBotones.add(button);
        }

        add(panelBotones, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "C":
                textField.setText("");
                num1 = num2 = resultado = 0;
                operador = ' ';
                break;
            case "=":
                num2 = Double.parseDouble(textField.getText());
                calcular();
                textField.setText(Double.toString(resultado));
                num1 = resultado;
                num2 = 0;
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                operador = command.charAt(0);
                num1 = Double.parseDouble(textField.getText());
                textField.setText("");
                break;
            default:
                textField.setText(textField.getText() + command);
                break;
        }
    }

    private void calcular() {
        switch (operador) {
            case '+' -> resultado = num1 + num2;
            case '-' -> resultado = num1 - num2;
            case '*' -> resultado = num1 * num2;
            case '/' -> {
                if (num2 != 0) {
                    resultado = num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(this, "Error: división por cero", "Error", JOptionPane.ERROR_MESSAGE);
                    resultado = Double.NaN;
                }
            }
        }
    }

    public static void main(String[] args) {
        new Calculadora();
    }
}
