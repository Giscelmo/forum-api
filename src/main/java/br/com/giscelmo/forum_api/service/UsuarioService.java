package br.com.giscelmo.forum_api.service;

import br.com.giscelmo.forum_api.domain.usuario.DadosCadastroUsuario;
import br.com.giscelmo.forum_api.domain.usuario.Usuario;
import br.com.giscelmo.forum_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    @Transactional
    public Usuario cadastrar(DadosCadastroUsuario dados) {
        var usuario = new Usuario(dados);
        return repository.save(usuario);
    }
}
