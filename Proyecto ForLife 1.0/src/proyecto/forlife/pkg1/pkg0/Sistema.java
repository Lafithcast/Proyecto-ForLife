/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.forlife.pkg1.pkg0;

/**
 *
 * @author lcast
 */
public class Sistema {

    private Empleado[] empleados;

    public Sistema() {
        empleados = new Empleado[50]; 
        cargarEmpleadosIniciales();
    }

    private void cargarEmpleadosIniciales() {
        empleados[0] = new Empleado(1, "Harvey Specter", "Recursos Humanos");
        empleados[1] = new Empleado(2, "Axel Road", "TI");
        empleados[2] = new Empleado(3, "Rachel Zane", "Finanzas");
        empleados[3] = new Empleado(4, "Jon Snow", "Marketing");
        empleados[4] = new Empleado(5, "Mike Ross", "Ventas");
        empleados[5] = new Empleado(6, "Jessica Pearson", "TI");
    }

    public Empleado buscarEmpleadoPorId(int id) {
        for (int i = 0; i < empleados.length; i++) {
            Empleado empleadoActual = empleados[i];
            if (empleadoActual != null && empleadoActual.getId() == id) {
                return empleadoActual;
            }
        }
        return null; 
    }

    public Empleado[] getEmpleados() {
        return empleados;
    }

    public boolean agregarEmpleado(Empleado nuevoEmpleado) {

        Empleado existente = buscarEmpleadoPorId(nuevoEmpleado.getId());
        if (existente != null) {
            return false; 
        }


        for (int i = 0; i < empleados.length; i++) {
            if (empleados[i] == null) {
                empleados[i] = nuevoEmpleado;
                return true;
            }
        }
        return false; 
    }

    public boolean eliminarEmpleadoPorId(int id) {
        for (int i = 0; i < empleados.length; i++) {
            if (empleados[i] != null && empleados[i].getId() == id) {
                for (int j = i; j < empleados.length - 1; j++) {
                    empleados[j] = empleados[j + 1];
                }
                empleados[empleados.length - 1] = null;
                return true;
            }
        }
        return false;
    }
}

