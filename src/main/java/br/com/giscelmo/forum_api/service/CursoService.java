package br.com.giscelmo.forum_api.service;

import br.com.giscelmo.forum_api.domain.curso.Curso;
import br.com.giscelmo.forum_api.domain.curso.DadosCadastroCurso;
import br.com.giscelmo.forum_api.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoService {

    private final CursoRepository repository;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Curso cadastrar(DadosCadastroCurso dados) {
        var curso = new Curso(dados);
        return repository.save(curso);
    }
}
