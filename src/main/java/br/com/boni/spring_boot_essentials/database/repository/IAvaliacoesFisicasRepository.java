package br.com.boni.spring_boot_essentials.database.repository;

import br.com.boni.spring_boot_essentials.database.model.AvaliacaoFisicasEntity;
import br.com.boni.spring_boot_essentials.database.model.ExerciciosEnity;
import br.com.boni.spring_boot_essentials.dto.AvaliacoesFisicasProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAvaliacoesFisicasRepository extends JpaRepository<AvaliacaoFisicasEntity, Integer> {

    @Query(value = """
        SELECT a.id AS idAluno,
               a.nome AS nomeAluno,
               af.id AS idAvaliacao,
               af.peso AS peso,
               af.altura AS altura,
               af.procentual_gordura_corporal AS porcentagemGorduraCorporal
        FROM avaliacoes_fisicas af
        INNER JOIN alunos a
            ON a.avaliacao_fisica_id = af.id
    """, nativeQuery = true)
    List<AvaliacoesFisicasProjection> getAllAvaliacoes();


    @Query(value = """
        SELECT a.id AS idAluno,
               a.nome AS nomeAluno,
               af.id AS idAvaliacao,
               af.peso AS peso,
               af.altura AS altura,
               af.procentual_gordura_corporal AS porcentagemGorduraCorporal
        FROM avaliacoes_fisicas af
        INNER JOIN alunos a
            ON a.avaliacao_fisica_id = af.id
    """,
            countQuery = """
        SELECT COUNT(af.id)
        FROM avaliacoes_fisicas af
        INNER JOIN alunos a
            ON a.avaliacao_fisica_id = af.id
    """,
            nativeQuery = true)
    Page<AvaliacoesFisicasProjection> getAllAvaliacoesPage(Pageable pageable);
}
