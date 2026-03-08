package br.com.giscelmo.forum_api.controller;

import br.com.giscelmo.forum_api.domain.topico.DadosCadastroTopico;
import br.com.giscelmo.forum_api.domain.topico.DadosDetalhamentoTopico;
import br.com.giscelmo.forum_api.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos")
public class TopicoController {
    private final TopicoService service;

    public TopicoController(TopicoService service) {
        this.service = service;
    }

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
}
