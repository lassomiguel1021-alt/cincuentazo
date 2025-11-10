package com.univalle.cincuentazo.models;

public class Carta {

    private final String rango;
    private final String palo;
    private final String imagen;

    public Carta(String rango, String palo, String imagen) {
        this.rango = rango;
        this.palo = palo;
        this.imagen = imagen;
    }

    public Carta(String rango, String palo) {
        this(rango, palo, null);
    }

    // --- Getters ---
    public String getRango() {
        return rango;
    }
    public String getPalo() {
        return palo;
    }
    public String getImagen() {
        return imagen;
    }

    public int getValorReal(int sumaMesaActual){
        String r = rango.trim().toUpperCase();

        try {
            int numero = Integer.parseInt(r);
            if (numero == 9) return 0;
            return numero;
        } catch (NumberFormatException e) {
            //No es numero entonces es letra
        }

        switch (r){
            case "J":
            case "Q":
            case "K":
                return -10;
            case "A":
                return (sumaMesaActual + 10 <= 50) ? 10 : 1;
            default:
                return 0;
        }
    }

    public boolean esJugable(int sumaMesaActual){
        return sumaMesaActual + getValorReal(sumaMesaActual) <= 50;
    }

    @Override
    public String toString(){
        return rango + " de " + palo;
    }
}
