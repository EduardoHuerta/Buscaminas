package buscaminas.mcc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Buscaminas {
    /*
     * El método main
     * @param args los argumentos
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean corriendo = true;
        boolean corriendo2 = true;
        boolean corriendo3 = true;
        boolean lenguaje = true;
        boolean partidaMenu = true;
        int dimensiones = 0;
        int dimensiones2 = 0;
        int numeroMinas = 0;

        while (corriendo3) {
            EscribirRecord gestorGuardado = new EscribirRecord();
            String fileName = "src/Victorias.txt";
            String nombre;

            // Solicitud del nombre de usuario para el record
            System.out.println("Introduce tu nombre:");
            nombre = br.readLine();
            do {
                while (corriendo) {
                    corriendo2 = true;
                    System.out.println("Escoge una opción (a, b, c, d)");
                    System.out.println("a) Empezar partida");
                    System.out.println("b) Ver puntuaciones");
                    System.out.println("c) Cambiar usuario");
                    System.out.println("d) Salir.");

                    String linea = br.readLine();

                    do {
                        // Tableros por defecto o personalizado
                        if (linea.equals("a")) {
                            System.out.println("Introduzca las dimensiones del tablero y la cantidad de minas\n " +
                                    "a) Principiante: 10 minas y 9x9\n" +
                                    "b) Intermedio: 40 minas y 16x16\n" +
                                    "c) Avanzado: 99 minas y 16x30\n" +
                                    "d) Personalizado: introduce tus propios valores\n" +
                                    "e) Volver al menú principal");
                            String lineaPartida = br.readLine();
                            do {
                                if (lineaPartida.equals("a")) {
                                    // Dimensiones del tablero por defecto.
                                    // Se replica en los siguientes
                                    dimensiones = 9;
                                    dimensiones2 = 9;
                                    numeroMinas = 10;
                                    // Inicio del tiempo de juego para el
                                    // record
                                    long inicioJuego = System.nanoTime();
                                    // Creación del tablero de juego
                                    TableroJuego juego = new TableroJuego(dimensiones, dimensiones2, numeroMinas);
                                    // Fin del tiempo de partida
                                    long tiempoJuego = System.nanoTime() - inicioJuego;
                                    // Si la partida terminó en victoria, se escribe el record
                                    if (TableroJuego.record) {
                                        gestorGuardado.gestionarGuardado(tiempoJuego, fileName, nombre);
                                    }
                                }
                                if (lineaPartida.equals("b")) {
                                    dimensiones = 16;
                                    dimensiones2 = 16;
                                    numeroMinas = 40;
                                    long inicioJuego = System.nanoTime();
                                    TableroJuego juego = new TableroJuego(dimensiones, dimensiones2, numeroMinas);
                                    long tiempoJuego = System.nanoTime() - inicioJuego;
                                    if (TableroJuego.record) {
                                        gestorGuardado.gestionarGuardado(tiempoJuego, fileName, nombre);
                                    }
                                }
                                if (lineaPartida.equals("c")) {
                                    dimensiones = 16;
                                    dimensiones2 = 30;
                                    numeroMinas = 99;
                                    long inicioJuego = System.nanoTime();
                                    TableroJuego juego = new TableroJuego(dimensiones, dimensiones2, numeroMinas);
                                    long tiempoJuego = System.nanoTime() - inicioJuego;
                                    if (TableroJuego.record) {
                                        gestorGuardado.gestionarGuardado(tiempoJuego, fileName, nombre);
                                    }
                                }
                                if (lineaPartida.equals("d")) {
                                    // Personalizado, se solicitan los datos al usuario
                                    System.out.println("Introduzca las dimensiones del lado 1 (Hasta 100)");
                                    dimensiones = (Integer.parseInt(br.readLine()));
                                    if (dimensiones > 100) {
                                        System.err.println("Error. Máximo 100, por favor\n");
                                        break;
                                    }
                                    System.out.println("Introduzca las dimensiones del lado 2 (Hasta 100)");
                                    dimensiones2 = (Integer.parseInt(br.readLine()));
                                    if (dimensiones2 > 100) {
                                        System.err.println("Error. Máximo 100, por favor\n");
                                        break;
                                    }
                                    System.out.println("Introduzca el número de minas");
                                    numeroMinas = (Integer.parseInt(br.readLine()));
                                    if (numeroMinas >= dimensiones * dimensiones2) {
                                        System.err.println("Error. Todas las casillas serán minas, la victoria es imposible\n");
                                        break;
                                    }
                                    long inicioJuego = System.nanoTime();
                                    TableroJuego juego = new TableroJuego(dimensiones, dimensiones2, numeroMinas);
                                    long tiempoJuego = System.nanoTime() - inicioJuego;
                                    if (TableroJuego.record) {
                                        gestorGuardado.gestionarGuardado(tiempoJuego, fileName, nombre);
                                    }
                                }
                                if (lineaPartida.equals("e")) {
                                    // Volver al menú principal
                                    partidaMenu = false;
                                    corriendo2 = false;
                                }
                                partidaMenu = false;
                            } while (partidaMenu);

                        }
                        if (linea.equals("b")) {
                            // Leer el fichero de victorias
                            gestorGuardado.getVictorias();
                            corriendo2 = false;
                        }
                        if (linea.equals("c")) {
                            // Cambio del nombre de usuario
                            System.out.println("Introduce el nombre de usuario");
                            nombre = br.readLine();
                            corriendo2 = false;
                        }
                        if (linea.equals("d")) {
                            // Salir del programa
                            corriendo3 = false;
                            lenguaje = false;
                            corriendo = false;
                            corriendo2 = false;
                        }
                        // Error en la selección, pausa y regreso al menú
                        if (!(linea.equals("a") || linea.equals("b")
                                || linea.equals("c") || linea.equals("d"))) {
                            System.err.println("Error. Por favor, introduce a, b, c ó d");
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }
                            corriendo2 = false;
                        }
                    } while (corriendo2);
                }
            } while (lenguaje == true);
        }
    }
}