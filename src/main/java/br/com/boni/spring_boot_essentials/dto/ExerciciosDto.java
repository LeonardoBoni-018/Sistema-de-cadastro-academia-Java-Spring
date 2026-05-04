package br.com.boni.spring_boot_essentials.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ExerciciosDto {
    @NotBlank
    private String nome;
    @NotBlank
    private String grupoMuscular;
}
