/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.forlife.pkg1.pkg0;

/**
 *
 * @author lcast
 */
public class Gimnasio {

    private String[] horarios;
    private int espaciosPorHora;
    private Empleado[][] reservas;

    public Gimnasio() {
        horarios = new String[] { "14:00", "15:00", "16:00", "17:00", "18:00", "19:00" };
        espaciosPorHora = 2; 
        reservas = new Empleado[horarios.length][espaciosPorHora];
    }

    public String[] getHorarios() {
        return horarios;
    }

    public int getCantidadDeHoras() {
        return horarios.length;
    }

    public int getEspaciosPorHora() {
        return espaciosPorHora;
    }

    public Empleado[][] getReservas() {
        return reservas;
    }

    public boolean empleadoYaTieneReserva(int idEmpleado) {
        for (int i = 0; i < horarios.length; i++) {
            for (int j = 0; j < espaciosPorHora; j++) {
                if (reservas[i][j] != null && reservas[i][j].getId() == idEmpleado) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean reservar(int indiceHora, int indiceEspacio, Empleado empleado) {
        if (empleado == null) {
            return false;
        }
        if (indiceHora < 0 || indiceHora >= horarios.length) {
            return false;
        }
        if (indiceEspacio < 0 || indiceEspacio >= espaciosPorHora) {
            return false;
        }
        if (empleadoYaTieneReserva(empleado.getId())) {
            return false;
        }
        if (reservas[indiceHora][indiceEspacio] == null) {
            reservas[indiceHora][indiceEspacio] = empleado;
            return true;
        }
        return false;
    }

    public boolean cancelarReservaPorEmpleado(int idEmpleado) {
        for (int i = 0; i < horarios.length; i++) {
            for (int j = 0; j < espaciosPorHora; j++) {
                if (reservas[i][j] != null && reservas[i][j].getId() == idEmpleado) {
                    reservas[i][j] = null;
                    return true;
                }
            }
        }
        return false;
    }

    public String listarReservas() {
        String texto = "";
        for (int i = 0; i < horarios.length; i++) {
            texto = texto + "Hora " + horarios[i] + ":\n";
            for (int j = 0; j < espaciosPorHora; j++) {
                texto = texto + "   Espacio " + j + ": ";
                if (reservas[i][j] == null) {
                    texto = texto + "(libre)\n";
                } else {
                    texto = texto + reservas[i][j].getId() + " - " + reservas[i][j].getNombre() + "\n";
                }
            }
            texto = texto + "\n";
        }
        return texto;
    }
}

