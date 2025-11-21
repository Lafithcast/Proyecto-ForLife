/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.forlife.pkg1.pkg0;

/**
 *
 * @author lcast
 */
public class Actividad {

    private String nombre;
    private String horario;
    private int cupoMaximo;
    private Empleado[] empleadosInscritos;
    private int cantidadDeInscritos;

    public Actividad(String nombre, String horario, int cupoMaximo) {
        this.nombre = nombre;
        this.horario = horario;
        this.cupoMaximo = cupoMaximo;
        this.empleadosInscritos = new Empleado[cupoMaximo];
        this.cantidadDeInscritos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHorario() {
        return horario;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public int getCantidadDeInscritos() {
        return cantidadDeInscritos;
    }

    public Empleado[] getEmpleadosInscritos() {
        return empleadosInscritos;
    }

    public boolean inscribirEmpleado(Empleado empleado) {
        if (empleado == null) {
            return false;
        }
        if (cantidadDeInscritos >= cupoMaximo) {
            return false; 
        }

        for (int i = 0; i < cantidadDeInscritos; i++) {
            if (empleadosInscritos[i].getId() == empleado.getId()) {
                return false; // Esto es por si ya estaba inscrito, asi evitamos que hayan 2 inscritos
            }
        }

        empleadosInscritos[cantidadDeInscritos] = empleado;
        cantidadDeInscritos = cantidadDeInscritos + 1;
        return true;
    }

    public boolean eliminarEmpleado(int idEmpleado) {
        for (int i = 0; i < cantidadDeInscritos; i++) {
            if (empleadosInscritos[i].getId() == idEmpleado) {
                for (int j = i; j < cantidadDeInscritos - 1; j++) {
                    empleadosInscritos[j] = empleadosInscritos[j + 1];
                }
                empleadosInscritos[cantidadDeInscritos - 1] = null;
                cantidadDeInscritos = cantidadDeInscritos - 1;
                return true;
            }
        }
        return false;
    }
}

