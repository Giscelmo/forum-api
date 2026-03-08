create table respostas(
    id bigserial primary key,
    mensagem text not null,
    data_criacao timestamp not null,
    solucao boolean not null default false,

    topico_id bigint not null,
    autor_id bigint not null,

    constraint fk_resposta_topico
        foreign key (topico_id)
        references topicos(id),

    constraint fk_resposta_autor
        foreign key (autor_id)
        references usuarios(id)
)