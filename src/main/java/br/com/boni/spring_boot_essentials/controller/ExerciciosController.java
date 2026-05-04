package br.com.boni.spring_boot_essentials.controller;

import br.com.boni.spring_boot_essentials.database.model.ExerciciosEnity;
import br.com.boni.spring_boot_essentials.dto.ExerciciosDto;
import br.com.boni.spring_boot_essentials.service.ExerciciosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/exercicios")
@RequiredArgsConstructor
@Validated
public class ExerciciosController {

    private final ExerciciosService exerciciosService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciciosEnity> findAll(){
        return exerciciosService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExercicio(@Valid @RequestBody ExerciciosDto exerciciosDto){
        exerciciosService.save(exerciciosDto);
    }

    @GetMapping("/grupos/{grupoMuscular}")
    @ResponseStatus(HttpStatus.OK)
    public List<ExerciciosEnity> getExerciciosBygrupoMusuclar(
            @PathVariable String grupoMuscular){
        return exerciciosService.getExerciciosByGrupoMusucular(grupoMuscular);
    }
}
