package aluno;

import java.util.Comparator;

public class ComparadorPorNota implements Comparator<Aluno> {
    @Override
    public int compare(Aluno aluno1, Aluno aluno2) {
        return Double.compare(aluno2.getNota(), aluno1.getNota());
    }
}

