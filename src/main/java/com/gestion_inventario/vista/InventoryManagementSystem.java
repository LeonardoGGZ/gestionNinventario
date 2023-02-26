/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion_inventario.vista;

/**
 *
 * @author Leo
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryManagementSystem extends JFrame{
    
  public InventoryManagementSystem(){
      
    setTitle("Sistema de gestión de inventario");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1200, 700);
      
    Container content = getContentPane();
    content.setLayout(new BorderLayout());

    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("Archivo");
    JMenuItem exitItem = new JMenuItem("Salir");
    fileMenu.add(exitItem);
    menuBar.add(fileMenu);
    setJMenuBar(menuBar);

    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    JLabel titleLabel = new JLabel("Sistema de gestión de inventario", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    topPanel.add(titleLabel, BorderLayout.CENTER);
    content.add(topPanel, BorderLayout.NORTH);

    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new GridLayout(8, 1));
    JButton addProductButton = new JButton("Añadir producto");
    JButton removeProductButton = new JButton("Eliminar producto");
    JButton updateProductButton = new JButton("Actualizar producto");
    JButton viewInventoryButton = new JButton("Ver inventario");
    JButton manageCategoriesButton = new JButton("Administrar categorías");
    JButton manageSuppliersButton = new JButton("Administrar proveedores");
    JButton generateReportButton = new JButton("Generar informe");
    JButton manageUsersButton = new JButton("Administrar usuarios");
    leftPanel.add(addProductButton);
    leftPanel.add(removeProductButton);
    leftPanel.add(updateProductButton);
    leftPanel.add(viewInventoryButton);
    leftPanel.add(manageCategoriesButton);
    leftPanel.add(manageSuppliersButton);
    leftPanel.add(generateReportButton);
    leftPanel.add(manageUsersButton);
    content.add(leftPanel, BorderLayout.WEST);

    
    addProductButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        
          new AgregarProductosUI().setVisible(true);
        
        
      }
    });
    
    
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BorderLayout());
    JLabel statusLabel = new JLabel("Estado: Conectado", SwingConstants.LEFT);
    statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
    rightPanel.add(statusLabel, BorderLayout.SOUTH);
    content.add(rightPanel, BorderLayout.EAST);

  } 
}