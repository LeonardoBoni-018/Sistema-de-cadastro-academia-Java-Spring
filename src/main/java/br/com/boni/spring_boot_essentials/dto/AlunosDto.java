package br.com.boni.spring_boot_essentials.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AlunosDto {
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
}
