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

    /**
     * Valor base de la carta.
     * NO decide el valor final del As (eso lo hace el jugador o la máquina).
     * Solo indica si necesita decisión especial.
     */
    public int getValorBase(){
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
                return -1;
            default:
                return 0;
        }
    }

    /**
     * Un As NO se evalúa aquí, porque depende del jugador.
     * Se evalúa en Juego según lo que el jugador elija.
     */
    public boolean esJugable(int sumaMesaActual, int valorElegido){
        return sumaMesaActual + valorElegido <= 50;
    }

    @Override
    public String toString(){
        return rango + " de " + palo;
    }
}
