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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author lcast
 */
public class PanelCine {
    private Sistema sistema;
    private GestorCine gestorCine;
    private JPanel panelSalida;

    public PanelCine(Sistema sistema, GestorCine gestorCine) {
        this.sistema = sistema;
        this.gestorCine = gestorCine;
        crearPanel();
    }

    public JPanel obtenerPanel() {
        return panelSalida;
    }

    private void crearPanel() {
        panelSalida = new JPanel();
        panelSalida.setLayout(null);

 JLabel labelTitulo = new JLabel("Módulo de Cine - Reservas de Asientos");
        labelTitulo.setBounds(250, 10, 350, 25);
        labelTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        panelSalida.add(labelTitulo);

        // ---------- Selección de sala ----------
        JLabel labelSala = new JLabel("Sala:");
        labelSala.setBounds(30, 50, 40, 25);
        panelSalida.add(labelSala);

        int cantidadSalas = gestorCine.getCantidadDeSalas();
        String[] nombresSalas = new String[cantidadSalas];
        for (int i = 0; i < cantidadSalas; i++) {
            nombresSalas[i] = "Sala " + (i + 1);
        }

        JComboBox<String> comboSalas = new JComboBox<>(nombresSalas);
        comboSalas.setBounds(70, 50, 100, 25);
        panelSalida.add(comboSalas);

        JLabel labelIdEmpleado = new JLabel("ID Empleado:");
        labelIdEmpleado.setBounds(200, 50, 90, 25);
        panelSalida.add(labelIdEmpleado);

        JTextField campoIdEmpleado = new JTextField();
        campoIdEmpleado.setBounds(290, 50, 80, 25);
        panelSalida.add(campoIdEmpleado);

        JButton botonReservar = new JButton("Reservar asiento");
        botonReservar.setBounds(390, 50, 150, 25);
        panelSalida.add(botonReservar);

        JButton botonCancelar = new JButton("Cancelar reserva");
        botonCancelar.setBounds(550, 50, 150, 25);
        panelSalida.add(botonCancelar);

        JButton botonCambiarPelicula = new JButton("Cambiar película");
        botonCambiarPelicula.setBounds(30, 85, 160, 25);
        panelSalida.add(botonCambiarPelicula);

        JButton botonCambiarTamano = new JButton("Cambiar tamaño sala");
        botonCambiarTamano.setBounds(210, 85, 190, 25);
        panelSalida.add(botonCambiarTamano);

        JButton botonRefrescar = new JButton("Actualizar vista");
        botonRefrescar.setBounds(420, 85, 150, 25);
        panelSalida.add(botonRefrescar);

        JLabel labelLeyenda = new JLabel("L = Libre    O = Ocupado");
        labelLeyenda.setBounds(590, 85, 200, 25);
        panelSalida.add(labelLeyenda);

        JLabel etiquetaMensaje = new JLabel(" ");
        etiquetaMensaje.setBounds(30, 115, 750, 25);
        panelSalida.add(etiquetaMensaje);

        JTextArea areaAsientos = new JTextArea();
        areaAsientos.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaAsientos);
        scroll.setBounds(30, 150, 800, 350);
        panelSalida.add(scroll);

        // ---------- Acción para dibujar la sala seleccionada ----------
        Runnable accionRefrescar = new Runnable() {
            @Override
            public void run() {
                int indiceSala = comboSalas.getSelectedIndex();
                SalaCine sala = gestorCine.getSala(indiceSala);
                if (sala == null) {
                    areaAsientos.setText("No se encontró la sala.");
                    return;
                }

                Empleado[][] asientos = sala.getAsientos();
                int filas = sala.getFilas();
                int columnas = sala.getColumnas();

                String texto = "";
                texto = texto + nombresSalas[indiceSala] + "    Película: " + sala.getNombrePelicula() + "\n";
                texto = texto + "L = Libre   O = Ocupado\n\n";
                texto = texto + "                PANTALLA\n\n";

                // Encabezado de columnas
                texto = texto + "     ";
                for (int j = 0; j < columnas; j++) {
                    texto = texto + (j + 1) + "   ";
                }
                texto = texto + "\n";

                // Filas con letras
                for (int i = 0; i < filas; i++) {
                    char letraFila = (char) ('A' + i);
                    texto = texto + letraFila + " :  ";
                    for (int j = 0; j < columnas; j++) {
                        if (asientos[i][j] == null) {
                            texto = texto + "L   ";
                        } else {
                            texto = texto + "O   ";
                        }
                    }
                    texto = texto + "\n";
                }

                areaAsientos.setText(texto);
            }
        };

