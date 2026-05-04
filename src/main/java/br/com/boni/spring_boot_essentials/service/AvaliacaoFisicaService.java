package br.com.boni.spring_boot_essentials.service;

import br.com.boni.spring_boot_essentials.database.model.AlunosEntity;
import br.com.boni.spring_boot_essentials.database.model.AvaliacaoFisicasEntity;
import br.com.boni.spring_boot_essentials.database.repository.IAlunosRepository;
import br.com.boni.spring_boot_essentials.database.repository.IAvaliacoesFisicasRepository;
import br.com.boni.spring_boot_essentials.dto.AvaliacoesFisicasProjection;
import br.com.boni.spring_boot_essentials.dto.AvalicaoFisicaDto;
import br.com.boni.spring_boot_essentials.exception.BadrequestExeption;
import br.com.boni.spring_boot_essentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoFisicaService {
    private final IAlunosRepository alunosRepository;
    private final IAvaliacoesFisicasRepository avaliacoesFisicasRepository;


    public void criarAvaliacaoFisica(AvalicaoFisicaDto avalicaoFisicaDto) throws NotFoundException, BadrequestExeption {
        AlunosEntity aluno = alunosRepository.findById(avalicaoFisicaDto.getIdAluno()).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        AvaliacaoFisicasEntity avaliacaoFisica = aluno.getAvaliacaoFisica();

        if(avaliacaoFisica != null){
            throw new BadrequestExeption("Avaliação física já cadastrada para este aluno");
        }

        avaliacaoFisica = AvaliacaoFisicasEntity.builder()
                .peso(avalicaoFisicaDto.getPeso())
                .altura(avalicaoFisicaDto.getAltura())
                .porcentagemGorduraCorporal(avalicaoFisicaDto.getPorcentualDeGordura())
                .build();

        aluno.setAvaliacaoFisica(avaliacaoFisica);
        alunosRepository.save(aluno);
    }

    public List<AvaliacoesFisicasProjection> getAllAvaliacoes(){
        return avaliacoesFisicasRepository.getAllAvaliacoes();
    }

    public Page<AvaliacoesFisicasProjection> getAllAvaliacoesPage(Integer page, Integer size){
        return avaliacoesFisicasRepository.getAllAvaliacoesPage(PageRequest.of(page, size));
    }
}
