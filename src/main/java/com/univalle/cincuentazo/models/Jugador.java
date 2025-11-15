package com.univalle.cincuentazo.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un jugador en el juego Cincuentazo.
 * Puede ser humano o máquina y tiene una mano de cartas.
 */

public class Jugador {

    private final String nombre;
    private final List<Carta> mano;
    private boolean esMaquina;

    /**
     * Constructor para crear un jugador
     */

    public Jugador(String nombre, boolean esMaquina){
        this.nombre = nombre;
        this.esMaquina = esMaquina;
        this.mano = new ArrayList<>();
    }

    /**
     * Añade una carta a la mano del jugador
     */
    public void agregarCarta(Carta carta){
        mano.add(carta);
    }

    /**
     * Juega (retira) una carta de la mano por indice
     */
    public Carta jugarCarta(int indice) throws IndexOutOfBoundsException{
        if (indice < 0 || indice >= mano.size()){
            throw new IndexOutOfBoundsException("Indice de carta invalido");
        }
        return mano.remove(indice);
    }

    /**
     * Devuelve las cartas actuales del jugador
     */
    public List<Carta> getMano() {
        return mano;
    }

    /**
     * Muestra el nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Indica si el jugador es maquina
     */
    public boolean isMaquina(){
        return esMaquina;
    }

    /**
     * Cambia el estado del jugador (humano o maquina)
     */
    public void setEsMaquina(boolean esMaquina){
        this.esMaquina = esMaquina;
    }

    /**
     * Devuelve cuantas cartas tiene el jugador
     */
    public int cartasEnMano(){
        return mano.size();
    }

    /**
     * Muestra informacion del jugador
     */
    @Override
    public String toString(){
        return nombre + " (" + (esMaquina ? "Maquina" : "Humano") + ") - " + mano.size()+ " cartas";
    }

}
