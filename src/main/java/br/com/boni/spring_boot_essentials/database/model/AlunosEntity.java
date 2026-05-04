package br.com.boni.spring_boot_essentials.database.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "alunos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AlunosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "avaliacao_fisica_id")
    private AvaliacaoFisicasEntity avaliacaoFisica;

    @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY)
    private Set<TreinosEnity> treinos = new HashSet<>();
}
