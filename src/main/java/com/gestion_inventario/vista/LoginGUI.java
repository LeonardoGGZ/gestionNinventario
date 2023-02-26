/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.gestion_inventario.vista;

/**
 *
 * @author Leo
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JLabel userLabel;
    private JTextField userText;
    private JLabel passwordLabel;
    private JPasswordField passwordText;
    private JButton loginButton;
    private JButton cancelButton;

    public LoginGUI() {
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Inicio de sesión - Sistema de Inventario");
        mainFrame.setSize(400, 200);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.setLocationRelativeTo(null);

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);

        statusLabel.setSize(350, 100);

        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 2));

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    public void showTextFieldDemo() {
        headerLabel.setText("Inicio de sesión - Sistema de Inventario");

        userLabel = new JLabel("Usuario: ", JLabel.RIGHT);
        userText = new JTextField(6);

        passwordLabel = new JLabel("Contraseña: ", JLabel.RIGHT);
        passwordText = new JPasswordField(6);

        passwordText.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // No se usa
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    loginButton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // No se usa
            }
        });

        loginButton = new JButton("Iniciar sesión");
        
        loginButton.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // No se usa
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    loginButton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // No se usa
            }
        });
        
        cancelButton = new JButton("Cancelar");

        cancelButton.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // No se usa
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {

                    cancelButton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // No se usa
            }
        });
        
        
        loginButton.addActionListener(new LoginButtonListener());
        cancelButton.addActionListener(new CancelButtonListener());

        controlPanel.add(userLabel);
        controlPanel.add(userText);
        controlPanel.add(passwordLabel);
        controlPanel.add(passwordText);
        controlPanel.add(loginButton);
        controlPanel.add(cancelButton);

        mainFrame.setVisible(true);
    }

    private class LoginButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            String user = userText.getText();
            char[] password = passwordText.getPassword();

            if (user.equals("admin") && String.valueOf(password).equals("admin")) {
                statusLabel.setText("Bienvenido al sistema de inventario");

//Creacion y despliegue del panel de control principal del lado del cliente
                mainFrame.dispose();
                InventoryManagementSystem control_window = new InventoryManagementSystem();
                control_window.setLocationRelativeTo(null);
                control_window.setVisible(true);

            } else {
                statusLabel.setText("Usuario o contraseña incorrecta");
            }
        }
    }

    private class CancelButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
