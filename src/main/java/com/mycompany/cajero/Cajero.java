package com.mycompany.cajero;
import java.util.Scanner;
// Clase Dispensador que maneja todas las denominaciones
class Dispensador {
    private int denominacion;
    private Dispensador next;

    public Dispensador(int denominacion) {
        this.denominacion = denominacion;
    }

    // Establecer el siguiente dispensador en la cadena
    public void setNext(Dispensador next) {
        this.next = next;
    }

    // Método para dispensar la cantidad
    public void dispense(int cantidad) {
        if (cantidad >= denominacion) {
            int num = cantidad / denominacion;
            int resto = cantidad % denominacion;
            System.out.println("Dispensando " + num + " billetes de $" + denominacion);
            if (resto != 0 && next != null) {
                next.dispense(resto);
            }
        } else if (next != null) {
            next.dispense(cantidad);
        }
    }
}

// Clase ATM para coordinar la cadena de dispensadores
class ATM {
    private Dispensador chain;

    public ATM() {
        // Configurando la cadena de dispensadores de forma simple
        Dispensador dispensador100k = new Dispensador(100000);
        Dispensador dispensador50k = new Dispensador(50000);
        Dispensador dispensador20k = new Dispensador(20000);
        Dispensador dispensador10k = new Dispensador(10000);
        Dispensador dispensador5k = new Dispensador(5000);

        // Definiendo la secuencia de dispensadores
        dispensador100k.setNext(dispensador50k);
        dispensador50k.setNext(dispensador20k);
        dispensador20k.setNext(dispensador10k);
        dispensador10k.setNext(dispensador5k);

        this.chain = dispensador100k;
    }

    public void withdraw(int cantidad) {
        if (cantidad % 5000 != 0) {
            System.out.println("Error: La cantidad debe ser múltiplo de 5.000");
        } else {
            chain.dispense(cantidad);
        }
    }
}

// Clase principal para probar el sistema ATM
public class Cajero {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner leer = new Scanner(System.in);
        int numero=leer.nextInt();
        
        atm.withdraw(numero); // Ejemplo de retiro
    }
}