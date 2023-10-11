import exceptions.MovimentoInvalidoException;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Robo robo1 = new RoboInteligente("A", 1, 1);  //instanciando
        Robo robo2 = new Robo("B", 5, 5);
        int[] p = pegarPosicaoComida();
        Robo.setAlimento(p[0], p[1]);

        input.close();
        Robo.mostrar();
        sleep();
        Robo[] robos = {robo1, robo2};
        int pararRobo = -1;

        while (!Robo.encontrouAlimento(robo1) || !Robo.encontrouAlimento(robo2)) { //verifica se pelo menos um dos robos nao encontrou o alimento
            for (int i = 0; i < 2; i++) {
                if (i == pararRobo) continue; //ignorar o robo que ja encontrou o alimento

                System.out.println("Vez do robo: " + robos[i].getNome());
                String opcao = robos[i].gerarMovimento();
                System.out.println("O robo " + robos[i].getNome() + " fez o movimento " + opcao);

                try {
                    robos[i].mover(opcao);
                    if (Robo.encontrouAlimento(robos[i]) && pararRobo == -1) { // fazer o robo parar de se mover
                        pararRobo = i;
                        Robo.setAlimento(p[0], p[1]); // setar alimento
                    }
                    robos[i].movimentosValidos++;
                }
                catch (MovimentoInvalidoException e) {
                    robos[i].movimentosInvalidos++;
                    if (robos[i] instanceof RoboInteligente) {
                        // o proximo movimento do robointeligente vai ser valido pq o ultimo foi invalido
                        // depois de um movimento errado ele precisa fazer um certo
                        ((RoboInteligente) robos[i]).ultimoMovimentoValido = false;
                    }
                    System.out.println("O robo fez um movimento invalido");
                }

                Robo.mostrar();
                sleep();
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
            Thread.sleep(1500);
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