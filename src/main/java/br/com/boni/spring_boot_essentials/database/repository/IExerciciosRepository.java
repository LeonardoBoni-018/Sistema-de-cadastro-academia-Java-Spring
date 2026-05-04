package br.com.boni.spring_boot_essentials.database.repository;

import br.com.boni.spring_boot_essentials.database.model.ExerciciosEnity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IExerciciosRepository extends JpaRepository<ExerciciosEnity, Integer> {

    // Query metodo
    List<ExerciciosEnity> findAllByGrupoMuscular(String grupomuscular);

    // JPQL
//    @Query(value = """
//    SELECT e
//     FROM ExerciciosEntity e
//      WHERE UPPER(e.grupoMuscular) = UPPER(:grupomuscular)
//""")
//    List<ExerciciosEnity> findAllByGrupoMuscularJpql(@Param("grupomuscular") String grupomuscular);


    // Native query
//    @NativeQuery(value = """
//    SELECT e
//     FROM exercicios e
//      WHERE UPPER(e.grupo_muscular) = UPPER(:grupomuscular)
//""")
//    List<ExerciciosEnity> findAllByGrupoMuscularNAtiveQuery(@Param("grupomuscular") String grupomuscular);
}
