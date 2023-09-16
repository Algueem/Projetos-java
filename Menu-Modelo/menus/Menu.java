package menus;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Menu {
    protected int opcaoAtual = -1;
    protected boolean fim = false;
    protected abstract void mostrarMenu();
    protected void pegarOpcao() {
        Scanner input = new Scanner(System.in);
        //limpar();
        this.mostrarMenu();
        boolean stop = false;
        do {
            try {
                this.opcaoAtual = input.nextInt();
                stop = true;
            } catch (InputMismatchException e) {
                System.out.println("Opção invalida");
                input.nextLine();
                //limpar();
            }

        } while (!stop);
        input.close();
    }

    public void terminar() {
        System.out.println("Encerrando...");
        System.exit(0);
        // Shutdown
    }
}
