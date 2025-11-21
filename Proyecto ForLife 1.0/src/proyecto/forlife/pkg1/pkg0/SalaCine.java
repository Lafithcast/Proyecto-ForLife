/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.forlife.pkg1.pkg0;

/**
 *
 * @author lcast
 */
public class SalaCine {

    private String nombrePelicula;
    private int filas;
    private int columnas;
    private Empleado[][] asientos;

    public SalaCine(String nombrePelicula, int filas, int columnas) {
        this.nombrePelicula = nombrePelicula;
        this.filas = filas;
        this.columnas = columnas;
        this.asientos = new Empleado[filas][columnas];
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public Empleado[][] getAsientos() {
        return asientos;
    }

    public boolean yaReservo(int idEmpleado) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (asientos[i][j] != null && asientos[i][j].getId() == idEmpleado) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean reservarAsiento(int fila, int columna, Empleado empleado) {
        if (empleado == null) {
            return false;
        }
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return false;
        }
        if (yaReservo(empleado.getId())) {
            return false;
        }
        if (asientos[fila][columna] == null) {
            asientos[fila][columna] = empleado;
            return true;
        }
        return false;
    }

    public boolean cancelarAsiento(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return false;
        }
        if (asientos[fila][columna] != null) {
            asientos[fila][columna] = null;
            return true;
        }
        return false;
    }

    public int[] obtenerAsientoPorEmpleado(int idEmpleado) {
        int[] posicion = new int[2];
        posicion[0] = -1;
        posicion[1] = -1;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (asientos[i][j] != null && asientos[i][j].getId() == idEmpleado) {
                    posicion[0] = i;
                    posicion[1] = j;
                    return posicion;
                }
            }
        }
        return posicion;
    }
}

