/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.forlife;

import javax.swing.JOptionPane;

/**
 *
 * @author lcast
 */
public class ClaseMenu {
    public static void main() {
        Sistema sistema = new Sistema();
        Clase yoga = new Clase("Yoga", "7 pm", 30);
        Clase baile = new Clase("Baile", "8 pm", 30);

        int opcion = 0;

        while (opcion != 5) {
            String menu = "=== MENÚ DE CLASES ===\n";
            menu += "1. Inscribir empleado en Yoga\n";
            menu += "2. Inscribir empleado en Baile\n";
            menu += "3. Eliminar inscripción\n";
            menu += "4. Ver inscritos\n";
            menu += "5. Salir\n\n";
            menu += "Seleccione una opción:";

            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            if (opcion == 1) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del empleado: "));
                Empleado e = sistema.buscarEmpleadoPorId(id);

                if (e != null) {
                    boolean existente = yoga.inscribirEmpleado(e);
                    if (existente) {
                        JOptionPane.showMessageDialog(null, e.getNombre() + " inscrito en Yoga");
                    } else {
                        JOptionPane.showMessageDialog(null, "Cupo lleno en Yoga");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Empleado no encontrado");
                }
            }

            else if (opcion == 2) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del empleado: "));
                Empleado e = sistema.buscarEmpleadoPorId(id);

                if (e != null) {
                    boolean existente = baile.inscribirEmpleado(e);
                    if (existente) {
                        JOptionPane.showMessageDialog(null, e.getNombre() + " inscrito en Baile");
                    } else {
                        JOptionPane.showMessageDialog(null, "Cupo lleno en Baile");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Empleado no encontrado");
                }
            }

            else if (opcion == 3) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del empleado que quiere eliminar:"));
                boolean eliminado = yoga.eliminarEmpleado(id);

                if (!eliminado) {
                    eliminado = baile.eliminarEmpleado(id);
                }

                if (eliminado) {
                    JOptionPane.showMessageDialog(null, "Inscripción eliminada.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró ninguna inscripción con ese ID.");
                }
            }

            else if (opcion == 4) {
                String lista = "Clase de Yoga:\n";
                if (yoga.getCantidadInscritos() == 0) {
                    lista += "(sin inscritos)\n";
                } else {
                    for (int i = 0; i < yoga.getCantidadInscritos(); i++) {
                        lista += "- " + yoga.getInscritos()[i].getNombre() + "\n";
                    }
                }

                lista += "Clase de Baile:\n";
                if (baile.getCantidadInscritos() == 0) {
                    lista += "(sin inscritos)\n";
                } else {
                    for (int i = 0; i < baile.getCantidadInscritos(); i++) {
                        lista += "- " + baile.getInscritos()[i].getNombre() + "\n";
                    }
                }

                JOptionPane.showMessageDialog(null, lista);
            }

            else if (opcion == 5) {
                JOptionPane.showMessageDialog(null, "Saliendo del módulo de clases...");
            }

            else {
                JOptionPane.showMessageDialog(null, "Opción inválida. Intente nuevamente.");
            }
        }
    }
} 

