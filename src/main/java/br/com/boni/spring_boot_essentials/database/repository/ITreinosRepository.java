package br.com.boni.spring_boot_essentials.database.repository;

import br.com.boni.spring_boot_essentials.database.model.ExerciciosEnity;
import br.com.boni.spring_boot_essentials.database.model.TreinosEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITreinosRepository extends JpaRepository<TreinosEnity, Integer> {

    Optional<TreinosEnity> findByNomeAndAlunoId(String nome, Integer alunoId);
}
