import exceptions.MovimentoInvalidoException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RoboInteligente extends Robo {
    private static ArrayList<String> movimentosProibidos = new ArrayList<>();
    public static boolean ultimoMovimentoValido;
    RoboInteligente(String nome, int x, int y) {
        super(nome, x, y);
    }

    public void mover(String direcao) throws MovimentoInvalidoException {
        switch(direcao) {
            case "up":
                if(posY > 0) {
                    String newpos = mapa[posY-1][posX];
                    if (newpos.equals("O") || newpos.equals("\uD83C\uDF4E")) {
                        mapa[posY][posX] = "O";
                        posY--;
                        mapa[posY][posX] = nome;
                    } else {
                        movimentosProibidos.add(direcao);
                        throw new MovimentoInvalidoException();
                    }
                } else {
                    throw new MovimentoInvalidoException();
                }
                break;

            case "down":
                if(posY < 4) {
                    String newpos = mapa[posY+1][posX];
                    if (newpos.equals("O") || newpos.equals("\uD83C\uDF4E")) {
                        mapa[posY][posX] = "O";
                        posY++;
                        mapa[posY][posX] = nome;
                    } else {
                        movimentosProibidos.add(direcao);
                        throw new MovimentoInvalidoException();
                    }
                } else {
                    throw new MovimentoInvalidoException();
                }
                break;

            case "right":
                if(posX < 4) {
                    String newpos = mapa[posY][posX+1];
                    if (newpos.equals("O") || newpos.equals("\uD83C\uDF4E")) {
                        mapa[posY][posX] = "O";
                        posX++;
                        mapa[posY][posX] = nome;
                    } else {
                        movimentosProibidos.add(direcao);
                        throw new MovimentoInvalidoException();
                    }
                } else {
                    throw new MovimentoInvalidoException();
                }
                break;

            case "left":
                if(posX > 0) {
                    String newpos = mapa[posY][posX-1];
                    if (newpos.equals("O") || newpos.equals("\uD83C\uDF4E")) {
                        mapa[posY][posX] = "O";
                        posX--;
                        mapa[posY][posX] = nome;
                    } else {
                        movimentosProibidos.add(direcao);
                        throw new MovimentoInvalidoException();
                    }
                } else {
                    throw new MovimentoInvalidoException();
                }
                break;
            default:
                throw new MovimentoInvalidoException();
        }

    }
    public String gerarMovimentoInteligente() {
        ArrayList<String> opcoes = new ArrayList<>();
        String[] possibilidades = {"O", "\uD83C\uDF4E"};
        List<String> pos = Arrays.stream(possibilidades).collect(Collectors.toList()); // Array list do vetor possibilidades
        if (posX > 0) {
            opcoes.add("left");
            if(!pos.contains(mapa[posX-1][posY])) opcoes.remove("left");
        }
        if (posY > 0) {
            opcoes.add("up");
            if(!pos.contains(mapa[posX][posY-1])) opcoes.remove("up");
        }
        if (posX < 4) {
            opcoes.add("right");
            if(!pos.contains(mapa[posX+1][posY])) opcoes.remove("right");
        }
        if (posY < 4) {
            opcoes.add("down");
            if(!pos.contains(mapa[posX][posY+1])) opcoes.remove("down");
        }
        int opcao = (int) Math.floor(Math.random() * pos.size());
        ultimoMovimentoValido = false;
        return pos.get(opcao);
    }
}
