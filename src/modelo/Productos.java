/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author joako
 */
public class Productos {

    int sku;
    String codigo;
    String nombre;
    String descripcion;
    double precio;
   
    int codPromo;

    public Productos() {
    }

    public Productos(int sku, String codigo, String nombre, String descripcion, double precio, int codPromo) {
        this.sku = sku;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    
        this.codPromo = codPromo;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

   
    public int getCodPromo() {
        return codPromo;
    }

    public void setCodPromo(int codPromo) {
        this.codPromo = codPromo;
    }
    
    
    
    
    
    
    
    
}
