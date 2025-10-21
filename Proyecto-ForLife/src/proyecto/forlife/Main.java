/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto.forlife;

import javax.swing.JOptionPane;

/**
 *
 * @author lcast
 */
public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        int opcion = 0;
        
        while (opcion !=5){
            String menu = "=== ForLife ==\n";
            menu +="1. Modulo de yoga y baile\n";
            menu +="2. Menu de GYM\n";
            menu +="3. Menu de Cafeteria\n";
            menu +="4. Menu de Cine\n";
            menu +="5. Salir\n";
            menu +="Seleccione una opcion por favor: ";
            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
            
            if (opcion == 1){
                ClaseMenu.main();
            }
            else if (opcion == 2){
                JOptionPane.showMessageDialog(null, "Aquí va el de gym️");
            }
            else if (opcion == 3){
                JOptionPane.showMessageDialog(null, "Aquí va la cafeteria");
            }
            else if (opcion == 4){
                JOptionPane.showMessageDialog(null, "Aquí va el cine️");
            }
            JOptionPane.showMessageDialog(null, "Saliendo del sistema️, gracias por usar!");
        }
    }
}   

        
    
