package com.univalle.cincuentazo.models;

import java.util.List;
import java.util.Random;

/**
 * Representa un jugador maquina en el juego cincuentazo
 * Decide automaticamente que carta jugar
 */
public class JugadorMaquina extends Jugador {

    private final Random random = new Random();

    public JugadorMaquina(String nombre){
        super(nombre, true);
    }

    /**
     * La máquina elige qué carta jugar según estas reglas:
     * 1. Busca una carta jugable (que no pase de 50).
     * 2. Si es un As, decide automáticamente si vale 1 o 10.
     * 3. Si ninguna carta evita pasarse, juega la primera carta disponible.
     */
    public Carta elegirCarta(int sumaActualMesa) {
        List<Carta> mano = getMano();

        // 1. Buscar una carta jugable
        for (Carta c : mano) {
            int valor = calcularValorParaMaquina(c, sumaActualMesa);

            if (sumaActualMesa + valor <= 50) {
                mano.remove(c);
                return c;
            }
        }

        // 2. si ninguna carta sirve, juega la primera
        return mano.remove(0);
    }

        /**
         * Decide el valor que usará la máquina.
         * Para As:
         *   → usa 10 si no se pasa
         *   → si se pasa, usa 1
         */
    public int calcularValorParaMaquina(Carta carta, int sumaActualMesa) {
        int base = carta.getValorBase();

        if (base != -1){
            // No es As
            return base;
        }

        //Es As
        return (sumaActualMesa + 10 <= 50) ? 10:1;
    }
}
