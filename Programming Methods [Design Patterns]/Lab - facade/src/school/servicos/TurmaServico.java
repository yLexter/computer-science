package school.servicos;

import school.entities.AlunoTurma;
import school.entities.Disciplina;
import school.entities.Horario;
import school.entities.Turma;
import school.erros.TurmaSevicoException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TurmaServico {

    public List<Turma> turmas;

    public TurmaServico() {
        this.turmas = new ArrayList<>();
    }

    public Turma obterTurmaPorId(String id) {
        return turmas.stream()
                .filter(turma -> turma.getId().equals(id))
                .findAny()
                .orElseThrow(() -> new TurmaSevicoException("Turma inexistente"));
    }

    public Turma adicionarTurma(Disciplina disciplina) {
        var turma = new Turma(disciplina);

        turmas.add(turma);

        return turma;
    }

    public void adicionarAlunoATurma(String alunoId, String turmaId) {
        Turma turma = obterTurmaPorId(turmaId);

        var alunosTurma = turma.getAlunos();

        for (AlunoTurma aluno : alunosTurma) {
            if (aluno.getMatricula().equals(alunoId))
                throw new TurmaSevicoException("Aluno já está na turma");
        }

        AlunoTurma alunoTurma = new AlunoTurma(alunoId);

        turma.getAlunos().add(alunoTurma);
    }

    public List<Turma> obterTurmaDeUmAluno(List<String> turmasId) {
        return turmas.stream()
                .filter(
                        turma ->
                                turmasId.stream()
                                        .anyMatch(turmaId -> turma.getId().equals(turmaId)))
                .collect(Collectors.toList());
    }

    public void adicionarTurmaAProfessor(String idProfessor, String idTurma) {
        Turma turma = obterTurmaPorId(idTurma);

        for (var turmaAtual : turmas) {

            if (turmaAtual.getIdProfessor() != null &&
                turmaAtual.getIdProfessor().equals(idProfessor) &&
                turmaAtual.getId().equals(turma.getId())
            ) {
                throw new TurmaSevicoException("Professor já está na turma");
            }

        }

        turma.setIdProfessor(idProfessor);
    }

    public Horario adicionarHorarioEmTurma(String turmaId, String horarioTurma, String nomeSala) {
        Turma turma = obterTurmaPorId(turmaId);
        Horario novoHorario = new Horario(horarioTurma, turma.getId(), nomeSala);

        for (Turma turmaAtual : turmas) {

            for (Horario horario : turmaAtual.getHorarios()) {
                boolean horarioEmUso = horario.getHorario().equals(novoHorario.getHorario());
                boolean salaEmUso = horario.getSalaId().equals(novoHorario.getSalaId());

                if (horarioEmUso && salaEmUso) {
                    throw new TurmaSevicoException("Horario em uso");
                }

            }

        }

        turma.getHorarios().add(novoHorario);

        return novoHorario;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }


}
