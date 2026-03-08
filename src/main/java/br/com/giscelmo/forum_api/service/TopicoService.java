package br.com.giscelmo.forum_api.service;

import br.com.giscelmo.forum_api.domain.topico.DadosCadastroTopico;
import br.com.giscelmo.forum_api.domain.topico.Topico;
import br.com.giscelmo.forum_api.repository.CursoRepository;
import br.com.giscelmo.forum_api.repository.TopicoRepository;
import br.com.giscelmo.forum_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public TopicoService(
            TopicoRepository topicoRepository,
            UsuarioRepository usuarioRepository,
            CursoRepository cursoRepository
    ) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    public Topico cadastrar(DadosCadastroTopico dados) {
        if (topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            throw new RuntimeException("Já existe um tópico com o mesmo título e mensagem.");
        }

        var autor = usuarioRepository.findById(dados.idAutor())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado."));

        var curso = cursoRepository.findById(dados.idCurso())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado."));

        var topico = new Topico(dados, autor, curso);

        return topicoRepository.save(topico);
    }
}
