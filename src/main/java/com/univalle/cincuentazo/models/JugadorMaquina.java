package com.univalle.cincuentazo.models;

/**
 * Representa un jugador maquina en el juego cincuentazo
 * Decide automaticamente que carta jugar
 */
public class JugadorMaquina extends Jugador {

    public JugadorMaquina(String nombre){
        super(nombre, true);
    }

    /**
     * La maquina elige la primera carta dispponible que no haga pasar de 50
     * Si todas hacen pasar de 50 juega la primera.
     */
    public Carta elegirCarta(int sumaActual) {
        for (int i = 0; i < getMano().size(); i++) {
            Carta c = getMano().get(i);
            int valor = c.getValorReal(sumaActual);


            if (sumaActual + valor <= 50) {
                //Juega esta carta
                return getMano().get(i);
            }
        }

        //Si ninguna sirve, juega la primera
        return getMano().remove(0);
    }
}
