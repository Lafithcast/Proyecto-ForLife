/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.forlife.pkg1.pkg0;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author lcast
 */
public class PanelGym {
    private Sistema sistema;
    private Gimnasio gimnasio;
    private JPanel panelSalida;

    public PanelGym(Sistema sistema, Gimnasio gimnasio) {
        this.sistema = sistema;
        this.gimnasio = gimnasio;
        crearPanel();
    }

    public JPanel obtenerPanel() {
        return panelSalida;
    }

    private void crearPanel() {
        panelSalida = new JPanel();
        panelSalida.setLayout(null);

        JLabel labelTitulo = new JLabel("Módulo de Gimnasio - Reservas de citas");
        labelTitulo.setBounds(220, 10, 400, 30);
        labelTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        panelSalida.add(labelTitulo);

        // ----- Campos de entrada -----
        JLabel labelIdEmpleado = new JLabel("ID del empleado:");
        labelIdEmpleado.setBounds(30, 60, 120, 25);
        panelSalida.add(labelIdEmpleado);

        JTextField campoIdEmpleado = new JTextField();
        campoIdEmpleado.setBounds(150, 60, 80, 25);
        panelSalida.add(campoIdEmpleado);

        JLabel labelIndiceHora = new JLabel("Índice de hora:");
        labelIndiceHora.setBounds(250, 60, 100, 25);
        panelSalida.add(labelIndiceHora);

        JTextField campoIndiceHora = new JTextField();
        campoIndiceHora.setBounds(350, 60, 40, 25);
        panelSalida.add(campoIndiceHora);

        // ----- Label con los índices de horas -----
        String textoIndices = "Índices: ";
        String[] horarios = gimnasio.getHorarios();
        for (int i = 0; i < horarios.length; i++) {
            textoIndices = textoIndices + i + " -> " + horarios[i];
            if (i < horarios.length - 1) {
                textoIndices = textoIndices + "   ";
            }
        }

        JLabel labelHorasIndices = new JLabel(textoIndices);
        labelHorasIndices.setBounds(30, 90, 800, 25);
        panelSalida.add(labelHorasIndices);

        // ----- Botones -----
        JButton botonReservar = new JButton("Reservar");
        botonReservar.setBounds(30, 130, 130, 25);
        panelSalida.add(botonReservar);

        JButton botonCancelar = new JButton("Cancelar reserva");
        botonCancelar.setBounds(180, 130, 160, 25);
        panelSalida.add(botonCancelar);

        JButton botonListar = new JButton("Listar reservas");
        botonListar.setBounds(360, 130, 150, 25);
        panelSalida.add(botonListar);

        JButton botonRefrescar = new JButton("Refrescar vista");
        botonRefrescar.setBounds(530, 130, 150, 25);
        panelSalida.add(botonRefrescar);

        // ----- Mensaje y área de texto -----
        JLabel etiquetaMensaje = new JLabel(" ");
        etiquetaMensaje.setBounds(30, 165, 750, 25);
        panelSalida.add(etiquetaMensaje);

        JTextArea areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaSalida);
        scroll.setBounds(30, 200, 800, 320);
        panelSalida.add(scroll);

        // ----- Acción para refrescar la vista -----
        Runnable accionRefrescar = new Runnable() {
            @Override
            public void run() {
                String texto = "";
                texto = texto + "Horarios y reservas del gimnasio:\n\n";
                texto = texto + gimnasio.listarReservas();
                areaSalida.setText(texto);
            }
        };

        // ----- Botón: Listar reservas -----
        botonListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionRefrescar.run();
            }
        });

        // ----- Botón: Refrescar vista -----
        botonRefrescar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionRefrescar.run();
                etiquetaMensaje.setText("Vista actualizada.");
            }
        });

        // ----- Botón: Reservar (auto-elige espacio libre) -----
        botonReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoId = campoIdEmpleado.getText().trim();
                String textoIndiceHora = campoIndiceHora.getText().trim();

                try {
                    int id = Integer.parseInt(textoId);
                    int indiceHora = Integer.parseInt(textoIndiceHora);

                    Empleado empleado = sistema.buscarEmpleadoPorId(id);
                    if (empleado == null) {
                        etiquetaMensaje.setText("Empleado no encontrado.");
                        return;
                    }

                    if (indiceHora < 0 || indiceHora >= gimnasio.getCantidadDeHoras()) {
                        etiquetaMensaje.setText("Índice de hora inválido.");
                        return;
                    }

                    // Primero revisamos si ya tiene reserva
                    if (gimnasio.empleadoYaTieneReserva(id)) {
                        etiquetaMensaje.setText("El empleado ya tiene una reserva en el gimnasio.");
                        return;
                    }

                    // Buscar el primer espacio libre en esa hora
                    Empleado[][] tablaReservas = gimnasio.getReservas();
                    int espacioLibre = -1;
                    for (int j = 0; j < gimnasio.getEspaciosPorHora(); j++) {
                        if (tablaReservas[indiceHora][j] == null) {
                            espacioLibre = j;
                            break;
                        }
                    }

                    if (espacioLibre == -1) {
                        etiquetaMensaje.setText("No hay espacios libres para esa hora.");
                        return;
                    }

                    // Usamos el método reservar del Gimnasio
                    boolean reservaExitosa = gimnasio.reservar(indiceHora, espacioLibre, empleado);

                    if (reservaExitosa) {
                        String horarioTexto = horarios[indiceHora];
                        etiquetaMensaje.setText("Reserva exitosa: "
                                + empleado.getNombre() + " en hora "
                                + horarioTexto + ", espacio " + espacioLibre + ".");
                    } else {
                        etiquetaMensaje.setText("No se pudo reservar (error inesperado).");
                    }

                    accionRefrescar.run();

                } catch (NumberFormatException ex) {
                    etiquetaMensaje.setText("ID y hora deben ser números.");
                }
            }
        });

        // ----- Botón: Cancelar reserva -----
        botonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoId = campoIdEmpleado.getText().trim();
                try {
                    int id = Integer.parseInt(textoId);

                    Empleado empleado = sistema.buscarEmpleadoPorId(id);
                    if (empleado == null) {
                        etiquetaMensaje.setText("Empleado no encontrado.");
                        return;
                    }

                    boolean cancelado = gimnasio.cancelarReservaPorEmpleado(id);

                    if (cancelado) {
                        etiquetaMensaje.setText("Reserva cancelada para " + empleado.getNombre() + ".");
                    } else {
                        etiquetaMensaje.setText("El empleado no tenía reserva registrada.");
                    }

                    accionRefrescar.run();

                } catch (NumberFormatException ex) {
                    etiquetaMensaje.setText("ID inválido (debe ser un número).");
                }
            }
        });

        // Cargar la vista inicial
        accionRefrescar.run();
    }
}
    

