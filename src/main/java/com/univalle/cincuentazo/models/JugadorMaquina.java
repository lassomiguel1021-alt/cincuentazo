package com.univalle.cincuentazo.models;

public class JugadorMaquina extends Jugador {

    public JugadorMaquina() {
        super();
    }

    /**
     * Estrategia simple: roba mientras la suma sea < umbral
     * o mientras tenga probabilidad de mejorar sin pasarse.
     * Aquí usamos un umbral conservador.
     */
    public boolean debeRobar(int sumaJugador) {
        // Si la máquina está por debajo de la suma del jugador y < 50, roba
        if (this.getSumaActual() < sumaJugador && this.getSumaActual() < 50) return true;
        // estrategia por umbral fijo (ej: si tiene menos de 36 roba)
        return this.getSumaActual() < 36;
    }
}
