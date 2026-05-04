package br.com.boni.spring_boot_essentials.service;

import br.com.boni.spring_boot_essentials.database.model.AlunosEntity;
import br.com.boni.spring_boot_essentials.database.model.AvaliacaoFisicasEntity;
import br.com.boni.spring_boot_essentials.database.model.TreinosEnity;
import br.com.boni.spring_boot_essentials.database.repository.IAlunosRepository;
import br.com.boni.spring_boot_essentials.database.repository.IAvaliacoesFisicasRepository;
import br.com.boni.spring_boot_essentials.database.repository.ITreinosRepository;
import br.com.boni.spring_boot_essentials.dto.AlunosDto;
import br.com.boni.spring_boot_essentials.exception.BadrequestExeption;
import br.com.boni.spring_boot_essentials.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunosService {
    private final IAlunosRepository alunosRepository;
    private final ITreinosRepository treinosRepository;
    private final IAvaliacoesFisicasRepository avaliacoesFisicasRepository;

    public void criarAluno(AlunosDto alunosDto) throws BadrequestExeption {
        AlunosEntity aluno = alunosRepository.findByEmail(alunosDto.getEmail()).orElse(null);

        if(aluno != null){
            throw  new BadrequestExeption("Aluno já cadastrado com este email");
        }

        alunosRepository.save(AlunosEntity.builder()
                .nome(alunosDto.getNome())
                .email(alunosDto.getEmail())
                .build());
    }

    public AvaliacaoFisicasEntity getAlunoAvaliacao(Integer alunoId) throws NotFoundException {
        AlunosEntity aluno = alunosRepository.findByIdFetch(alunoId).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        AvaliacaoFisicasEntity avaliacao = aluno.getAvaliacaoFisica();
        if (avaliacao == null){
            throw  new NotFoundException("Avaliação física não encontrada!");
        }
        return avaliacao;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletarAluno(Integer alunoId) throws NotFoundException {
        AlunosEntity aluno = alunosRepository.findByIdFetch(alunoId).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        List<Integer> treinosAlunoIds = aluno.getTreinos().stream().map(TreinosEnity:: getId).toList();
        treinosRepository.deleteAllById(treinosAlunoIds);

        alunosRepository.deleteById(alunoId);

        avaliacoesFisicasRepository.deleteById(aluno.getAvaliacaoFisica().getId());

    }

}
