package aluno;

public class Aluno {
    private int nota;
    private String respostas;
    private String nome;

    public Aluno(String nome, String respostas) {
        this.nome = nome;
        this.respostas = respostas;
    }

    public String getNome() {
        return nome;
    }

    public int getNota() {
        return nota;
    }

    public void calcularNota(String gabarito) {
        int nota =0;
        if (this.respostas.equals("VVVVVVVVVV") || this.respostas.equals("FFFFFFFFFF")) {
            this.nota = nota;
            return ;
        }

        for(int i =0; i<gabarito.length(); i++) {
            if(gabarito.charAt(i) == this.respostas.charAt(i)) {
                nota++;
            }
        }
        this.nota = nota;
    }
}

