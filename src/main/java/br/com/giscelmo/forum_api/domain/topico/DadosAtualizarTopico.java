package br.com.giscelmo.forum_api.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizarTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem
) {
}
