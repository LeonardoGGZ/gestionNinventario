/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gestion_inventario.vista;

import java.awt.Color;
import java.awt.Dialog;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Leo
 */
public class MostrarAnimacionDeCarga extends JDialog {

    private JDialog dialog;

    public MostrarAnimacionDeCarga(JFrame parent) {

        super(parent);

        System.out.println("mostrando");
        dialog = this;
        dialog.setModal(true);
        dialog.setUndecorated(true);

        dialog.setBackground(new Color(0, 0, 0, 0));
        dialog.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);

        // Agregar animación de carga al JDialog
        JPanel panel = new JPanel();
        //JLabel label = new JLabel(new ImageIcon("C:/Users/leona/Documents/NetBeansProjects/Gestion_de_inventario_inteligente/src/main/resources/progress.gif"));
        JLabel label = new JLabel("Cargando...");
        panel.add(label);
        dialog.add(panel);

    }

    public JDialog getDialog() {
        return dialog;
    }

}
