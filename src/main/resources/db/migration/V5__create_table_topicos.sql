create table topicos(
    id bigserial primary key,
    titulo varchar(100) not null,
    mensagem text not null,
    data_criacao timestamp not null,
    status varchar(50) not null,

    autor_id bigint not null,
    curso_id bigint not null,

    constraint fk_topico_autor
        foreign key (autor_id)
        references usuarios(id),

    constraint fk_topico_curso
        foreign key (curso_id)
        references cursos(id)
)