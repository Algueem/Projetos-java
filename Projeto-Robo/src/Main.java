import exceptions.MovimentoInvalidoException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int opcao = -1;
        Robo robo = new Robo("A");
        int[] p = pegarPosicaoComida();
        Plano plano = new Plano(p[0], p[1]);
        plano.addRobo(robo, 1, 1);
        // plano.addRobo(robo2);
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

    public static int[] pegarPosicaoComida() {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o X da comida:");
        int x = input.nextInt();
        System.out.println("Digite o Y da comida:");
        int y = input.nextInt();
        return new int[]{x, y};
    }

}