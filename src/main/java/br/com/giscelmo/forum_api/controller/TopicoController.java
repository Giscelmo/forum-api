package br.com.giscelmo.forum_api.controller;

import br.com.giscelmo.forum_api.domain.topico.DadosAtualizarTopico;
import br.com.giscelmo.forum_api.domain.topico.DadosCadastroTopico;
import br.com.giscelmo.forum_api.domain.topico.DadosDetalhamentoTopico;
import br.com.giscelmo.forum_api.domain.topico.DadosListagemTopico;
import br.com.giscelmo.forum_api.repository.TopicoRepository;
import br.com.giscelmo.forum_api.service.TopicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
@RequiredArgsConstructor //Cria automaticamente um construtor com todos os campos final
public class TopicoController {

    private final TopicoService service;
    private TopicoRepository repository;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoTopico> cadastrar(
            @RequestBody @Valid DadosCadastroTopico dados,
            UriComponentsBuilder uriBuilder
    ) {
        var topico = service.cadastrar(dados);
        var uri = uriBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(
            @PageableDefault(
                    size = 10,
                    sort = "dataCriacao",
                    direction = Sort.Direction.ASC
            )
            Pageable paginacao
    ) {
        var page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> detalhar(@PathVariable Long id) {
        return service.detalhar(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizarTopico dados
    ) {
        var topico = service.atualizar(id, dados);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
