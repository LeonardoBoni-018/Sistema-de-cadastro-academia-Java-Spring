package br.com.boni.spring_boot_essentials.service;

import br.com.boni.spring_boot_essentials.database.model.AlunosEntity;
import br.com.boni.spring_boot_essentials.database.model.AvaliacaoFisicasEntity;
import br.com.boni.spring_boot_essentials.database.model.ExerciciosEnity;
import br.com.boni.spring_boot_essentials.database.model.TreinosEnity;
import br.com.boni.spring_boot_essentials.database.repository.IAlunosRepository;
import br.com.boni.spring_boot_essentials.database.repository.IExerciciosRepository;
import br.com.boni.spring_boot_essentials.database.repository.ITreinosRepository;
import br.com.boni.spring_boot_essentials.dto.AlunosDto;
import br.com.boni.spring_boot_essentials.dto.TreinosDto;
import br.com.boni.spring_boot_essentials.exception.BadrequestExeption;
import br.com.boni.spring_boot_essentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TreinoService {
    private final ITreinosRepository treinosRepository;
    private final IExerciciosRepository exerciciosRepository;
    private final IAlunosRepository alunosRepository;

    public void criarTreino(TreinosDto treinosDto) throws NotFoundException, BadrequestExeption {
        Set<ExerciciosEnity> exercicios = new HashSet<>();
        AlunosEntity aluno = alunosRepository.findById(treinosDto.getAlunoId()).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        TreinosEnity treino = treinosRepository.findByNomeAndAlunoId(treinosDto.getNome(), treinosDto.getAlunoId()).orElse(null);

        if(treino != null){
            throw new BadrequestExeption("Já existe um treino com este nome para este aluno!");
        }

        for (Integer exercicioId : treinosDto.getExerciciosIds()){
            ExerciciosEnity exercicio = exerciciosRepository.findById(exercicioId).orElseThrow(() -> new NotFoundException("Exercício não encontrado " + exercicioId));

            exercicios.add(exercicio);
        }

        treino = TreinosEnity.builder()
                .nome(treinosDto.getNome())
                .aluno(aluno)
                .exercicios(exercicios)
                .build();

        treinosRepository.save(treino);
    }
}
