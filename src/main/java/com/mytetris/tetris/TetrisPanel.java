package com.mytetris.tetris;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Clase que representa el panel del juego Tetris.
 */
public class TetrisPanel extends JPanel implements ActionListener {
    private Tablero tablero;
    private ColaDePiezas colaDePiezas;
    private Timer timer;
    private Pieza piezaActual;
    private int nivel;
    private int lineasCompletadas;
    private int piezasColocadas;
    private JLabel estadoJuego;
    private boolean juegoActivo;
    private int piezaFila;      // Posición actual de la fila de la pieza
    private int piezaColumna;   // Posición actual de la columna de la pieza
    private static final int VELOCIDAD_NORMAL = 900; // Tiempo en milisegundos
    private static final int VELOCIDAD_RAPIDA = 100; // Tiempo en milisegundos
    private boolean spacePressed = false; // Para verificar si la tecla de espacio está presionada

    public TetrisPanel() {
        this.tablero = new Tablero(20, 10); // Tablero de 20 filas y 10 columnas
        this.colaDePiezas = new ColaDePiezas();
        this.piezaActual = colaDePiezas.obtenerSiguientePieza();
        this.piezaFila = 0;      // Inicia en la parte superior
        this.piezaColumna = 4;   // Inicia en el centro del tablero
        this.nivel = 1;
        this.lineasCompletadas = 0;
        this.piezasColocadas = 0;
        this.estadoJuego = new JLabel("Jugando");
        this.juegoActivo = true;

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(531, 597));

        // Configurar temporizador para el juego (1 segundo entre movimientos)
        timer = new Timer(VELOCIDAD_NORMAL, this);
        timer.start();

        // Crear panel para el Tetris
        JPanel tetrisPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarTablero(g);
                dibujarPieza(g, piezaActual, piezaFila, piezaColumna); // Dibuja la pieza en su posición actual
            }
        };
        tetrisPanel.setPreferredSize(new Dimension(300, 600));
        tetrisPanel.setBackground(Color.WHITE);
        Border border = BorderFactory.createLineBorder(Color.BLUE, 2); // Borde azul
        tetrisPanel.setBorder(border);

        // Hacer que el panel sea receptivo a los eventos de teclado
        tetrisPanel.setFocusable(true);
        tetrisPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (juegoActivo) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            if (tablero.puedeColocar(piezaActual, piezaFila, piezaColumna - 1)) {
                                piezaColumna--; // Mover la pieza a la izquierda
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (tablero.puedeColocar(piezaActual, piezaFila, piezaColumna + 1)) {
                                piezaColumna++; // Mover la pieza a la derecha
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            piezaActual.rotar(false); // Rota a la izquierda
                            break;
                        case KeyEvent.VK_UP:
                            piezaActual.rotar(true); // Rota a la derecha
                            break;
                        case KeyEvent.VK_SPACE:
                            spacePressed = true; // Marcar que la tecla de espacio está presionada
                            timer.setDelay(VELOCIDAD_RAPIDA); // Cambiar a velocidad rápida
                            break;
                    }
                    tetrisPanel.repaint(); // Cambia repaint a tetrisPanel
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    spacePressed = false; // Marcar que la tecla de espacio no está presionada
                    timer.setDelay(VELOCIDAD_NORMAL); // Volver a la velocidad normal
                }
            }
        });

        // Crear panel para la lista de próximas piezas
        JPanel listaPiezasPanel = new JPanel();
        listaPiezasPanel.setLayout(new GridLayout(4, 1));
        Border borderPiezas = BorderFactory.createLineBorder(Color.RED, 3);
        listaPiezasPanel.setBorder(borderPiezas);
        for (int i = 0; i < 4; i++) {
            listaPiezasPanel.add(new JLabel("Pieza " + (i + 1))); // Aquí puedes agregar la lógica para mostrar las piezas reales
        }

        // Crear panel para los contadores
        JPanel contadoresPanel = new JPanel();
        contadoresPanel.setLayout(new GridLayout(6, 1));
        contadoresPanel.setBorder(border);
        contadoresPanel.add(new JLabel("Nivel: " + nivel));
        contadoresPanel.add(new JLabel("Líneas: " + lineasCompletadas));
        contadoresPanel.add(new JLabel("Piezas colocadas: " + piezasColocadas));
        contadoresPanel.add(estadoJuego);
        JButton reiniciarButton = new JButton("Reiniciar");
        reiniciarButton.addActionListener(e -> reiniciarJuego());
        contadoresPanel.add(reiniciarButton);

        // Añadir los paneles al panel principal
        add(tetrisPanel, BorderLayout.WEST);
        add(listaPiezasPanel, BorderLayout.CENTER);
        add(contadoresPanel, BorderLayout.EAST);

        // Configuración inicial
        setBackground(Color.GRAY);
    }

    /**
     * Reinicia el juego Tetris.
     */
    private void reiniciarJuego() {
        tablero = new Tablero(20, 10);
        colaDePiezas = new ColaDePiezas();
        piezaActual = colaDePiezas.obtenerSiguientePieza();
        piezaFila = 0;
        piezaColumna = 4;
        nivel = 1;
        lineasCompletadas = 0;
        piezasColocadas = 0;
        estadoJuego.setText("Jugando");
        juegoActivo = true;
        timer.start();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (juegoActivo) {
            movePieceDown(); // Llama al método para mover la pieza hacia abajo
        }
    }

    /**
     * Mueve la pieza actual hacia abajo.
     */
    void movePieceDown() {
        if (juegoActivo) {
            // Lógica para mover la pieza hacia abajo
            if (tablero.puedeColocar(piezaActual, piezaFila + 1, piezaColumna)) {
                piezaFila++; // Mover la pieza hacia abajo
            } else {
                // Colocar la pieza en el tablero y generar una nueva
                tablero.colocarPieza(piezaActual, piezaFila, piezaColumna);
                tablero.eliminarLineasCompletas();
                piezasColocadas++;
                piezaActual = colaDePiezas.obtenerSiguientePieza();
                piezaFila = 0;
                piezaColumna = 4;

                // Verificar si hay espacio para la nueva pieza; si no, termina el juego
                if (!tablero.puedeColocar(piezaActual, piezaFila, piezaColumna)) {
                    estadoJuego.setText("Perdiste");
                    juegoActivo = false;
                    timer.stop();
                }
            }
            repaint();
        }
    }

    /**
     * Dibuja el tablero de Tetris.
     */
    private void dibujarTablero(Graphics g) {
        boolean[][] matriz = tablero.getMatriz();
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                if (matriz[i][j]) {
                    g.fillRect(j * 30, i * 30, 30, 30); // Tamaño de cada bloque
                }
            }
        }
    }

    /**
     * Dibuja una pieza de Tetris en la posición dada.
     */
    private void dibujarPieza(Graphics g, Pieza pieza, int fila, int columna) {
        g.setColor(pieza.getColor());
        for (int i = 0; i < pieza.getForma().length; i++) {
            for (int j = 0; j < pieza.getForma()[i].length; j++) {
                if (pieza.getForma()[i][j]) {
                    g.fillRect((columna + j) * 30, (fila + i) * 30, 30, 30);
                }
            }
        }
    }
}
