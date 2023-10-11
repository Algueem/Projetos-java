import exceptions.MovimentoInvalidoException;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Robo robo1 = new Robo("A", 1, 1);
        Robo robo2 = new Robo("B", 1, 5);
        int[] p = pegarPosicaoComida();
        Robo.setAlimento(p[0], p[1]);
        input.close();
        Robo.mostrar();
        sleep();
        while (!Robo.encontrouAlimento(robo1) && !Robo.encontrouAlimento(robo2)) {
            Robo[] robos = {robo1, robo2};
            for (int i = 0; i < 2; i++) {
                String opcao = robos[i].gerarMovimento();
                System.out.println("Vez do robo: " + robos[i].getNome());
                System.out.println("O robo " + robos[i].getNome() + " fez o movimento " + opcao);
                try {
                    robos[i].mover(opcao);
                    robos[i].movimentosValidos++;
                } catch (MovimentoInvalidoException e) {
                    robos[i].movimentosInvalidos++;
                }
                Robo.mostrar();
                sleep();
            }
        }

        System.out.println("Resultado final:");
        String winner = Robo.encontrouAlimento(robo1) ? robo1.getNome() : robo2.getNome();
        System.out.println("O robo " + winner + " encontrou o alimento");
        System.out.println("- O robo " + robo1.getNome() + " fez " + robo1.movimentosValidos + " movimentos validos");
        System.out.println("- O robo " + robo1.getNome() + " fez " + robo1.movimentosInvalidos + " movimentos invalidos");
        System.out.println("- O robo " + robo2.getNome() + " fez " + robo2.movimentosValidos + " movimentos validos");
        System.out.println("- O robo " + robo2.getNome() + " fez " + robo2.movimentosInvalidos + " movimentos invalidos");

    }
    public static void sleep() {
        try {
            Thread.sleep(1800);
        } catch (InterruptedException ignored) {
        }
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