package br.com.giscelmo.forum_api.controller;

import br.com.giscelmo.forum_api.domain.usuario.DadosCadastroUsuario;
import br.com.giscelmo.forum_api.domain.usuario.DadosDetalhamentoUsuario;
import br.com.giscelmo.forum_api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(
            @RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder
    ) {
        var usuario = service.cadastrar(dados);

        var uri = uriBuilder
                .path("/usuario/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new DadosDetalhamentoUsuario(usuario));
    }

}
