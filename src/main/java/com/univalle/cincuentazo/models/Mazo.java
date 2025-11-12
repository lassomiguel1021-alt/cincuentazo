package com.univalle.cincuentazo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Mazo {

    private final List<Carta> cartas; //Lista de cartas disponibles
    /*
    * Constructor: Crea el mazo de todas las cartas y lo baraja
    */
    public Mazo(){
        this.cartas = new ArrayList<>();
        inicializarMazo();
        barajar();
    }

    private void inicializarMazo(){
        String[] palos = {"corazones", "picas", "tréboles", "diamantes"};
        String[] rangos = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (String palo : palos){
            for (String rango : rangos){
                cartas.add(new Carta(rango, palo));
            }
        }
    }
    /*
    * Baraja el mazo aleatoriamente
    */
    private void barajar(){
        Collections.shuffle(cartas);
    }

    /**
    * Toma una carta del tope del mazo
    * @throws NoHayCartasException si el mazo está vacío
    */

    public Carta tomarCarta() throws NoHayCartasException {
        if (cartas.isEmpty()) {
            throw new NoHayCartasException("No hay más cartas en el mazo.");
        }
        return cartas.remove(0);
    }

    /**
    * Devuelve el numero de cartas que quedan en el mazo
    */
    public int cartasRestantes(){
        return cartas.size();
    }

    /**
     * Reinserta cartas en el mazo (por ejemplo, cuando se reciclan de la mesa).
     */

    public void agregarCartas(List<Carta> nunevasCartas){
        cartas.addAll(nunevasCartas);
        barajar();
    }

    @Override
    public String toString() {
        return "Mazo con " + cartas.size() + " cartas restantes";
    }

}
