package com.univalle.cincuentazo.models;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    protected final List<Carta> mano;
    protected int sumaActual;

    public Jugador() {
        mano = new ArrayList<>();
        sumaActual = 0;
    }

    public void recibirCarta(Carta c) {
        mano.add(c);
        sumaActual += c.getValorNumerico();
    }

    public List<Carta> getMano() {
        return mano;
    }

    public int getSumaActual() {
        return sumaActual;
    }

    public void reiniciar() {
        mano.clear();
        sumaActual = 0;
    }

    public String manoToString() {
        StringBuilder sb = new StringBuilder();
        for (Carta c : mano) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(c.toString());
        }
        return sb.toString();
    }
}
