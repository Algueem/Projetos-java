package menus;

public class CalculadoraMenu extends Menu {
    public void start() {
        while (!this.fim) {
            this.pegarOpcao();
            switch (this.opcaoAtual) {
                case 1: // Parar menu sem parar o programa
                    this.fim = true;
                    break;
                case 2:
                    this.somar();
                    break;
                case 0:
                    this.terminar();
                    break;
                default:
                    break;
            }
        }
    }
    @Override
    protected void mostrarMenu() {
        System.out.println("1 - Voltar\n2 - Somar\n3 - Subtrair\n0 - Encerrar");
    }

    public void somar() {
        
    }
}
