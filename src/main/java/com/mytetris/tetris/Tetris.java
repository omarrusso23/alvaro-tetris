package com.mytetris.tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Clase principal que inicia el juego Tetris.
 */

public class Tetris {
 public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");
        TetrisPanel tetrisPanel = new TetrisPanel();
        
        // Agregar un Timer para hacer que las piezas caigan
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetrisPanel.movePieceDown(); // Mueve la pieza hacia abajo automáticamente
            }
        });
        timer.start();
        
        frame.add(tetrisPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
