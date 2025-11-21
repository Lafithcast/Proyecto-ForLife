/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.forlife.pkg1.pkg0;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelClases {

    private Sistema sistema;
    private Actividad claseYoga;
    private Actividad claseBaile;
    private JPanel panelSalida;

    public PanelClases(Sistema sistema, Actividad claseYoga, Actividad claseBaile) {
        this.sistema = sistema;
        this.claseYoga = claseYoga;
        this.claseBaile = claseBaile;
        crearPanel();
    }

    public JPanel obtenerPanel() {
        return panelSalida;
    }

    private void crearPanel() {
        panelSalida = new JPanel();
        panelSalida.setLayout(null);

        JLabel labelTitulo = new JLabel("Módulo de Clases (Yoga y Baile)");
        labelTitulo.setBounds(250, 10, 300, 25);
        labelTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        panelSalida.add(labelTitulo);

        JLabel labelId = new JLabel("ID del empleado:");
        labelId.setBounds(30, 50, 120, 25);
        panelSalida.add(labelId);

        JTextField campoId = new JTextField();
        campoId.setBounds(160, 50, 80, 25);
        panelSalida.add(campoId);

        JButton botonInscribirYoga = new JButton("Inscribir en Yoga");
        JButton botonInscribirBaile = new JButton("Inscribir en Baile");
        JButton botonEliminar = new JButton("Eliminar inscripción");
        JButton botonActualizar = new JButton("Actualizar lista");

        botonInscribirYoga.setBounds(260, 50, 150, 25);
        botonInscribirBaile.setBounds(420, 50, 150, 25);
        botonEliminar.setBounds(30, 90, 190, 25);
        botonActualizar.setBounds(230, 90, 150, 25);

        panelSalida.add(botonInscribirYoga);
        panelSalida.add(botonInscribirBaile);
        panelSalida.add(botonEliminar);
        panelSalida.add(botonActualizar);

        JLabel etiquetaMensaje = new JLabel(" ");
        etiquetaMensaje.setBounds(30, 120, 600, 25);
        panelSalida.add(etiquetaMensaje);

        JTextArea areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaSalida);
        scroll.setBounds(30, 150, 800, 350);
        panelSalida.add(scroll);

        Runnable accionRefrescar = new Runnable() {
            @Override
            public void run() {
                String texto = "";

                texto = texto + "Clase: " + claseYoga.getNombre() + " (" 
                         + claseYoga.getHorario() + ") "
                         + "Cupo: " + claseYoga.getCantidadDeInscritos()
                         + "/" + claseYoga.getCupoMaximo() + "\n";
                Empleado[] inscritosYoga = claseYoga.getEmpleadosInscritos();
                if (claseYoga.getCantidadDeInscritos() == 0) {
                    texto = texto + "  (sin inscritos)\n";
                } else {
                    for (int i = 0; i < claseYoga.getCantidadDeInscritos(); i++) {
                        texto = texto + "  - " + inscritosYoga[i].getId()
                                + " - " + inscritosYoga[i].getNombre() + "\n";
                    }
                }

                texto = texto + "\n";

                texto = texto + "Clase: " + claseBaile.getNombre() + " (" 
                         + claseBaile.getHorario() + ") "
                         + "Cupo: " + claseBaile.getCantidadDeInscritos()
                         + "/" + claseBaile.getCupoMaximo() + "\n";
                Empleado[] inscritosBaile = claseBaile.getEmpleadosInscritos();
                if (claseBaile.getCantidadDeInscritos() == 0) {
                    texto = texto + "  (sin inscritos)\n";
                } else {
                    for (int i = 0; i < claseBaile.getCantidadDeInscritos(); i++) {
                        texto = texto + "  - " + inscritosBaile[i].getId()
                                + " - " + inscritosBaile[i].getNombre() + "\n";
                    }
                }

                areaSalida.setText(texto);
            }
        };

        botonInscribirYoga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(campoId.getText().trim());
                    Empleado empleado = sistema.buscarEmpleadoPorId(id);
                    if (empleado == null) {
                        etiquetaMensaje.setText("Empleado no encontrado.");
                        return;
                    }
                    boolean ok = claseYoga.inscribirEmpleado(empleado);
                    if (ok) {
                        etiquetaMensaje.setText("Empleado inscrito en Yoga.");
                    } else {
                        etiquetaMensaje.setText("No se pudo inscribir (clase llena o ya inscrito).");
                    }
                    accionRefrescar.run();
                } catch (NumberFormatException ex) {
                    etiquetaMensaje.setText("ID inválido.");
                }
            }
        });

        botonInscribirBaile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(campoId.getText().trim());
                    Empleado empleado = sistema.buscarEmpleadoPorId(id);
                    if (empleado == null) {
                        etiquetaMensaje.setText("Empleado no encontrado.");
                        return;
                    }
                    boolean ok = claseBaile.inscribirEmpleado(empleado);
                    if (ok) {
                        etiquetaMensaje.setText("Empleado inscrito en Baile.");
                    } else {
                        etiquetaMensaje.setText("No se pudo inscribir (clase llena o ya inscrito).");
                    }
                    accionRefrescar.run();
                } catch (NumberFormatException ex) {
                    etiquetaMensaje.setText("ID inválido.");
                }
            }
        });

        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(campoId.getText().trim());
                    boolean eliminado = claseYoga.eliminarEmpleado(id);
                    if (!eliminado) {
                        eliminado = claseBaile.eliminarEmpleado(id);
                    }
                    if (eliminado) {
                        etiquetaMensaje.setText("Inscripción eliminada correctamente.");
                    } else {
                        etiquetaMensaje.setText("El empleado no estaba inscrito en ninguna clase.");
                    }
                    accionRefrescar.run();
                } catch (NumberFormatException ex) {
                    etiquetaMensaje.setText("ID inválido.");
                }
            }
        });

        botonActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accionRefrescar.run();
            }
        });

        // Primera carga
        accionRefrescar.run();
    }
}

