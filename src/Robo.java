import exceptions.MovimentoInvalidoException;

public class Robo {
	
	protected int posX;
	protected int posY;
	protected String nome;

	protected int movimentosValidos = 0;
	protected int movimentosInvalidos = 0;
	
	Robo(String nome){
		this.nome = nome;
		posX = 0;
		posY = 0;
	}
	
	public void mover(String direcao) throws MovimentoInvalidoException {
		switch(direcao) {
			case "up":
				if(posY > 0) {
					posY--;
				} else {
					throw new MovimentoInvalidoException();
				}
				break;

			case "down":
				if(posY < 4) {
					posY++;
				} else {
					throw new MovimentoInvalidoException();
				}
				break;

			case "right":
				if(posX < 4) {
					posX++;
				} else {
					throw new MovimentoInvalidoException();
				}
				break;

			case "left":
				if(posX > 0) {
					posX--;
				} else {
					throw new MovimentoInvalidoException();
				}
				break;
			}
		
	}
	
	public void mover(int opcao) throws MovimentoInvalidoException{
		String[] opcoes = {"up", "down", "right", "left"};
		if (opcao > 4 || opcao < 1) {
			throw new MovimentoInvalidoException();
		}
		mover(opcoes[opcao-1]);
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public String getNome() {
		return nome;
	}
}
