import exceptions.MovimentoInvalidoException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoboInteligente extends Robo {
    public boolean ultimoMovimentoValido = true; // se o ultimo movimento for invalido essa variavel sera usada pra gerar um valido
    RoboInteligente(String nome, int x, int y) {
        super(nome, x, y);
    }
    public String gerarMovimento() {
        if (!ultimoMovimentoValido) return gerarMovimentoInteligente();
        return super.gerarMovimento();
    }

    public String gerarMovimentoInteligente() {
        ArrayList<String> opcoes = new ArrayList<>();
        String[] possibilidades = {"O", "\uD83C\uDF4E"};

        List<String> pos = Arrays.stream(possibilidades).toList(); // Array list do vetor possibilidades
        if (posX > 0) {
            opcoes.add("left");
            if(!pos.contains(mapa[posY][posX-1])) opcoes.remove("left");
        }
        if (posY > 0) {
            opcoes.add("up");
            if(!pos.contains(mapa[posY-1][posX])) opcoes.remove("up");
        }
        if (posX < 4) {
            opcoes.add("right");
            if(!pos.contains(mapa[posY][posX+1])) opcoes.remove("right");
        }
        if (posY < 4) {
            opcoes.add("down");
            if(!pos.contains(mapa[posY+1][posX])) opcoes.remove("down");
        }
        int opcao = (int) Math.floor(Math.random() * opcoes.size());
        ultimoMovimentoValido = true;
        return opcoes.get(opcao);
    }
}