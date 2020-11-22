package modelo;

/**
 *
 * @author seiko
 */
public class Clientes {

    int id_cliente;
    String rut_cliente;
    String nombre_cliente;
    String correo_cliente;
    String telefono_cliente;
    String direccion_cliente;

    public Clientes() {
    }

    public Clientes(int id_cliente, String rut_cliente, String nombre_cliente, String correo_cliente, String telefono_cliente, String direccion_cliente) {
        this.id_cliente = id_cliente;
        this.rut_cliente = rut_cliente;
        this.nombre_cliente = nombre_cliente;
        this.correo_cliente = correo_cliente;
        this.telefono_cliente = telefono_cliente;
        this.direccion_cliente = direccion_cliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    
    public String getRut_cliente() {
        return rut_cliente;
    }

    public void setRut_cliente(String rut_cliente) {
        this.rut_cliente = rut_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getCorreo_cliente() {
        return correo_cliente;
    }

    public void setCorreo_cliente(String correo_cliente) {
        this.correo_cliente = correo_cliente;
    }

    public String getTelefono_cliente() {
        return telefono_cliente;
    }

    public void setTelefono_cliente(String telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

}
