/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto.forlife.pkg1.pkg0;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ProyectoForLife10 {

    private JFrame frame;
    private JPanel panelContenido;
    private CardLayout cardLayout;

    // Lógica
    private Sistema sistema;
    private Actividad claseYoga;
    private Actividad claseBaile;
    private Gimnasio gimnasio;
    private GestorCine gestorCine;
    private Cafeteria cafeteria;

    // Paneles (ventanas) de Swing
    private PanelClases panelClases;
    private PanelGym panelGym;
    private PanelCine panelCine;
    private PanelCafeteria panelCafeteria;
    private PanelEmpleados panelEmpleados;

    public static void main(String[] args) {
        new ProyectoForLife10();
    }

    public ProyectoForLife10() {
        // 1. Crear lógica
        sistema = new Sistema();
        claseYoga = new Actividad("Yoga", "7 pm", 30);
        claseBaile = new Actividad("Baile", "8 pm", 30);
        gimnasio = new Gimnasio();
        gestorCine = new GestorCine(3);   // por ejemplo 3 salas
        cafeteria = new Cafeteria(50);    // máximo 50 pedidos

        // 2. Crear paneles (ventanas) y pasarles la lógica que necesitan
        panelClases = new PanelClases(sistema, claseYoga, claseBaile);
        panelGym = new PanelGym(sistema, gimnasio);
        panelCine = new PanelCine(sistema, gestorCine);
        panelCafeteria = new PanelCafeteria(sistema, cafeteria);
        panelEmpleados = new PanelEmpleados(sistema);

        // 3. Armar la ventana principal
        inicializarVentana();
    }

    private void inicializarVentana() {
        frame = new JFrame("Sistema For Life - Proyecto");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // ----- Título arriba -----
        JPanel panelTitulo = new JPanel();
        JLabel labelTitulo = new JLabel("Sistema de Actividades For Life");
        labelTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        panelTitulo.add(labelTitulo);
        frame.add(panelTitulo, BorderLayout.NORTH);

        // ----- Menú a la izquierda -----
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(6, 1, 5, 5));

        JButton botonClases = new JButton("Clases (Yoga / Baile)");
        JButton botonGym = new JButton("Gimnasio");
        JButton botonCine = new JButton("Cine");
        JButton botonCafeteria = new JButton("Cafetería");
        JButton botonEmpleados = new JButton("Empleados");
        JButton botonSalir = new JButton("Salir");

        panelMenu.add(botonClases);
        panelMenu.add(botonGym);
        panelMenu.add(botonCine);
        panelMenu.add(botonCafeteria);
        panelMenu.add(botonEmpleados);
        panelMenu.add(botonSalir);

        frame.add(panelMenu, BorderLayout.WEST);

        // ----- Panel central con CardLayout -----
        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);

        panelContenido.add(panelClases.obtenerPanel(), "CLASES");
        panelContenido.add(panelGym.obtenerPanel(), "GYM");
        panelContenido.add(panelCine.obtenerPanel(), "CINE");
        panelContenido.add(panelCafeteria.obtenerPanel(), "CAFETERIA");
        panelContenido.add(panelEmpleados.obtenerPanel(), "EMPLEADOS");

        frame.add(panelContenido, BorderLayout.CENTER);

        // ----- Acciones de los botones del menú -----
        botonClases.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenido, "CLASES");
            }
        });

        botonGym.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenido, "GYM");
            }
        });

        botonCine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenido, "CINE");
            }
        });

        botonCafeteria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenido, "CAFETERIA");
            }
        });

        botonEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenido, "EMPLEADOS");
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // Mostrar ventana
        frame.setVisible(true);
    }
}
