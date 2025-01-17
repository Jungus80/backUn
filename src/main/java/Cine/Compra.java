package Cine;

import java.util.List;

public class Compra {
    private int numeroOrden;
    private String time;
    private String nombrePelicula;
    private List<String> asientos;
    private String nombreCompleto;
    private String correo;
    private int total; // Nuevo atributo para el total de la compra

    public Compra(int numeroOrden, String nombrePelicula, List<String> asientos, String nombreCompleto, String correo, String time, int total) {
        this.numeroOrden = numeroOrden;
        this.nombrePelicula = nombrePelicula;
        this.asientos = asientos;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.time = time;
        this.total = total; // Inicializar el nuevo atributo
    }

    // Getters y setters
    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public List<String> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<String> asientos) {
        this.asientos = asientos;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
