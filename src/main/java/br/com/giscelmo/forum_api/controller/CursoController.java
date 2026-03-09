package br.com.giscelmo.forum_api.controller;

import br.com.giscelmo.forum_api.domain.curso.DadosCadastroCurso;
import br.com.giscelmo.forum_api.domain.curso.DadosDetalhamentoCurso;
import br.com.giscelmo.forum_api.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    private final CursoService service;

    public CursoController(CursoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoCurso> cadastrar(
            @RequestBody @Valid DadosCadastroCurso dados,
            UriComponentsBuilder uriBuilder
    ) {
        var curso = service.cadastrar(dados);
        var uri =uriBuilder
                .path("/cursos/{id}")
                .buildAndExpand(curso.getId())
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(new DadosDetalhamentoCurso(curso));
    }

}
