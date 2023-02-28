package com.gestion_inventario.vista;

import com.gestion_inventario.controlador.CtrlProducto;
import com.gestion_inventario.modelo.ConsultasInventario;
import com.gestion_inventario.modelo.ConsultasProducto;
import com.gestion_inventario.modelo.ConsultasRegistros;
import com.gestion_inventario.modelo.Producto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlDeInventario extends JFrame{
    
  public ControlDeInventario(){
      
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
        
         Producto producto = new Producto();
         ConsultasInventario consultasInventario = new ConsultasInventario();
         ConsultasProducto consultasProducto = new ConsultasProducto();
         ConsultasRegistros consultasRegistros = new ConsultasRegistros();
         ConsultarProductosUI consultarProductosUI = new ConsultarProductosUI();
         AgregarStockUI agregarStockUI = new AgregarStockUI(consultarProductosUI, producto);
         
         
         CtrlProducto ctrlProducto = new CtrlProducto(producto, consultasProducto, consultarProductosUI);
         
         consultarProductosUI.setVisible(true);
        
        
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