        // Al cambiar de sala en el combo
        comboSalas.addActionListener(new ActionListener() {
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

        // ---------- Reservar asiento ----------
        botonReservar.addActionListener(new ActionListener() {
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

                    int indiceSala = comboSalas.getSelectedIndex();
                    SalaCine sala = gestorCine.getSala(indiceSala);
                    if (sala == null) {
                        etiquetaMensaje.setText("Sala no encontrada.");
                        return;
                    }

                    if (sala.yaReservo(id)) {
                        etiquetaMensaje.setText("El empleado ya tiene un asiento reservado en esta sala.");
                        return;
                    }

                    String filaStr = JOptionPane.showInputDialog(panelSalida,
                            "Ingrese la fila (letra, por ejemplo A, B, C):");
                    String colStr = JOptionPane.showInputDialog(panelSalida,
                            "Ingrese la columna (número, por ejemplo 1, 2, 3):");

                    if (filaStr == null || colStr == null) {
                        return; // canceló
                    }

                    filaStr = filaStr.trim().toUpperCase();
                    int fila;
                    if (filaStr.length() == 1) {
                        char letra = filaStr.charAt(0);
                        fila = (int) letra - (int) 'A';
                    } else {
                        etiquetaMensaje.setText("Fila inválida.");
                        return;
                    }

                    int columna = Integer.parseInt(colStr.trim()) - 1;

                    boolean ok = sala.reservarAsiento(fila, columna, empleado);
                    if (ok) {
                        etiquetaMensaje.setText("Reserva exitosa para " + empleado.getNombre() + ".");
                    } else {
                        etiquetaMensaje.setText("No se pudo reservar (asiento ocupado o datos inválidos).");
                    }

                    accionRefrescar.run();

                } catch (NumberFormatException ex) {
                    etiquetaMensaje.setText("ID o columna inválida.");
                }
            }
        });

        // ---------- Cancelar reserva ----------
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

                    int indiceSala = comboSalas.getSelectedIndex();
                    SalaCine sala = gestorCine.getSala(indiceSala);
                    if (sala == null) {
                        etiquetaMensaje.setText("Sala no encontrada.");
                        return;
                    }

                    int[] pos = sala.obtenerAsientoPorEmpleado(id);
                    if (pos[0] == -1) {
                        etiquetaMensaje.setText("Este empleado no tiene asiento reservado en esta sala.");
                        return;
                    }

                    boolean ok = sala.cancelarAsiento(pos[0], pos[1]);
                    if (ok) {
                        etiquetaMensaje.setText("Reserva cancelada para " + empleado.getNombre() + ".");
                    } else {
                        etiquetaMensaje.setText("No se pudo cancelar la reserva.");
                    }

                    accionRefrescar.run();

                } catch (NumberFormatException ex) {
                    etiquetaMensaje.setText("ID inválido.");
                }
            }
        });

        // ---------- Cambiar película ----------
        botonCambiarPelicula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indiceSala = comboSalas.getSelectedIndex();
                String nuevaPelicula = JOptionPane.showInputDialog(panelSalida,
                        "Ingrese el nuevo nombre de la película:");
                if (nuevaPelicula == null) {
                    return; // canceló
                }
                gestorCine.cambiarPelicula(indiceSala, nuevaPelicula);
                etiquetaMensaje.setText("Película cambiada correctamente.");
                accionRefrescar.run();
            }
        });

        // ---------- Cambiar tamaño de la sala ----------
        botonCambiarTamano.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int indiceSala = comboSalas.getSelectedIndex();

                String filasStr = JOptionPane.showInputDialog(panelSalida,
                        "Ingrese la nueva cantidad de filas:");
                String columnasStr = JOptionPane.showInputDialog(panelSalida,
                        "Ingrese la nueva cantidad de columnas:");

                if (filasStr == null || columnasStr == null) {
                    return; // canceló
                }

                try {
                    int nuevasFilas = Integer.parseInt(filasStr.trim());
                    int nuevasColumnas = Integer.parseInt(columnasStr.trim());

                    gestorCine.cambiarTamanoSala(indiceSala, nuevasFilas, nuevasColumnas);
                    etiquetaMensaje.setText("Tamaño de la sala cambiado. Se limpiaron las reservas.");
                    accionRefrescar.run();

                } catch (NumberFormatException ex) {
                    etiquetaMensaje.setText("Filas o columnas inválidas.");
                }
            }
        });

        // Mostrar al inicio
        accionRefrescar.run();
    }
    
}
