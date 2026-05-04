package br.com.boni.spring_boot_essentials.controller;

import br.com.boni.spring_boot_essentials.database.model.AvaliacaoFisicasEntity;
import br.com.boni.spring_boot_essentials.dto.AlunosDto;
import br.com.boni.spring_boot_essentials.dto.AvalicaoFisicaDto;
import br.com.boni.spring_boot_essentials.exception.BadrequestExeption;
import br.com.boni.spring_boot_essentials.exception.NotFoundException;
import br.com.boni.spring_boot_essentials.service.AlunosService;
import br.com.boni.spring_boot_essentials.service.AvaliacaoFisicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/alunos")
@RequiredArgsConstructor
@Validated
public class AlunosController {

    private final AlunosService alunosService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAluno(@Valid @RequestBody AlunosDto alunosDto) throws BadrequestExeption {
        alunosService.criarAluno(alunosDto);
    }

    @GetMapping("/{alunoId}/avalicao")
    public AvaliacaoFisicasEntity getAvaliacaoFisica(@PathVariable Integer alunoId) throws NotFoundException {
        return alunosService.getAlunoAvaliacao(alunoId);
    }

    @DeleteMapping("/{alunoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerAluno(@PathVariable Integer alunoId) throws NotFoundException {
        alunosService.deletarAluno(alunoId);
    }
}
