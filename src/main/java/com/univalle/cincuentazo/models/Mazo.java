package com.univalle.cincuentazo.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Mazo {
    private final List<Carta> cartas;

    public Mazo() {
        cartas = new ArrayList<>();
        crearMazo();
        mezclar();
    }

    private void crearMazo() {
        // Creamos un mazo simple: valores 1..10 por 4 palos (como ejemplo)
        String[] palos = {"Corazones", "Diamantes", "Treboles", "Picas"};
        String[] nombres = {"As","2","3","4","5","6","7","8","9","10"};
        int[] valores =    {1,  2,  3,  4,  5,  6,  7,  8,  9,  10};
        for (String palo : palos) {
            for (int i = 0; i < nombres.length; i++) {
                cartas.add(new Carta(nombres[i], palo, valores[i]));
            }
        }
    }

    public void mezclar() {
        Collections.shuffle(cartas, new Random());
    }

    public boolean estaVacio() {
        return cartas.isEmpty();
    }

    public Carta sacarCarta() throws NoHayCartasException {
        if (estaVacio()) throw new NoHayCartasException("No hay cartas en el mazo");
        return cartas.remove(0);
    }

    public int cartasRestantes() {
        return cartas.size();
    }
}
