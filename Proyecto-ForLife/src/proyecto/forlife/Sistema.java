/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.forlife;

/**
 *
 * @author lcast
 */
public class Sistema {
     private Empleado[] empleados;

    public Sistema() {
        cargarEmpleados();
    }

    private void cargarEmpleados() {
        empleados = new Empleado[40];
        empleados[0] = new Empleado(1, "Harvey Specter", "Recursos Humanos");
        empleados[1] = new Empleado(2, "Axel Road", "TI");
        empleados[2] = new Empleado(3, "Rachel Zane", "Finanzas");
        empleados[3] = new Empleado(4, "Jon Snow", "Marketing");
        empleados[4] = new Empleado(5, "Mike Ross", "Ventas");
        empleados[5] = new Empleado(6, "Jessica Person", "TI");
    }

    public Empleado buscarEmpleadoPorId(int id) {
        for (Empleado e : empleados) {
            if (e != null && e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public Empleado[] getEmpleados() {
        return empleados;
    }
    
}
