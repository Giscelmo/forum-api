package br.com.giscelmo.forum_api.repository;

import br.com.giscelmo.forum_api.domain.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
