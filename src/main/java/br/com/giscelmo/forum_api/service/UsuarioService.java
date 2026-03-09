package br.com.giscelmo.forum_api.service;

import br.com.giscelmo.forum_api.domain.usuario.DadosCadastroUsuario;
import br.com.giscelmo.forum_api.domain.usuario.Usuario;
import br.com.giscelmo.forum_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario cadastrar(DadosCadastroUsuario dados) {
        var usuario = new Usuario(
                dados.nome(),
                dados.email(),
                passwordEncoder.encode(dados.senha())
        );
        return repository.save(usuario);
    }
}
