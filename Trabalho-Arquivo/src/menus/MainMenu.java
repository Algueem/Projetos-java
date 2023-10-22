package menus;

import java.io.*;
import java.util.Scanner;

public class MainMenu extends Menu {
    private String text = "1 - Cadastrar respostas\n2 - Corrigir disciplina\n3 - Ver resultados\n0 - Encerrar";

    public void start() {
        while (!this.fim) {
            Menu m = null;
            this.pegarOpcao();
            switch (this.opcaoAtual) {
                case 1:
                    m = new CadastrarDisciplinaMenu();
                    break;
                case 2:
                    m = new CorrigirDisciplinaMenu();
                    break;
                case 3:
                    m = new VerResultadoMenu();
                    break;
                case 0:
                    this.terminar();
                    break;
                default:
                    m = null;
                    break;
            }
            if (m == null) {
                System.out.println("Opção invalida");
                start();
            } else{
                m.start();
            }
        }
    }
    @Override
    protected void mostrarMenu() {
        System.out.println(this.text);
    }
}
