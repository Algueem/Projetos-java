import exceptions.MovimentoInvalidoException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int opcao = -1;
        Robo robo = new Robo("A", 1, 1);
        int[] p = pegarPosicaoComida();
        Robo.setAlimento(p[0], p[1]);
        boolean stop = false;
        Robo.mostrar();
        while (opcao != 0) {
            opcao = input.nextInt();
            try{
                robo.mover(opcao);
            } catch (MovimentoInvalidoException e) {
                System.out.println("Movimento inválido! Tente novamente");
            }
            Robo.mostrar();
            if(Robo.encontrouAlimento(robo)){
                System.out.println("Alimento encontrado.");
                break;
            }
        }
        input.close();
    }

    public static int[] pegarPosicaoComida() {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o X da comida:");
        int x = input.nextInt();
        System.out.println("Digite o Y da comida:");
        int y = input.nextInt();
        return new int[]{x-1, y-1};
    }


}