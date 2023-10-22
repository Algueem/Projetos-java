package menus;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import aluno.*;

public class CorrigirDisciplinaMenu extends Menu {
    public void start() {

        Scanner s = new Scanner(System.in);
        String[] disciplinas = listarDisciplinas();
        this.pegarOpcao();
        while (this.opcaoAtual <= 0 || this.opcaoAtual > disciplinas.length) {
            System.out.println("Opção invalida!");
            this.pegarOpcao();
        }
        String nomeDisciplina = disciplinas[this.opcaoAtual-1].substring(0, disciplinas[this.opcaoAtual-1].length()-4);
        System.out.printf("Digite o caminho do gabarito da disciplina '%s': \n", nomeDisciplina);
        String caminho = s.nextLine();
        try {
            File fileGabarito = new File(caminho); // Arquivo gabarito
            FileReader frGabarito = new FileReader(fileGabarito);
            BufferedReader brGabarito = new BufferedReader(frGabarito);

            File fileRespostas = new File("src/respostas/"+nomeDisciplina+".txt"); // Arquivo respostas
            FileReader frRespostas = new FileReader(fileRespostas);
            BufferedReader brRespostas = new BufferedReader(frRespostas);//leitor de resposta

            String gabarito = brGabarito.readLine();
            String linha = "";

            ArrayList<Aluno> alunos = new ArrayList<>();
            while((linha = brRespostas.readLine())!=null) {
                String[] partes = linha.split("\t");
                String resposta = partes[0];
                String nomeAluno = partes[1];

                Aluno aluno = new Aluno(nomeAluno, resposta);
                aluno.calcularNota(gabarito);
                alunos.add(aluno);
            }
            // Ordenar e registrar resultados
            File resultadosPorNota = new File("src/resultados/" + nomeDisciplina + "-PorNota.txt");
            File resultadosPorNome = new File("src/resultados/" + nomeDisciplina + "-PorNome.txt");
            FileWriter fwNota = new FileWriter(resultadosPorNota);
            FileWriter fwNome = new FileWriter(resultadosPorNome);

            alunos.sort(new ComparadorPorNota());
            for (Aluno a: alunos) {
                fwNota.write(a.getNome() + " - " +a.getNota()+"\n");
            }

            alunos.sort(new ComparadorPorNome());
            for (Aluno a: alunos) {
                fwNome.write(a.getNome() + " - " +a.getNota()+"\n");
            }
            fwNota.close();
            fwNome.close();
            brGabarito.close();
            frGabarito.close();
            brRespostas.close();
            frRespostas.close();
            System.out.println("Prova corrigida!");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch (IOException ignored) {
        }
    }
    public String[] listarDisciplinas() {
        File dir = new File("src/respostas");
        return dir.list();
    }
    @Override
    protected void mostrarMenu() {
        System.out.println("Escolha uma das disciplinas a seguir: ");
        String[] disciplinas = listarDisciplinas();
        for (int i = 0; i < disciplinas.length; i++) {
            System.out.printf("%d - %s\n", i+1, disciplinas[i].substring(0, disciplinas[i].length()-4));
        }
    }
}