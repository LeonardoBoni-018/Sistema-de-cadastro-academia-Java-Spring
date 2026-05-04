package br.com.boni.spring_boot_essentials.service;

import br.com.boni.spring_boot_essentials.database.model.ExerciciosEnity;
import br.com.boni.spring_boot_essentials.database.repository.IExerciciosRepository;
import br.com.boni.spring_boot_essentials.dto.ExerciciosDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciciosService {

    private final IExerciciosRepository exerciciosRepository;

    public List<ExerciciosEnity> findAll(){
        return exerciciosRepository.findAll();
    }

    public void save(ExerciciosDto exerciciosDto){
        exerciciosRepository.save(ExerciciosEnity.builder()
                .nome(exerciciosDto.getNome())
                .grupoMuscular(exerciciosDto.getGrupoMuscular())
                .build());
    }

    public List<ExerciciosEnity> getExerciciosByGrupoMusucular(String grupoMusucular){
        return exerciciosRepository.findAllByGrupoMuscular(grupoMusucular);
    }
}
