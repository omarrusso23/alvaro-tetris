package com.mytetris.tetris;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Clase que representa la cola de piezas en el juego Tetris.
 */
public class ColaDePiezas {
    private Queue<Pieza> cola;
    private Random random;

    public ColaDePiezas() {
        this.cola = new LinkedList<>();
        this.random = new Random();
        // Generar las piezas iniciales
        inicializarCola();
    }

    private void inicializarCola() {
        // Puedes añadir algunas piezas iniciales a la cola
        cola.add(generarPiezaAleatoria()); // Genera una pieza aleatoria
        cola.add(generarPiezaAleatoria());
        cola.add(generarPiezaAleatoria());
        cola.add(generarPiezaAleatoria());
    }

    public Pieza obtenerSiguientePieza() {
        if (estaVacia()) {
            // Genera una nueva pieza aleatoria si la cola está vacía
            añadirPieza(generarPiezaAleatoria());
        }
        return cola.poll(); // Obtener y eliminar la cabeza de la cola
    }

    public void añadirPieza(Pieza pieza) {
        cola.add(pieza);
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    private Pieza generarPiezaAleatoria() {
        char[] tiposPiezas = {'I', 'O', 'T', 'L', 'J', 'S', 'Z'};
        char tipoAleatorio = tiposPiezas[random.nextInt(tiposPiezas.length)];
        return generarPieza(tipoAleatorio);
    }

    private Pieza generarPieza(char tipo) {
        switch (tipo) {
            case 'I':
                return new Pieza(new boolean[][] {
                    { true, true, true, true }
                }, Color.CYAN);
            case 'O':
                return new Pieza(new boolean[][] {
                    { true, true },
                    { true, true }
                }, Color.YELLOW);
            case 'T':
                return new Pieza(new boolean[][] {
                    { false, true, false },
                    { true, true, true }
                }, Color.MAGENTA);
            case 'L':
                return new Pieza(new boolean[][] {
                    { true, false },
                    { true, false },
                    { true, true }
                }, Color.ORANGE);
            case 'J':
                return new Pieza(new boolean[][] {
                    { false, true },
                    { false, true },
                    { true, true }
                }, Color.BLUE);
            case 'S':
                return new Pieza(new boolean[][] {
                    { false, true, true },
                    { true, true, false }
                }, Color.GREEN);
            case 'Z':
                return new Pieza(new boolean[][] {
                    { true, true, false },
                    { false, true, true }
                }, Color.RED);
            default:
                throw new IllegalArgumentException("Tipo de pieza no válido");
        }
    }
}
