import exceptions.MovimentoInvalidoException;

public class Plano {
    private String[][] mapa = {
            {"O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O"}
    };
    private int alimentoX;
    private int alimentoY;

    public Plano(int x, int y) {
        alimentoX = x-1;
        alimentoY = y-1;
        addComida(x, y);
    }
    public void moverRobo(Robo robo, int direcao) throws MovimentoInvalidoException{
        try {
            String[] direcoes = {"up", "down", "right", "left"};
            String[] direcoesReversas = {"down", "up", "left", "right"};
            System.out.println("O robo " + robo.getNome() + " fez o movimento " + (direcao-1) + " " + direcoes[direcao-1]);
            robo.mover(direcao);
            robo.movimentosValidos++;
            if (!encontrouAlimento(robo)) {
                if (!mapa[robo.getPosY()][robo.getPosX()].equals("O")) {
                    robo.movimentosValidos--;
                    robo.mover(direcoesReversas[direcao-1]);
                    throw new MovimentoInvalidoException(); // Bateu em outro robo
                }
            } else {
                System.out.println("Comida encontrada pelo robo " + robo.getNome());
            }
        } catch (MovimentoInvalidoException e) {
            robo.movimentosInvalidos++;
            System.out.println("Movimento inválido!");
            return;
        }
        atualizarMapa(robo);

    }
    public void addRobo(Robo robo, int posX, int posY) {
        mapa[posY-1][posX-1] = robo.getNome();
        robo.setPosX(posX-1);
        robo.setPosY(posY-1);
    }
    public void addComida(int posX, int posY) {
        mapa[posY-1][posX-1] = "🍎";
    }

    public boolean encontrouAlimento(Robo robo) {
        if (alimentoX == robo.getPosX() && alimentoY == robo.getPosY()) {
            return true;
        }
        return false;
    }

    public void atualizarMapa(Robo robo) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (mapa[i][j].equals(robo.nome)) {
                    mapa[i][j] = "O";
                }
            }
        }
        mapa[robo.getPosY()][robo.getPosX()] = robo.getNome();
    }

    public void mostrar() {
        System.out.println("X  1  2  3  4  5");
        System.out.println("Y  -  -  -  -  -");
        for (int i = 0; i < 5; i++) {
            System.out.print(i+1 + "| ");
            for (int j = 0; j < 5; j++) {
                System.out.print(""+mapa[i][j]+"  ");
            }
            System.out.print("\n");
        }
    }
}
