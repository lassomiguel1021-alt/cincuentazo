package com.univalle.cincuentazo;

import com.univalle.cincuentazo.models.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    @FXML private Label lblMazo;
    @FXML private Label lblJugadorSuma;
    @FXML private Label lblMaquinaSuma;
    @FXML private TextArea txtLog;
    @FXML private Button btnRepartir;
    @FXML private Button btnPlantar;
    @FXML private ListView<String> lstJugador;
    @FXML private ListView<String> lstMaquina;

    private Mazo mazo;
    private Jugador jugador;
    private JugadorMaquina maquina;
    private boolean juegoTerminado = false;

    @FXML
    public void initialize() {
        reiniciarJuego();
    }

    private void reiniciarJuego() {
        mazo = new Mazo();
        jugador = new Jugador();
        maquina = new JugadorMaquina();
        juegoTerminado = false;
        lblMazo.setText("Cartas: " + mazo.cartasRestantes());
        lblJugadorSuma.setText("Jugador: 0");
        lblMaquinaSuma.setText("Máquina: 0");
        txtLog.clear();
        lstJugador.getItems().clear();
        lstMaquina.getItems().clear();
        btnRepartir.setDisable(false);
        btnPlantar.setDisable(false);
        log("Juego reiniciado. Presiona Repartir para tomar una carta.");
    }

    private void log(String s) {
        txtLog.appendText(s + "\n");
    }

    @FXML
    protected void onRepartir() {
        if (juegoTerminado) {
            reiniciarJuego();
            return;
        }

        try {
            Carta c = mazo.sacarCarta();
            jugador.recibirCarta(c);
            lstJugador.getItems().add(c.toString());
            lblJugadorSuma.setText("Jugador: " + jugador.getSumaActual());
            lblMazo.setText("Cartas: " + mazo.cartasRestantes());
            log("Jugador recibe: " + c.toString());

            if (jugador.getSumaActual() == 50) {
                log("¡Jugador hace 50! Jugador gana.");
                terminarJuego("Jugador");
                return;
            } else if (jugador.getSumaActual() > 50) {
                log("Jugador se pasó (" + jugador.getSumaActual() + "). Máquina gana.");
                terminarJuego("Máquina");
                return;
            }

        } catch (NoHayCartasException ex) {
            log("Mazo vacío. No se puede repartir.");
            terminarJuego("Empate (mazo vacío)");
        }
    }

    @FXML
    protected void onPlantar() {
        if (juegoTerminado) {
            reiniciarJuego();
            return;
        }

        log("Jugador planta con " + jugador.getSumaActual() + ". Turno de la máquina.");
        // Turno de la máquina: roba mientras su estrategia diga que debe
        while (true) {
            if (maquina.getSumaActual() == 50) {
                log("Máquina hace 50!");
                terminarJuego("Máquina");
                return;
            } else if (maquina.getSumaActual() > 50) {
                log("Máquina se pasó (" + maquina.getSumaActual() + "). Jugador gana.");
                terminarJuego("Jugador");
                return;
            }

            boolean debeRobar = maquina.debeRobar(jugador.getSumaActual());
            if (!debeRobar) break;

            try {
                Carta c = mazo.sacarCarta();
                maquina.recibirCarta(c);
                lstMaquina.getItems().add(c.toString());
                lblMaquinaSuma.setText("Máquina: " + maquina.getSumaActual());
                lblMazo.setText("Cartas: " + mazo.cartasRestantes());
                log("Máquina recibe: " + c.toString());

            } catch (NoHayCartasException e) {
                log("Mazo vacío durante turno de la máquina.");
                break;
            }
        }

        // Evaluar ganador final
        int sumJ = jugador.getSumaActual();
        int sumM = maquina.getSumaActual();

        log("Resultados finales -> Jugador: " + sumJ + " | Máquina: " + sumM);

        if (sumM > 50 && sumJ > 50) {
            log("Ambos se pasaron. Empate.");
            terminarJuego("Empate");
        } else if (sumM > 50) {
            terminarJuego("Jugador");
        } else if (sumJ > 50) {
            terminarJuego("Máquina");
        } else {
            // quien esté más cerca de 50 gana
            int difJ = 50 - sumJ;
            int difM = 50 - sumM;
            if (difJ < difM) terminarJuego("Jugador");
            else if (difM < difJ) terminarJuego("Máquina");
            else terminarJuego("Empate");
        }
    }

    private void terminarJuego(String ganador) {
        juegoTerminado = true;
        log("---- Juego terminado. Ganador: " + ganador + " ----");
        btnRepartir.setDisable(false);
        btnPlantar.setDisable(false);
        // Cambiamos textos de los botones para reiniciar al siguiente clic
        btnRepartir.setText("Reiniciar");
        btnPlantar.setText("Reiniciar");
        // mostrar sumas finales
        lblJugadorSuma.setText("Jugador: " + jugador.getSumaActual());
        lblMaquinaSuma.setText("Máquina: " + maquina.getSumaActual());
    }

    @FXML
    protected void onSalir() {
        Platform.exit();
    }

    @FXML
    protected void onReset() {
        reiniciarJuego();
        btnRepartir.setText("Repartir");
        btnPlantar.setText("Plantar");
    }
}
