package Cine;

import java.util.ArrayList;
import java.util.List;

public class Pelicula {
    //cada pelicula tendra un id
    private int id;
    private String nombre;
    // cada pelicula tiene tandas

    private List<Tanda> tandas;

    public  Pelicula  (int id, String nombre){
        this.id = id; // identificador de pelicula
        this.nombre = nombre;
        this.tandas  =  new ArrayList<>(); // recibe un array del formato
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Tanda> getTandas() {
        return tandas;
    }

    public void setTandas(List<Tanda> tandas) {
        this.tandas = tandas; //  aqui puedo hacer ell set  pero debo colocar todo.
    }


}
