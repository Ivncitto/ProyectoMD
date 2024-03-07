package proyectomd;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Definición de las cintas y constantes
    private static ArrayList<Character> cinta1 = new ArrayList<>();
    private static ArrayList<Character> cinta2 = new ArrayList<>();
    private static ArrayList<Character> cinta3 = new ArrayList<>();
    private static final char UNO = '1';
    private static final char CERO = '0';
    private static final char SIMBOLO = 'S';
    private static boolean ONorOFF = true;//Variable para el estado de el reloj visual
    private static final int time = 250; //reloj con .25 segundos

    // Método principal (main)
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Menú de opciones
            System.out.println("---------------------------");
            System.out.println("|          MENÚ           |");
            System.out.println("| 1. Fase uno             |");
            System.out.println("| 2. Fase dos             |");
            System.out.println("| 3. Fase tres            |");
            System.out.println("| 4. Limpiar todo         |");
            System.out.println("| 5. Ajustar reloj visual |");
            System.out.println("| 6. Hacer las tres fases |");
            System.out.println("| 0. Salir                |");
            System.out.println("---------------------------");

            try {
                System.out.print("Selecciona una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (opcion) {
                    case 1:
                        faseUno();
                        break;
                    case 2:
                        faseDos();
                        break;
                    case 3:
                        faseTres();
                        break;
                    case 4:
                        // Limpiar todas las cintas
                        if (!cinta1.isEmpty()) {
                            cinta1.clear();
                            cinta2.clear();
                            cinta3.clear();

                            System.out.println("\nCinta limpiada con éxito!\n");
                            if (ONorOFF) {
                                Thread.sleep(time); // Retraso de 0.5 segundos
                            }
                        } else {
                            System.out.println("La cinta no ha sido utilizada, no hay nada que limpiar!");
                        }
                        break;
                    case 5:
                        System.out.println("Ajuste de reloj");
                        reloj();
                        break;
                    case 6:
                        if (cinta1.isEmpty()) {
                            System.out.println("REALIZANDO LAS TRES FASES");
                            System.out.println("--------FASE 1--------");
                            faseUno();
                            System.out.println("--------FASE 2--------");
                            faseDos();
                            System.out.println("--------FASE 3-------");
                            faseTres();
                            System.out.println("--------Termina--------");
                        } else {
                            System.out.println("Los vectores están siendo utilizados, borra su contenido");
                        }
                        break;
                    case 0:
                        cierre();
                        System.out.println("\nCiao... (by: IIV)\n");
                        System.exit(0); // Salir del programa
                    default:
                        System.out.println("Opción no válida, inténtalo de nuevo.");
                        if (ONorOFF) {
                            Thread.sleep(time); // Retraso de 0.5 segundos
                        }
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número entero.");
                scanner.nextLine(); // Limpiar el búfer de entrada
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void reloj() {
        Scanner t = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("---------------------------------------------");
            System.out.println("|                 RELOJ                     |");
            System.out.println("| 1. Apagar reloj                           |");
            System.out.println("| 2. Encender reloj (encendido por defecto) |");
            System.out.println("| 3. Atrás                                  |");
            System.out.println("---------------------------------------------");
            System.out.println("Dame una opción");

            opcion = t.nextInt();
            switch (opcion) {
                case 1:
                    if (!ONorOFF) {
                        System.out.println("El reloj esta apagado, no puedes apagarlo :v");
                    } else {
                        ONorOFF = false;
                        System.out.println("El reloj ha sido apagado");
                    }
                    opcion = 3;
                    break;
                case 2:
                    if (ONorOFF) {
                        System.out.println("El reloj esta encendido, no puedes encenderlo :v");
                    } else {
                        ONorOFF = true;
                        System.out.println("El reloj ha sido encendido");
                    }
                    opcion = 3;
                    break;
                case 3:
                    System.out.println("Atrás");
                    break;
                default:
                    System.out.println("Dame una opción valida");
                    break;
            }
        } while (opcion != 3);
    }

    // Fase uno: introducción de números y operaciones en cinta1
    public static void faseUno() throws InterruptedException {
        if (cinta1.isEmpty()) {

            Scanner scanner = new Scanner(System.in);
            int cabezal = 0;

            // Iterar dos veces: una vez para cada número a ingresar
            for (int i = 0; i < 2; i++) {
                // Mostrar mensaje de introducción de valor según el valor de i
                if (i == 0) {
                    System.out.print("-------------------------------------------------------------------------\n");
                    System.out.print("| Introduce el primer número compuesto solo por unos (1's) para la suma |\n");
                    System.out.print("-------------------------------------------------------------------------\n");
                } else if (i == 1) {
                    System.out.print("--------------------------------------------------------------------------\n");
                    System.out.print("| Introduce el segundo número compuesto solo por unos (1's) para la suma |\n");
                    System.out.print("--------------------------------------------------------------------------\n");
                }

                // Leer la entrada del usuario
                String input = scanner.nextLine();

                // Procesar cada carácter de la entrada
                for (int j = 0; j < input.length(); j++) {
                    char c = input.charAt(j);
                    // Verificar si el carácter es '1'
                    if (c == UNO) {//si es uno los meteremos en el vecto
                        cinta1.add(UNO);
                        cabezal = cinta1.size() - 1;
                        // Mover el cabezal a la última posición
                        System.out.println("Agregando '1' a la cinta:");
                        imprimirCinta(cinta1, cabezal);

                    } else {//si es diferente que uno 
                        // Si el carácter no es '1', indicar que el número no es válido
                        System.out.println("-------------------------------------------------------------");
                        System.out.printf("\nEn la fase 1: El número %c en la posicion %d no es válido.", c, (cabezal + 2));
                        System.out.println("El número Solo debe consistir  en unos (1's).\n");
                        System.out.println("\nLa cinta se vació debido a un error en la entrada.\n");
                        System.out.println("-------------------------------------------------------------");
                        Thread.sleep(3000);
                        cinta1.clear();

                        break;
                    }
                }

                // Agregar '0' o 'S' a la cinta según el valor de i
                if (i == 0) {
                    cinta1.add(CERO);
                    cabezal = cinta1.size() - 1; // Mover el cabezal a la última posición
                    System.out.println("Agregando '0' (signo) a la cinta:");
                    imprimirCinta(cinta1, cabezal); // Imprimir 0 y S
                } else if (i == 1) {
                    cinta1.add(SIMBOLO);
                    cabezal = cinta1.size() - 1; // Mover el cabezal a la última posición
                    System.out.println("Agregando 'S' (símbolo) a la cinta:");
                    imprimirCinta(cinta1, cabezal); // Imprimir 0 y S
                }
            }

            // Si la cinta no está vacía, realizar operación y acomodar unos
            if (!cinta1.isEmpty()) {
                System.out.println("Realizando operación:");
                System.out.println("Buscando unos en la cinta:");
                operacionCinta(cinta1);
                System.out.println();
                System.out.println("Acomodando unos encontrados en la cinta:");
                unosDDS(cinta1, cabezal);

            }
        } else {

            System.out.println("\nNecesitas borrar los datos en las cinta, ya que ha sido utilizada\n");

        }
    }

    // Fase dos: realizar operaciones en cinta2
    public static void faseDos() throws InterruptedException {
        // Verificar si la cinta2 no está vacía
        if (!cinta2.isEmpty() && cinta2.contains(UNO)) {
            System.out.println();
            // Imprimir el resultado de la operación en la cinta principal
            System.out.println("Resultado de la operación en la cinta principal:");
            for (int i = 0; i < cinta2.size(); i++) {
                System.out.print("|" + cinta2.get(i) + "|");
                if (ONorOFF) {
                    Thread.sleep(time); // Retraso de 0.5 segundos
                } // Retraso de 0.5 segundos
            }
            System.out.println("\n");

            // Mostrar el estado actual de la cinta2
            System.out.println("Buscando unos en la cinta:");
            for (int i = 0; i < cinta2.size(); i++) {
                System.out.print("|" + cinta2.get(i) + "|");
                if (ONorOFF) {
                    Thread.sleep(time); // Retraso de 0.5 segundos
                }
            }
            System.out.println();

            // Procesar la cinta2 cambiando unos por ceros y agregando ceros a la cinta3
            for (int i = 0; i < cinta2.size(); i++) {
                if (cinta2.get(i) == UNO) {
                    System.out.print(" ^ ");
                    cinta2.set(i, CERO);
                    cinta3.add(CERO);
                    if (ONorOFF) {
                        Thread.sleep(time); // Retraso de 0.5 segundos
                    }
                } else {
                    System.out.print("   ");
                }
            }

            // Imprimir el estado después de cambiar unos por ceros en la cinta2
            System.out.println();
            System.out.println("Cambiando unos por ceros en la cinta:");
            for (int i = 0; i < cinta2.size(); i++) {
                System.out.print("|" + cinta2.get(i) + "|");
                if (ONorOFF) {
                    Thread.sleep(time); // Retraso de 0.5 segundos
                }
            }
            System.out.println("\n");
        } else if (cinta2.contains(CERO)) {

            System.out.println("\nNecesitas borrar los datos en las cinta, ya que ha sido utilizada\n");

        } else if (cinta2.isEmpty()) {
            // Si la cinta2 está vacía, mostrar un mensaje
            System.out.println("\na cinta está vacía. Realiza primero la fase uno.\n");
        }
    }

    // Fase tres: realizar operaciones en cinta3
    public static void faseTres() throws InterruptedException {
        // Verificar si la cinta3 no está vacía
        if (!cinta3.isEmpty() && !cinta3.contains('I')) {
            System.out.println();
            // Imprimir el resultado de la operación en la cinta principal
            for (int i = 0; i < cinta3.size(); i++) {
                System.out.print("|" + cinta3.get(i) + "|");
                if (ONorOFF) {
                    Thread.sleep(time); // Retraso de 0.5 segundos
                }
            }
            System.out.println();

            // Mostrar el estado actual de la cinta3
            System.out.println("Agregando iniciales al final de los elementos de la cinta:");
            cinta3.add('I');
            cinta3.add('I');
            cinta3.add('V');
            for (int i = 0; i < cinta3.size(); i++) {
                System.out.print("|" + cinta3.get(i) + "|");
                if (ONorOFF) {
                    Thread.sleep(time); // Retraso de 0.5 segundos
                }
            }
            System.out.println();

            // Mostrar el estado de la cinta3 después de agregar iniciales
            for (int i = 0; i < cinta3.size(); i++) {
                if (cinta3.get(i) != CERO) {
                    System.out.print(" ^ ");
                    if (ONorOFF) {
                        Thread.sleep(time); // Retraso de 0.5 segundos
                    }
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println("\n");
        } else if (cinta3.contains('I')) {
            System.out.println("\nNecesitas borrar los datos en las cinta, ya que ha sido utilizada\n");
        } else if (cinta3.isEmpty()) {
            // Si la cinta3 está vacía, mostrar un mensaje
            System.out.println("\nLa cinta está vacía. Realiza primero la fase dos.\n");
        }
    }

    // Operación en cinta1: buscar unos y agregar al final
    public static void operacionCinta(ArrayList<Character> cinta1) throws InterruptedException {
        // Verificar si la cinta1 no está vacía
        if (!cinta1.isEmpty()) {
            // Crear una lista para almacenar las modificaciones
            ArrayList<Character> modificaciones = new ArrayList<>();

            // Imprimir el estado actual de la cinta1
            for (int i = 0; i < cinta1.size(); i++) {
                System.out.print("|" + cinta1.get(i) + "|");
            }
            System.out.println();

            // Procesar la cinta1 cambiando unos por unos y agregando unos al final
            for (int i = 0; i < cinta1.size(); i++) {
                if (cinta1.get(i) == UNO) {
                    System.out.print(" ^ ");
                    // Agregar '1' al final cuando se encuentra un '1'
                    if (ONorOFF) {
                        Thread.sleep(time); // Retraso de 0.5 segundos
                    }
                    modificaciones.add(UNO);
                } else {
                    System.out.print("   ");
                }
            }

            // Actualizar la cinta original con las modificaciones
            cinta1.addAll(modificaciones);
            cinta2.addAll(modificaciones);
            modificaciones.clear(); // Limpiar la lista de modificaciones
        }
    }

    // Imprimir una cinta con indicación del cabezal
    public static void imprimirCinta(ArrayList<Character> cinta1, int cabezal) throws InterruptedException {
        // Verificar si la cinta1 no está vacía
        if (!cinta1.isEmpty()) {
            // Imprimir indicadores visuales de la posición del cabezal
            for (int i = 0; i < cinta1.size(); i++) {
                if (i == cabezal) {
                    System.out.print(" ↓ en la posición " + (i + 1) + "\n");//imprime en el tamaño de la cinta -1
                } else {
                    System.out.print("   ");
                }
            }

            // Imprimir el contenido de la cinta1
            for (int i = 0; i < cinta1.size(); i++) {
                System.out.print("|" + cinta1.get(i) + "|");
                if (ONorOFF) {
                    Thread.sleep(time); // Retraso de 0.5 segundos
                }
            }
            System.out.println("\n"); // Salto de línea
        }
    }

    // Operación en cinta1: cambiar unos por ceros en cinta2
    public static void unosDDS(ArrayList<Character> cinta1, int cabezal) throws InterruptedException {
        // Verificar si la cinta1 no está vacía
        if (!cinta1.isEmpty()) {
            // Variable para controlar si estamos después del símbolo 'S'
            boolean despuesDeS = false;

            // Imprimir indicador visual de la posición del cabezal
            for (int i = 0; i < cinta1.size(); i++) {
                if (i == cabezal) {
                    System.out.print("    ↓ desde la posición " + (i + 2));
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();

            // Imprimir el contenido de la cinta1
            for (int i = 0; i < cinta1.size(); i++) {
                System.out.print("|" + cinta1.get(i) + "|");
                if (ONorOFF) {
                    Thread.sleep(time); // Retraso de 0.5 segundos
                }
            }
            System.out.println();

            // Procesar la cinta1 para resaltar unos después de 'S'
            for (int i = 0; i < cinta1.size(); i++) {
                char charActual = cinta1.get(i);

                // Establecer la bandera despuesDeS cuando se encuentra 'S'
                if (charActual == SIMBOLO) {
                    despuesDeS = true;
                }

                // Resaltar unos después de 'S'
                if (despuesDeS && charActual == UNO) {
                    System.out.print(" ^ ");
                    if (ONorOFF) {
                        Thread.sleep(time); // Retraso de 0.5 segundos
                    }
                } else {
                    // Mostrar otros caracteres normalmente o vacío si antes de 'S'
                    if (despuesDeS && charActual != SIMBOLO) {
                        System.out.print("|" + "   " + "|");//espacio en blanco
                        if (ONorOFF) {
                            Thread.sleep(time); // Retraso de 0.5 segundos
                        }
                    } else {
                        System.out.print("   ");
                    }
                }
            }
            System.out.println("\n"); // Salto de línea

        }
    }

    private static void cierre() throws InterruptedException {
        String[] frames = {
            "     (   )",
            "      \\ /",
            "       -",
            "      / \\",
            "     (   )"
        };

        for (int i = 0; i < 3; i++) {
            for (String frame : frames) {
                System.out.println(frame);
                Thread.sleep(time); // Pausa en milisegundos entre los frames

            }
        }
    }

}
