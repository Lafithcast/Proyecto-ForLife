/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.forlife.pkg1.pkg0;

/**
 *
 * @author lcast
 */
public class GestorCine {

    private SalaCine[] salas;

    public GestorCine(int cantidadDeSalas) {
        salas = new SalaCine[cantidadDeSalas];

        for (int i = 0; i < salas.length; i++) {
            salas[i] = new SalaCine("Película " + (i + 1), 5, 5);
        }
    }

    public int getCantidadDeSalas() {
        return salas.length;
    }

    public SalaCine getSala(int indiceSala) {
        if (indiceSala < 0 || indiceSala >= salas.length) {
            return null;
        }
        return salas[indiceSala];
    }

    public void cambiarPelicula(int indiceSala, String nuevaPelicula) {
        SalaCine sala = getSala(indiceSala);
        if (sala != null) {
            sala.setNombrePelicula(nuevaPelicula);
        }
    }

    public void cambiarTamanoSala(int indiceSala, int nuevasFilas, int nuevasColumnas) {
        SalaCine sala = getSala(indiceSala);
        if (sala != null) {
            String peliculaActual = sala.getNombrePelicula();
            salas[indiceSala] = new SalaCine(peliculaActual, nuevasFilas, nuevasColumnas);
        }
    }

    public SalaCine[] getSalas() {
        return salas;
    }
}
