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
            System.out.println(robo.getPosX() + " " + robo.getPosY());
            robo.mover(direcao);
            robo.movimentosValidos++;
            if (!encontrouAlimento(robo)) {
                if (!mapa[robo.getPosY()][robo.getPosX()].equals("O")) {
                    robo.movimentosValidos--;
                    robo.movimentosInvalidos++;
                    throw new MovimentoInvalidoException(); // Bateu em outro robo
                }
            } else {
                System.out.println("Comida encontrada");
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
        System.out.println("X  0  1  2  3  4");
        for (int i = 0; i < 5; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < 5; j++) {
                System.out.print(mapa[i][j]+ "  ");
            }
            System.out.print("\n");
        }
    }
}
