/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.forlife.pkg1.pkg0;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author lcast
 */
public class PanelCafeteria {
    private Sistema sistema;
    private Cafeteria cafeteria;
    private JPanel panelSalida;

    public PanelCafeteria(Sistema sistema, Cafeteria cafeteria) {
        this.sistema = sistema;
        this.cafeteria = cafeteria;
        crearPanel();
    }

    public JPanel obtenerPanel() {
        return panelSalida;
    }

    private void crearPanel() {
        panelSalida = new JPanel();
        panelSalida.setLayout(null);

 JLabel labelTitulo = new JLabel("Módulo de Cafetería - Pedidos de Bebidas");
        labelTitulo.setBounds(230, 10, 400, 25);
        labelTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        panelSalida.add(labelTitulo);

        // ------------------- CAMPOS DE ENTRADA -------------------

        JLabel labelId = new JLabel("ID del empleado:");
        labelId.setBounds(30, 60, 120, 25);
        panelSalida.add(labelId);

        JTextField campoId = new JTextField();
        campoId.setBounds(150, 60, 80, 25);
        panelSalida.add(campoId);

        JLabel labelBebida = new JLabel("Bebida:");
        labelBebida.setBounds(250, 60, 80, 25);
        panelSalida.add(labelBebida);

        JComboBox<String> comboBebidas = new JComboBox<>(cafeteria.getMenuBebidas());
        comboBebidas.setBounds(310, 60, 180, 25);
        panelSalida.add(comboBebidas);

        JLabel labelHora = new JLabel("Hora entrega:");
        labelHora.setBounds(510, 60, 90, 25);
        panelSalida.add(labelHora);

        JTextField campoHora = new JTextField();
        campoHora.setBounds(600, 60, 80, 25);
        panelSalida.add(campoHora);


        // ------------------- BOTONES -------------------

        JButton botonRegistrar = new JButton("Registrar pedido");
        botonRegistrar.setBounds(30, 100, 160, 25);
        panelSalida.add(botonRegistrar);

        JButton botonModificar = new JButton("Modificar pedido");
        botonModificar.setBounds(210, 100, 160, 25);
        panelSalida.add(botonModificar);

        JButton botonListar = new JButton("Listar pedidos");
        botonListar.setBounds(390, 100, 140, 25);
        panelSalida.add(botonListar);

        JButton botonRefrescar = new JButton("Refrescar vista");
        botonRefrescar.setBounds(550, 100, 160, 25);
        panelSalida.add(botonRefrescar);


        // ------------------- MENSAJES Y ÁREA DE TEXTO -------------------

        JLabel etiquetaMensaje = new JLabel(" ");
        etiquetaMensaje.setBounds(30, 135, 700, 25);
        panelSalida.add(etiquetaMensaje);

        JTextArea areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaSalida);
        scroll.setBounds(30, 170, 800, 340);
        panelSalida.add(scroll);


        // ------------------- ACCIONES -------------------

        Runnable accionRefrescar = new Runnable() {
            @Override
            public void run() {
                areaSalida.setText("=== Pedidos registrados ===\n\n" + cafeteria.listarPedidos());
            }
        };

        botonListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionRefrescar.run();
            }
        });

        botonRefrescar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionRefrescar.run();
                etiquetaMensaje.setText("Vista actualizada.");
            }
        });

        // ------------------- REGISTRAR PEDIDO -------------------

        botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoId = campoId.getText().trim();
                String bebida = comboBebidas.getSelectedItem().toString();
                String hora = campoHora.getText().trim();

                try {
                    int id = Integer.parseInt(textoId);

                    Empleado empleado = sistema.buscarEmpleadoPorId(id);
                    if (empleado == null) {
                        etiquetaMensaje.setText("Empleado no encontrado.");
                        return;
                    }

                    boolean ok = cafeteria.registrarPedido(id, bebida, hora);
                    if (ok) {
                        etiquetaMensaje.setText("Pedido registrado correctamente.");
                    } else {
                        etiquetaMensaje.setText("Este empleado ya tiene un pedido o no hay espacio.");
                    }

                    accionRefrescar.run();

                } catch (NumberFormatException ex) {
                    etiquetaMensaje.setText("ID inválido.");
                }
            }
        });

        // ------------------- MODIFICAR PEDIDO -------------------

        botonModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String textoId = campoId.getText().trim();
                String nuevaBebida = comboBebidas.getSelectedItem().toString();
                String nuevaHora = campoHora.getText().trim();

                try {
                    int id = Integer.parseInt(textoId);

                    boolean ok = cafeteria.modificador(id, nuevaBebida, nuevaHora);

                    if (ok) {
                        etiquetaMensaje.setText("Pedido modificado correctamente.");
                    } else {
                        etiquetaMensaje.setText("Este empleado no tiene pedido registrado.");
                    }

                    accionRefrescar.run();

                } catch (NumberFormatException ex) {
                    etiquetaMensaje.setText("ID inválido.");
                }
            }
        });

        // Primera carga
        accionRefrescar.run();
    }
    
}
