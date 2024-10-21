    package com.mytetris.tetris;

    import java.awt.Color;

    /**
    * Clase que representa una pieza del juego Tetris.
    */
    public class Pieza {
    private boolean[][] forma; // Matriz que representa la forma de la pieza
    private Color color;       // Color de la pieza

    public Pieza(boolean[][] forma, Color color) {
        this.forma = forma;
        this.color = color;
    }

    /**
     * Rota la pieza 90 grados en sentido horario.
     */
    public void rotarDerecha() {
        boolean[][] nuevaForma = new boolean[forma[0].length][forma.length];
        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[i].length; j++) {
                nuevaForma[j][forma.length - 1 - i] = forma[i][j];
            }
        }
        forma = nuevaForma;
    }

    /**
     * Rota la pieza 90 grados en sentido antihorario.
     */
    public void rotarIzquierda() {
        boolean[][] nuevaForma = new boolean[forma[0].length][forma.length];
        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[i].length; j++) {
                nuevaForma[forma[0].length - 1 - j][i] = forma[i][j];
            }
        }
        forma = nuevaForma;
    }

    /**
     * Rota la pieza 90 grados en sentido horario o antihorario.
     * @param sentido true para rotar a la derecha, false para rotar a la izquierda.
     */
    public void rotar(boolean sentido) {
        if (sentido) {
            rotarDerecha(); // Rota a la derecha si sentido es true
        } else {
            rotarIzquierda(); // Rota a la izquierda si sentido es false
        }
    }

    /**
     * Devuelve la forma de la pieza.
     */
    public boolean[][] getForma() {
        return forma;
    }

    /**
     * Devuelve el color de la pieza.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Método estático para generar las piezas del juego Tetris.
     */
    public static Pieza generarPieza(char tipo) {
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
