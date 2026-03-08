package br.com.giscelmo.forum_api.repository;

import br.com.giscelmo.forum_api.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
