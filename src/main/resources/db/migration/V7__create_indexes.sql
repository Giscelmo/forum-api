create index idx_topicos_autor on topicos(autor_id);

create index idx_topicos_curso on topicos(curso_id);

create index idx_respostas_topico on respostas(topico_id);

create index idx_respostas_autor on respostas(autor_id);