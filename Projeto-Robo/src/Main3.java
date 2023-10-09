import exceptions.MovimentoInvalidoException;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Robo robo1 = new RoboInteligente("A", 1, 1);
        Robo robo2 = new Robo("B", 1, 2);
        int[] p = pegarPosicaoComida();
        Robo.setAlimento(p[0], p[1]);
        input.close();
        Robo.mostrar();
        while (!Robo.encontrouAlimento(robo1) || !Robo.encontrouAlimento(robo2)) {
            Robo[] robos = {robo1, robo2};
            for (int i = 0; i < 2; i++) {
                System.out.println("Vez do robo: " + robos[i].getNome());
                sleep();
                String opcao = robos[i].gerarMovimento();
                System.out.println("O robo " + robos[i].getNome() + " fez o movimento " + opcao);
                try {
                    robos[i].mover(opcao);
                    if (robos[i] instanceof RoboInteligente) {
                        ((RoboInteligente) robos[i]).ultimoMovimentoValido = true;
                    }
                    robos[i].movimentosValidos++;
                    //Robo.mostrar();
                } catch (MovimentoInvalidoException e) {
                    robos[i].movimentosInvalidos++;
                    if (robos[i] instanceof RoboInteligente) {
                        ((RoboInteligente) robos[i]).ultimoMovimentoValido = false;
                    }
                    System.out.println("O robo fez um movimento invalido");
                }
                //System.out.println("O robo " + robos[i].getNome() + " fez " + robos[i].movimentosValidos + " movimentos validos");
                //System.out.println("O robo " + robos[i].getNome() + " fez " + robos[i].movimentosInvalidos + " movimentos invalidos");
                sleep();
                Robo.mostrar();
            }
        }

        System.out.println("Resultado final:");
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
        return new int[]{x-1, y-1};
    }
}