package menus;

import java.io.*;
import java.util.Scanner;

public class VerResultadoMenu extends Menu {
    public void start() {

        Scanner s = new Scanner(System.in);
        String[] resultados = listarResultados();
        this.pegarOpcao();
        while (this.opcaoAtual <= 0 || this.opcaoAtual > resultados.length) {
            System.out.println("Opção invalida!");
            this.pegarOpcao();
        }
        try {
            File resultado = new File("src/resultados/" + resultados[this.opcaoAtual-1]);
            FileReader fr = new FileReader(resultado);
            BufferedReader br = new BufferedReader(fr);
            String linha = "";
            while((linha = br.readLine())!=null) {
                System.out.println(linha);
            }
        } catch (IOException ignored) {
        }
    }
    public String[] listarResultados() {
        File dir = new File("src/resultados");
        return dir.list();
    }
    @Override
    protected void mostrarMenu() {
        System.out.println("Lista de resultados disponíveis:");
        String[] disciplinas = listarResultados();
        for (int i = 0; i < disciplinas.length; i++) {
            System.out.printf("%d - %s\n", i+1, disciplinas[i].substring(0, disciplinas[i].length()-4));
        }
    }
}