package br.com.giscelmo.forum_api.controller;

import br.com.giscelmo.forum_api.domain.usuario.DadosAutenticacao;
import br.com.giscelmo.forum_api.domain.usuario.Usuario;
import br.com.giscelmo.forum_api.infra.security.DadosTokenJWT;
import br.com.giscelmo.forum_api.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity <DadosTokenJWT> login(@RequestBody @Valid DadosAutenticacao dados) {

        var authToken = new UsernamePasswordAuthenticationToken(
                dados.email(),
                dados.senha()
        );

        var authentication = authenticationManager.authenticate(authToken);

        var token = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(token));
    }
}
