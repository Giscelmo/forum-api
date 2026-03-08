package br.com.giscelmo.forum_api.domain.topico;

import br.com.giscelmo.forum_api.domain.curso.Curso;
import br.com.giscelmo.forum_api.domain.resposta.Resposta;
import br.com.giscelmo.forum_api.domain.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhamentoTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        StatusTopico status,
        String autor,
        String curso

) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome());
    }
}
