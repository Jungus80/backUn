package Cine;

import java.util.ArrayList;
import java.util.List;

public class Tanda {
    private int id;
    private String horario;
    private List<Asientos> asientos;

    public Tanda(int id, String horario) {
        this.id = id;
        this.horario = horario;
        this.asientos = new ArrayList<>();
        inicializarAsientos();
    }

    private void inicializarAsientos() {
        String[] filas = {"A", "B", "C"};
        for (String fila : filas) {
            for (int j = 1; j <= 5; j++) {
                asientos.add(new Asientos(fila + j, false, 10, fila));
            }
        }
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public List<Asientos> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<Asientos> asientos) {
        this.asientos = asientos;
    }
}
