package br.com.boni.spring_boot_essentials.database.repository;

import br.com.boni.spring_boot_essentials.database.model.AlunosEntity;
import br.com.boni.spring_boot_essentials.database.model.AvaliacaoFisicasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAlunosRepository extends JpaRepository<AlunosEntity, Integer> {

    Optional<AlunosEntity> findByEmail(String email);

    @Query("SELECT a FROM AlunosEntity a LEFT JOIN FETCH a.avaliacaoFisica WHERE a.id = :alunoId")
    Optional<AlunosEntity> findByIdFetch(@Param("alunoId") Integer alunoId);
}
