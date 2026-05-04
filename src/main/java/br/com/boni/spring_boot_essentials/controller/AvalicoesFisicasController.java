package br.com.boni.spring_boot_essentials.controller;

import br.com.boni.spring_boot_essentials.database.model.ExerciciosEnity;
import br.com.boni.spring_boot_essentials.dto.AvaliacoesFisicasProjection;
import br.com.boni.spring_boot_essentials.dto.AvalicaoFisicaDto;
import br.com.boni.spring_boot_essentials.dto.ExerciciosDto;
import br.com.boni.spring_boot_essentials.exception.BadrequestExeption;
import br.com.boni.spring_boot_essentials.exception.NotFoundException;
import br.com.boni.spring_boot_essentials.service.AvaliacaoFisicaService;
import br.com.boni.spring_boot_essentials.service.ExerciciosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/avaliacoes")
@RequiredArgsConstructor
@Validated
public class AvalicoesFisicasController {

    private final AvaliacaoFisicaService avaliacaoFisicaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAvaliacaoFisica(@Valid @RequestBody AvalicaoFisicaDto avalicaoFisicaDto) throws NotFoundException, BadrequestExeption {
        avaliacaoFisicaService.criarAvaliacaoFisica(avalicaoFisicaDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AvaliacoesFisicasProjection> getAllAvaliacoes(){
        return avaliacaoFisicaService.getAllAvaliacoes();
    }

    @GetMapping("/page/{page}/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public Page<AvaliacoesFisicasProjection> getAllAvaliacoesPage(@PathVariable Integer page, @PathVariable Integer size){
        return avaliacaoFisicaService.getAllAvaliacoesPage(page,size);
    }
}
