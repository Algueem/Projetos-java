package menus;

public class MainMenu extends Menu {
    private String text = "1 - Calculadora\n2 - Sla\n0 - Encerrar";

    public void start() {
        while (!this.fim) {
            this.pegarOpcao();
            switch (this.opcaoAtual) {
                case 1:
                    this.menuCalculadora();
                    break;
                case 2:
                    this.menuSla();
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
        System.out.println(this.text);
    }
    public void menuCalculadora() {
        CalculadoraMenu calc = new CalculadoraMenu();
        calc.start();
    }

    public void menuSla() {

    }
}
