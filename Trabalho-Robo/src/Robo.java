import exceptions.MovimentoInvalidoException;

public class Robo {
    protected static String[][] mapa = {
            {"O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O"},
            {"O", "O", "O", "O", "O"}
    };

    protected static int alimentoX;
    protected static int alimentoY;
    protected int posX;
    protected int posY;
    protected String nome;

    protected int movimentosValidos = 0;
    protected int movimentosInvalidos = 0;

    Robo(String nome, int x, int y){
        this.nome = nome;
        posX = x-1;
        posY = y-1;
        mapa[y-1][x-1] = nome;
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

    public int gerarMovimento() {
        int opcao = (int) Math.floor(Math.random() * 4 +1);
        return opcao;
    }

    public void mover(int opcao) throws MovimentoInvalidoException{
        String[] opcoes = {"up", "down", "right", "left"};
        if (opcao > 4 || opcao < 1) {
            throw new MovimentoInvalidoException();
        }
        mover(opcoes[opcao-1]);
    }

    public static boolean encontrouAlimento(Robo robo) {
        if (alimentoX == robo.getPosX() && alimentoY == robo.getPosY()) {
            return true;
        }
        return false;
    }


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getNome() {
        return nome;
    }

    public static void mostrar() {
        System.out.println("X  1  2  3  4  5");
        System.out.println("Y  -  -  -  -  -");
        for (int i = 0; i < 5; i++) {
            System.out.print(i+1 + "| ");
            for (int j = 0; j < 5; j++) {
                System.out.print(mapa[i][j]+"  ");
            }
            System.out.print("\n");
        }
    }

    public static void setAlimento(int x, int y) {
        mapa[y][x] = "\uD83C\uDF4E";
        alimentoX = x;
        alimentoY = y;
    }
}