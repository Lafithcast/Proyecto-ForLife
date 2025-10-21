/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.forlife;

/**
 *
 * @author lcast
 */
public class Clase {
 private final String nombre;
    private final String hora;
    private final int cupoMaximo;
    private int cantidadDeInscritos;
    private final Empleado[] inscritos;

    public Clase(String nombre, String hora, int cupoMaximo) {
        this.nombre = nombre;
        this.hora = hora;
        this.cupoMaximo = cupoMaximo;
        this.inscritos = new Empleado[cupoMaximo];
        this.cantidadDeInscritos = 0;
    }

    public String getNombre() { return nombre; }
    public String getHora() { return hora; }
    public int getCupoMaximo() { return cupoMaximo; }
    public int getCantidadInscritos() { return cantidadDeInscritos; }
    public Empleado[] getInscritos() { return inscritos; }

    
    public boolean inscribirEmpleado(Empleado e) {
        if (cantidadDeInscritos < cupoMaximo) {
            inscritos[cantidadDeInscritos] = e;
            cantidadDeInscritos++;
            return true;
        }
        return false; // en caso de que se llene la clase
    }

    public boolean eliminarEmpleado(int id) {
        for (int i = 0; i < cantidadDeInscritos; i++) {
            if (inscritos[i] != null && inscritos[i].getId() == id) {
                for (int j = i; j < cantidadDeInscritos - 1; j++) {
                    inscritos[j] = inscritos[j + 1];
                }
                inscritos[cantidadDeInscritos - 1] = null;
                cantidadDeInscritos--;
                return true;
            }
        }
        return false; // por si no encontro al empleado, o sea no existe
    }
}
