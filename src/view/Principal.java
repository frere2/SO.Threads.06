package view;

import controller.ThreadCalc;
import controller.Banco;

public class Principal {
    public static void main(String[] args) {
        Banco banco = new Banco();
        for (int i = 1; i <= 21; i++) {
            new ThreadCalc(i, banco).start();
        }
    }
}
