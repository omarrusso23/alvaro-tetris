package com.mytetris.tetris;

import java.awt.Color;

/**
 * Clase que representa el tablero del juego Tetris.
 */

public class Tablero {
    private boolean[][] matriz; // Matriz que representa el tablero
    private int filas; // Número de filas del tablero
    private int columnas; // Número de columnas del tablero
    private Color[][] colores; // Matriz para almacenar los colores de las piezas
    private int filasTerminadas; // Número de filas terminadas

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new boolean[filas][columnas];
        filasTerminadas=0;
    }

    public boolean puedeColocar(Pieza pieza, int fila, int columna) {
        // Verifica si la pieza se puede colocar
        for (int i = 0; i < pieza.getForma().length; i++) {
            for (int j = 0; j < pieza.getForma()[i].length; j++) {
                if (pieza.getForma()[i][j]) {
                    if (fila + i < 0 || fila + i >= filas || columna + j < 0 || columna + j >= columnas || matriz[fila + i][columna + j]) {
                        return false; // No se puede colocar
                    }
                }
            }
        }
        return true; // Se puede colocar
    }

    public void colocarPieza(Pieza pieza, int fila, int columna) {
        // Coloca la pieza en el tablero
        for (int i = 0; i < pieza.getForma().length; i++) {
            for (int j = 0; j < pieza.getForma()[i].length; j++) {
                if (pieza.getForma()[i][j]) {
                    matriz[fila + i][columna + j] = true; // Marca la posición como ocupada
                }
            }
        }
    }

    public void eliminarLineasCompletas() {
        for (int i = filas - 1; i >= 0; i--) {
            boolean lineaCompleta = true;
            for (int j = 0; j < columnas; j++) {
                if (!matriz[i][j]) {
                    lineaCompleta = false;
                    break;
                }
            }
            if (lineaCompleta) {
                for (int k = i; k > 0; k--) {
                    matriz[k] = matriz[k - 1]; // Baja las líneas superiores
                }
                matriz[0] = new boolean[columnas]; // Rellena la nueva línea en la parte superior
                i++; // Verifica la nueva línea en la posición actual
            }
        }
    }

    public boolean[][] getMatriz() {
        return matriz;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
    
    public Color[][] getColores() {
        return colores;
    }
    
    public int getFilasTerminadas(){
        return filasTerminadas;
    }
}