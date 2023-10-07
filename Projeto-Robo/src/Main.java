import exceptions.MovimentoInvalidoException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static String[][] mapa = {
            {"O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O"}
    };

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int opcao = -1;
        Robo robo = new Robo("A");
        // pegar posicao do alimento do usuario
        Plano plano = new Plano(4, 4);
        plano.addRobo(robo, 1, 1);
        //plano.addRobo(robo2);
        double aleatorio = Math.floor(Math.random() * 100);
        //limpar();
        boolean stop = false;
        plano.mostrar();
        while (opcao != 0) {
            do {
                try {
                    opcao = input.nextInt();
                    stop = true;
                } catch (InputMismatchException e) {
                    System.out.println("Opção invalida");
                    input.nextLine();
                }

            } while (!stop);
            try{
                plano.moverRobo(robo, opcao);
            } catch (MovimentoInvalidoException e) {
                System.out.println("Movimento inválido! Tente novamente");
            }
            plano.mostrar();
        }
        input.close();
    }



}