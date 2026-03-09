package br.com.giscelmo.forum_api.service;

import br.com.giscelmo.forum_api.domain.topico.*;
import br.com.giscelmo.forum_api.repository.CursoRepository;
import br.com.giscelmo.forum_api.repository.TopicoRepository;
import br.com.giscelmo.forum_api.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor //Cria automaticamente um construtor com todos os campos final
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

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

    @Transactional(readOnly = true) // Esse metodo só vai ler dados, não vai alterar nada no banco
    public Page<DadosListagemTopico> listar(Pageable paginacao) {
        return topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);
    }

    @Transactional(readOnly = true) // Esse metodo só vai ler dados, não vai alterar nada no banco
    public ResponseEntity<DadosDetalhamentoTopico> detalhar(Long id) {
      return topicoRepository.findById(id)
                .map(t -> ResponseEntity.ok(new DadosDetalhamentoTopico(t)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public Topico atualizar(Long id, DadosAtualizarTopico dados) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topico não encontrado"));

        if (topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            throw new RuntimeException("Já existe um tópico com esse título e mensagem.");
        }

        topico.atualizarInformacoes(dados);
        return topico;
    }

    @Transactional
    public void excluir(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new RuntimeException("Tópico não encontrado.");
        }
        topicoRepository.deleteById(id);

    }
}
