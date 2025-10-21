/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.forlife;

/**
 *
 * @author lcast
 */
public class Empleado {
    private int id;
    private String nombre;
    private String departamento;
    
    public Empleado (int id, String nombre, String departamento){
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
    }
    
    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public String getDepartamento() {return departamento;}
    
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDepartamento(String departamento) { this.departamento = departamento;}
}
