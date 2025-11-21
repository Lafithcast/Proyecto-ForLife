/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.forlife.pkg1.pkg0;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author lcast
 */

public class PanelEmpleados {

    private Sistema sistema;
    private JPanel panelSalida;

    public PanelEmpleados(Sistema sistema) {
        this.sistema = sistema;
        crearPanel();
    }

    public JPanel obtenerPanel() {
        return panelSalida;
    }

    private void crearPanel() {
        panelSalida = new JPanel();
        panelSalida.setLayout(null);

        JLabel labelTitulo = new JLabel("Administrar Empleados");
        labelTitulo.setBounds(250, 10, 300, 30);
        labelTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 16));
        panelSalida.add(labelTitulo);

        JLabel labelId = new JLabel("ID:");
        labelId.setBounds(30, 60, 40, 25);
        panelSalida.add(labelId);

        JTextField campoId = new JTextField();
        campoId.setBounds(70, 60, 80, 25);
        panelSalida.add(campoId);

        JLabel labelNombre = new JLabel("Nombre:");
        labelNombre.setBounds(170, 60, 60, 25);
        panelSalida.add(labelNombre);

        JTextField campoNombre = new JTextField();
        campoNombre.setBounds(230, 60, 200, 25);
        panelSalida.add(campoNombre);

        JLabel labelDepartamento = new JLabel("Departamento:");
        labelDepartamento.setBounds(450, 60, 100, 25);
        panelSalida.add(labelDepartamento);

        JTextField campoDepartamento = new JTextField();
        campoDepartamento.setBounds(560, 60, 200, 25);
        panelSalida.add(campoDepartamento);

        JButton botonCrear = new JButton("Crear empleado");
        botonCrear.setBounds(30, 100, 150, 25);
        panelSalida.add(botonCrear);

        JButton botonEliminar = new JButton("Eliminar por ID");
        botonEliminar.setBounds(200, 100, 150, 25);
        panelSalida.add(botonEliminar);

        JButton botonListar = new JButton("Listar empleados");
        botonListar.setBounds(370, 100, 150, 25);
        panelSalida.add(botonListar);

        JLabel etiquetaMensaje = new JLabel(" ");
        etiquetaMensaje.setBounds(30, 140, 700, 25);
        panelSalida.add(etiquetaMensaje);

        JTextArea areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaSalida);
        scroll.setBounds(30, 180, 800, 320);
        panelSalida.add(scroll);

        // Acción: crear empleado
        botonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(campoId.getText().trim());
                    String nombre = campoNombre.getText().trim();
                    String departamento = campoDepartamento.getText().trim();

                    if (nombre.equals("") || departamento.equals("")) {
                        etiquetaMensaje.setText("Nombre y departamento no pueden estar vacíos.");
                        return;
                    }

                    Empleado nuevo = new Empleado(id, nombre, departamento);
                    boolean ok = sistema.agregarEmpleado(nuevo);

                    if (ok) {
                        etiquetaMensaje.setText("Empleado creado: " + nombre);
                    } else {
                        etiquetaMensaje.setText("No se pudo crear (ID duplicado o sin espacio).");
                    }
                } catch (NumberFormatException ex) {
                    etiquetaMensaje.setText("ID inválido (debe ser un número).");
                }
            }
        });

        // Acción: eliminar empleado
        botonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoId = campoId.getText().trim();
                try {
                    int id = Integer.parseInt(textoId);
                    boolean ok = sistema.eliminarEmpleadoPorId(id);
                    if (ok) {
                        etiquetaMensaje.setText("Empleado con ID " + id + " eliminado.");
                    } else {
                        etiquetaMensaje.setText("No se encontró empleado con ese ID.");
                    }
                } catch (NumberFormatException ex) {
                    etiquetaMensaje.setText("ID inválido.");
                }
            }
        });

        // Acción: listar empleados
        botonListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Empleado[] lista = sistema.getEmpleados();
                String texto = "=== Empleados registrados ===\n\n";
                for (int i = 0; i < lista.length; i++) {
                    Empleado emp = lista[i];
                    if (emp != null) {
                        texto = texto + "ID: " + emp.getId()
                                + " | Nombre: " + emp.getNombre()
                                + " | Departamento: " + emp.getDepartamento()
                                + "\n";
                    }
                }
                areaSalida.setText(texto);
            }
        });
    }
}

