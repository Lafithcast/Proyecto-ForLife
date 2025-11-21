/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.forlife.pkg1.pkg0;

/**
 *
 * @author lcast
 */
public class Cafeteria {

    private String[] menuBebidas;
    private int[] idsEmpleados;
    private String[] bebidasElegidas;
    private String[] horasEntrega;
    private int cantidadDePedidos;

    public Cafeteria(int capacidadMaximaPedidos) {
        menuBebidas = new String[] {
            "Café normal",
            "Capuchino",
            "Capuchino con vainilla",
            "Chocolate",
            "Moka",
            "Té chai",
            "Café frío"
        };

        idsEmpleados = new int[capacidadMaximaPedidos];
        bebidasElegidas = new String[capacidadMaximaPedidos];
        horasEntrega = new String[capacidadMaximaPedidos];
        cantidadDePedidos = 0;
    }

    public String[] getMenuBebidas() {
        return menuBebidas;
    }

    private int buscarPedidoPorId(int idEmpleado) {
        for (int i = 0; i < cantidadDePedidos; i++) {
            if (idsEmpleados[i] == idEmpleado) {
                return i;
            }
        }
        return -1;
    }

    public boolean registrarPedido(int idEmpleado, String bebida, String hora) {
        int indice = buscarPedidoPorId(idEmpleado);
        if (indice != -1) {
            return false; 
        }
        if (cantidadDePedidos >= idsEmpleados.length) {
            return false; 
        }

        idsEmpleados[cantidadDePedidos] = idEmpleado;
        bebidasElegidas[cantidadDePedidos] = bebida;
        horasEntrega[cantidadDePedidos] = hora;
        cantidadDePedidos = cantidadDePedidos + 1;
        return true;
    }

    public boolean modificador(int idEmpleado, String nuevaBebida, String nuevaHora) {
        int indice = buscarPedidoPorId(idEmpleado);
        if (indice == -1) {
            return false;
        }
        bebidasElegidas[indice] = nuevaBebida;
        horasEntrega[indice] = nuevaHora;
        return true;
    }

    public String listarPedidos() {
        String texto = "";
        for (int i = 0; i < cantidadDePedidos; i++) {
            texto = texto + "ID: " + idsEmpleados[i]
                    + " | Bebida: " + bebidasElegidas[i]
                    + " | Hora: " + horasEntrega[i] + "\n";
        }
        return texto;
    }
}

