package br.com.boni.spring_boot_essentials.controller;

import br.com.boni.spring_boot_essentials.database.model.AvaliacaoFisicasEntity;
import br.com.boni.spring_boot_essentials.dto.AlunosDto;
import br.com.boni.spring_boot_essentials.dto.TreinosDto;
import br.com.boni.spring_boot_essentials.exception.BadrequestExeption;
import br.com.boni.spring_boot_essentials.exception.NotFoundException;
import br.com.boni.spring_boot_essentials.service.AlunosService;
import br.com.boni.spring_boot_essentials.service.TreinoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/treinos")
@RequiredArgsConstructor
@Validated
public class TreinosController {

    private final TreinoService treinoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
   public void criarTreino(@Valid @RequestBody TreinosDto treinosDto) throws NotFoundException, BadrequestExeption {
        treinoService.criarTreino(treinosDto);
    }
}
