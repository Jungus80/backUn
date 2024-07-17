package Cine;

public class Asientos {
    private String identificador;
    private boolean ocupado;
    private int price;
    private String row;

    public Asientos(String identificador, boolean ocupado, int price, String row) {
        this.identificador = identificador;
        this.ocupado = ocupado;
        this.price = price;
        this.row = row;
    }

    // Getters y setters
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }
}


