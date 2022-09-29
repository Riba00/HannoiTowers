import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;


public class Main {
    static Stack pila1 = new Stack<Integer>();
    static Stack pila2 = new Stack<Integer>();
    static Stack pila3 = new Stack<Integer>();
    static Stack pilaFinal = new Stack<Integer>();

    public static void main(String[] args) {
        menuInicial();
    }

    public static void menuInicial() {
        Scanner teclat = new Scanner(System.in);
        // VARIABLE DE LA RESPOSTA INICIAL
        int respostaInicial = 0;

        do {
            do {
                //MENU INICIAL
                System.out.println();
                System.out.println("---TORRES DE HANOI---");
                System.out.println("1- Regles");
                System.out.println("2- Jugar");
                System.out.println("3- Sortir");
                System.out.print("Resposta: ");
                try {
                    respostaInicial = teclat.nextInt();
                } catch (InputMismatchException e) {
                    teclat.next();
                }
            } while (!respostaInicialCorrecta(respostaInicial));

            switch (respostaInicial) {
                case 1 -> showRegles();
                case 2 -> jugar();
                case 3 -> System.out.println("Adeu, fins la pròxima!!!");
            }
        } while (respostaInicial != 3);
    }

    public static void jugar() {
        Scanner teclat = new Scanner(System.in);

        int pilaOrigen;
        int pilaDestino;

        plenarPila1PilaFinal();
        showPiles();
        do {
            do {
                pilaOrigen=0;
                pilaDestino=0;
                System.out.print("De quina pila vols moure? ");
                try {
                    pilaOrigen = teclat.nextInt();
                } catch (InputMismatchException e) {
                    teclat.next();
                }
            } while (!verificarMoviment(pilaOrigen));

            do {
                System.out.print("On ho vols deixar? ");
                try {
                    pilaDestino = teclat.nextInt();
                } catch (InputMismatchException e) {
                    teclat.next();
                }
            } while (!verificarMoviment(pilaDestino));

            moureDisco(pilaOrigen, pilaDestino);
            showPiles();
        } while (!pila3.equals(pilaFinal));
        System.out.println("FELICITATS!!!! HAS GUANYAT");
        pila3.clear();
    }

    public static boolean verificarMoviment(int numero) {
        if (numero >= 1 && numero <= 3) return true;
        System.out.println("Numero incorrecte");
        return false;
    }

    public static boolean verificarDiscos(int numeroDiscos) {
        if (numeroDiscos > 0) return true;
        return false;
    }

    public static void moureDisco(int pilaOrigen, int pilaDestino) {

        int ultimElementPila1 = 100;
        int ultimElementPila2 = 100;
        int ultimElementPila3 = 100;

        if (!pila1.empty()) ultimElementPila1 = (int) pila1.peek();
        if (!pila2.empty()) ultimElementPila2 = (int) pila2.peek();
        if (!pila3.empty()) ultimElementPila3 = (int) pila3.peek();

        if (pilaOrigen == 1 && pilaDestino == 2) {
            if (pila1.empty()) {
                System.out.println("La pila 1 és buida");
            } else {
                if (ultimElementPila1 < ultimElementPila2) {
                    pila2.push(pila1.pop());
                } else {
                    System.out.println("Moviment incorrecte");
                }
            }
        }
        if (pilaOrigen == 1 && pilaDestino == 3) {
            if (pila1.empty()) {
                System.out.println("La pila 1 és buida");
            } else {
                if (ultimElementPila1 < ultimElementPila3) {
                    pila3.push(pila1.pop());
                } else {
                    System.out.println("Moviment incorrecte");
                }
            }
        }
        if (pilaOrigen == 2 && pilaDestino == 1) {
            if (pila2.empty()) {
                System.out.println("La pila 2 és buida");
            } else {
                if (ultimElementPila2 < ultimElementPila1) {
                    pila1.push(pila2.pop());
                } else {
                    System.out.println("Moviment incorrecte");
                }
            }
        }
        if (pilaOrigen == 2 && pilaDestino == 3) {
            if (pila2.empty()) {
                System.out.println("La pila 2 és buida");
            } else {
                if (ultimElementPila2 < ultimElementPila3) {
                    pila3.push(pila2.pop());
                } else {
                    System.out.println("Moviment incorrecte");
                }
            }
        }
        if (pilaOrigen == 3 && pilaDestino == 1) {
            if (pila3.empty()) {
                System.out.println("La pila 3 és buida");
            } else {
                if (ultimElementPila3 < ultimElementPila1) {
                    pila1.push(pila3.pop());
                } else {
                    System.out.println("Moviment incorrecte");
                }
            }
        }
        if (pilaOrigen == 3 && pilaDestino == 2) {
            if (pila3.empty()) {
                System.out.println("La pila 3 és buida");
            } else {
                if (ultimElementPila3 < ultimElementPila2) {
                    pila2.push(pila3.pop());
                } else {
                    System.out.println("Moviment incorrecte");
                }
            }
        }
    }

    public static void showRegles() {
        System.out.println("                REGLES");
        System.out.println("- El número indica el diàmetre del disc");
        System.out.println("- No es pot posar un disc més gran sobre un petit");
        System.out.println("    EXEMPLE:   INCORRECTE   CORRECTE");
        System.out.println("                    3           1");
        System.out.println("                    2           2");
        System.out.println("- Per a finalitzar el joc, han d'estar tots els discos");
        System.out.println("     ordenats per diàmetre a la PILA 3.");
        System.out.println("    EXEMPLE:   INCORRECTE   CORRECTE");
        System.out.println("                    1           1");
        System.out.println("                    3           2");
        System.out.println("                    2           3");
        System.out.println("                    4           4");
        System.out.println("                  PILA3       PILA 3");

    }

    public static boolean respostaInicialCorrecta(int respostaInicial) {
        if (respostaInicial == 1 || respostaInicial == 2 || respostaInicial == 3) return true;
        else {
            System.out.println("Resposta incorrecta");
            return false;
        }
    }

    public static void plenarPila1PilaFinal() {
        Scanner teclat = new Scanner(System.in);

        int numeroDiscos;

        do {
            numeroDiscos = 0;
            System.out.print("Numero de discos a jugar: ");
            try {
                numeroDiscos = teclat.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Numero incorrecte");
                teclat.next();
            }
        }while (!verificarDiscos(numeroDiscos));

        for (int i = numeroDiscos; i > 0; i--) {
            pila1.push(i);
        }

        for (int i = numeroDiscos; i > 0; i--) {
            pilaFinal.push(i);
        }
    }

    public static void showPiles() {
        if (pila1.empty()) {
            System.out.println("  -");
        } else {
            for (int i = pila1.size() - 1; i >= 0; i--) {
                System.out.print("  ");
                System.out.println(pila1.get(i));
            }
        }

        System.out.println("PILA 1");
        System.out.println();
        if (pila2.empty()) {
            System.out.println("  -");
        } else {
            for (int i = pila2.size() - 1; i >= 0; i--) {
                System.out.print("  ");
                System.out.println(pila2.get(i));
            }
        }
        System.out.println("PILA 2");
        System.out.println();
        if (pila3.empty()) {
            System.out.println("  -");
        } else {
            for (int i = pila3.size() - 1; i >= 0; i--) {
                System.out.print("  ");
                System.out.println(pila3.get(i));
            }
        }
        System.out.println("PILA 3");

    }
}