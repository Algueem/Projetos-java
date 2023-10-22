package menus;

import java.io.*;
import java.util.Scanner;

public class CadastrarDisciplinaMenu extends Menu{
    public void mostrarMenu() {

    }
    public void start() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o nome da disciplina: ");
        String nomeDisciplina = scan.nextLine();
        System.out.println("Quantos alunos serão registrados: ");
        int qntAlunos = scan.nextInt();

        try {
            File arquivo = new File("src/respostas/" + nomeDisciplina + ".txt");
            if (!arquivo.createNewFile()) {
                System.out.println("A disciplina já foi cadastrada!");
                return;
            }
            FileWriter f = new FileWriter(arquivo); //abrindo arquivo
            BufferedWriter bw = new BufferedWriter(f);
            for (int i = 0; i < qntAlunos; i++) {
                System.out.printf("Digite as resposta do aluno %d:", i+1);
                String resposta = scan.next();
                System.out.printf("Digite o nome do aluno %d:", i+1);
                String nome = scan.next();
                bw.write(resposta + "\t" + nome.toUpperCase() + "\n");
            }
            bw.close();
            f.close();
            System.out.println("Disciplina cadastrada!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
