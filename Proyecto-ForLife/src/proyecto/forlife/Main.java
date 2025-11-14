/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto.forlife;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
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
public class Main {


    private JFrame frame;
    private JPanel panelContenido;
    private CardLayout cardLayout;

    private Sistema sistema;
    private Clase claseYoga;
    private Clase claseBaile;
    
    public static void main(String[] args) {
        new Main();  
    }

    // Constructor: arma la ventana y los módulos
    public Main() {
        sistema = new Sistema();
        claseYoga = new Clase("Yoga", "7 pm", 30);
        claseBaile = new Clase("Baile", "8 pm", 30);

        inicializarVentana();
    }

    private void inicializarVentana() {
        frame = new JFrame("Sistema de Actividades Empresariales");
        frame.setSize(700, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // ---------- Panel de título ----------
        JPanel panelTitulo = new JPanel();
        JLabel lblTitulo = new JLabel("Sistema de Actividades Empresariales");
        lblTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
        panelTitulo.add(lblTitulo);
        frame.add(panelTitulo, BorderLayout.NORTH);

        // ---------- Menú lateral ----------
        JPanel panelMenu = new JPanel();
        panelMenu.setLayout(new GridLayout(5, 1, 5, 5));

        JButton btnClases = new JButton("Clases (Yoga / Baile)");
        JButton btnGym   = new JButton("Gimnasio");
        JButton btnCafe  = new JButton("Cafetería");
        JButton btnCine  = new JButton("Cine");
        JButton btnSalir = new JButton("Salir");

        panelMenu.add(btnClases);
        panelMenu.add(btnGym);
        panelMenu.add(btnCafe);
        panelMenu.add(btnCine);
        panelMenu.add(btnSalir);

        frame.add(panelMenu, BorderLayout.WEST);

        // ---------- Panel central con tarjetas ----------
        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);

        panelContenido.add(crearPanelClases(), "CLASES");
        panelContenido.add(crearPanelGym(), "GYM");
        panelContenido.add(crearPanelCafe(), "CAFE");
        panelContenido.add(crearPanelCine(), "CINE");

        frame.add(panelContenido, BorderLayout.CENTER);

        // ---------- Acciones de menú ----------
        btnClases.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenido, "CLASES");
            }
        });

        btnGym.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenido, "GYM");
            }
        });

        btnCafe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenido, "CAFE");
            }
        });

        btnCine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenido, "CINE");
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }

    // PANEL CLASES 
    private JPanel crearPanelClases() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Módulo de Clases (Yoga y Baile)");
        lblTitulo.setBounds(200, 10, 250, 25);
        lblTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        panel.add(lblTitulo);

        JLabel lblId = new JLabel("ID del empleado:");
        lblId.setBounds(30, 50, 120, 25);
        panel.add(lblId);

        JTextField txtId = new JTextField();
        txtId.setBounds(150, 50, 80, 25);
        panel.add(txtId);

        JButton btnInscribirYoga  = new JButton("Inscribir en Yoga");
        JButton btnInscribirBaile = new JButton("Inscribir en Baile");
        JButton btnEliminar       = new JButton("Eliminar inscripción");
        JButton btnActualizar     = new JButton("Actualizar lista");

        btnInscribirYoga.setBounds(250, 50, 150, 25);
        btnInscribirBaile.setBounds(410, 50, 150, 25);
        btnEliminar.setBounds(30, 90, 190, 25);
        btnActualizar.setBounds(230, 90, 150, 25);

        panel.add(btnInscribirYoga);
        panel.add(btnInscribirBaile);
        panel.add(btnEliminar);
        panel.add(btnActualizar);

        JLabel lblMensaje = new JLabel(" ");
        lblMensaje.setBounds(30, 120, 530, 25);
        panel.add(lblMensaje);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setBounds(30, 150, 530, 200);
        panel.add(scroll);

        // Refrescar lista
        Runnable refrescar = new Runnable() {
            @Override
            public void run() {
                String texto = "";
                texto += "Clase de Yoga 8:00 AM (" + claseYoga.getCantidadInscritos() + "/" + claseYoga.getCupoMaximo() + "):\n";
                if (claseYoga.getCantidadInscritos() == 0) {
                    texto += "  (sin inscritos)\n";
                } else {
                    for (int i = 0; i < claseYoga.getCantidadInscritos(); i++) {
                        texto += "  - " + claseYoga.getInscritos()[i].getId() + " - " +
                                 claseYoga.getInscritos()[i].getNombre() + "\n";
                    }
                }
                texto += "\nClase de Baile 9:00 AM(" + claseBaile.getCantidadInscritos() + "/" + claseBaile.getCupoMaximo() + "):\n";
                if (claseBaile.getCantidadInscritos() == 0) {
                    texto += "  (sin inscritos)\n";
                } else {
                    for (int i = 0; i < claseBaile.getCantidadInscritos(); i++) {
                        texto += "  - " + claseBaile.getInscritos()[i].getId() + " - " +
                                 claseBaile.getInscritos()[i].getNombre() + "\n";
                    }
                }
                area.setText(texto);
            }
        };

        // Botón Yoga
        btnInscribirYoga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoId = txtId.getText().trim();
                try {
                    int id = Integer.parseInt(textoId);
                    Empleado emp = sistema.buscarEmpleadoPorId(id);
                    if (emp != null) {
                        boolean exito = claseYoga.inscribirEmpleado(emp);
                        if (exito) {
                            lblMensaje.setText("Empleado " + emp.getNombre() + " inscrito en Yoga.");
                            refrescar.run();
                        } else {
                            lblMensaje.setText("Cupo lleno en Yoga.");
                        }
                    } else {
                        lblMensaje.setText("Empleado no encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    lblMensaje.setText("ID inválido. Ingrese un número.");
                }
            }
        });

        // Botón Baile
        btnInscribirBaile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoId = txtId.getText().trim();
                try {
                    int id = Integer.parseInt(textoId);
                    Empleado emp = sistema.buscarEmpleadoPorId(id);
                    if (emp != null) {
                        boolean exito = claseBaile.inscribirEmpleado(emp);
                        if (exito) {
                            lblMensaje.setText("Empleado " + emp.getNombre() + " inscrito en Baile.");
                            refrescar.run();
                        } else {
                            lblMensaje.setText("Cupo lleno en Baile.");
                        }
                    } else {
                        lblMensaje.setText("Empleado no encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    lblMensaje.setText("ID inválido. Ingrese un número.");
                }
            }
        });

        // Botón Eliminar
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textoId = txtId.getText().trim();
                try {
                    int id = Integer.parseInt(textoId);
                    boolean eliminado = claseYoga.eliminarEmpleado(id);
                    if (!eliminado) {
                        eliminado = claseBaile.eliminarEmpleado(id);
                    }
                    if (eliminado) {
                        lblMensaje.setText("Inscripción eliminada correctamente.");
                        refrescar.run();
                    } else {
                        lblMensaje.setText("No se encontró ninguna inscripción con ese ID.");
                    }
                } catch (NumberFormatException ex) {
                    lblMensaje.setText("ID inválido. Ingrese un número.");
                }
            }
        });

        // Botón Actualizar
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refrescar.run();
            }
        });

        // Primera carga
        refrescar.run();

        return panel;
    }

    // ---------- PANEL GIMNASIO (plantilla) ----------
    private JPanel crearPanelGym() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Módulo de Gimnasio");
        lblTitulo.setBounds(250, 20, 200, 25);
        lblTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        panel.add(lblTitulo);

        JLabel lblInfo = new JLabel("Aquí implementarán las reservas de 2 pm a 7 pm.");
        lblInfo.setBounds(160, 80, 350, 25);
        panel.add(lblInfo);

        return panel;
    }

    // ---------- PANEL CAFETERÍA (plantilla) ----------
    private JPanel crearPanelCafe() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Módulo de Cafetería");
        lblTitulo.setBounds(250, 20, 200, 25);
        lblTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        panel.add(lblTitulo);

        JLabel lblInfo = new JLabel("Aquí implementarán el sistema de pedidos de bebidas.");
        lblInfo.setBounds(150, 80, 380, 25);
        panel.add(lblInfo);

        return panel;
    }

    // ---------- PANEL CINE (plantilla) ----------
    private JPanel crearPanelCine() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Módulo de Cine");
        lblTitulo.setBounds(270, 20, 150, 25);
        lblTitulo.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        panel.add(lblTitulo);

        JLabel lblInfo = new JLabel("Aquí implementarán la matriz de asientos.");
        lblInfo.setBounds(190, 80, 320, 25);
        panel.add(lblInfo);

        return panel;
    }
}

        
    
