import exceptions.MovimentoInvalidoException;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Robo robo1 = new Robo("A");
        Robo robo2 = new Robo("B");
        int[] p = pegarPosicaoComida();
        Plano plano = new Plano(p[0], p[1]);
        input.close();
        plano.addRobo(robo1, 1, 1);
        plano.addRobo(robo2, 1, 2);
        plano.mostrar();
        while (!plano.encontrouAlimento(robo1) && !plano.encontrouAlimento(robo2)) {
            Robo[] robos = {robo1, robo2};
            for (int i = 0; i < 2; i++) {
                int opcao = (int) Math.floor(Math.random() * 4+1);
                System.out.println("Vez do robo: " + robos[i].getNome());
                sleep();
                try {
                    plano.moverRobo(robos[i], opcao);
                    plano.mostrar();
                } catch (MovimentoInvalidoException e) {
                    System.out.println("O robo fez um movimento invalido");
                }
                System.out.println("O robo " + robos[i].getNome() + " fez " + robos[i].movimentosValidos + " movimentos validos");
                System.out.println("O robo " + robos[i].getNome() + " fez " + robos[i].movimentosInvalidos + " movimentos invalidos");
                sleep();
            }
        }
        System.out.println("Resultado final:");
        String winner = plano.encontrouAlimento(robo1) ? robo1.getNome() : robo2.getNome();
        System.out.println("O robo " + winner + " encontrou o alimento");
        System.out.println("- O robo " + robo1.getNome() + " fez " + robo1.movimentosValidos + " movimentos validos");
        System.out.println("- O robo " + robo1.getNome() + " fez " + robo1.movimentosInvalidos + " movimentos invalidos");
        System.out.println("- O robo " + robo2.getNome() + " fez " + robo2.movimentosValidos + " movimentos validos");
        System.out.println("- O robo " + robo2.getNome() + " fez " + robo2.movimentosInvalidos + " movimentos invalidos");

    }
    public static void sleep() {
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            return;
        }
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
