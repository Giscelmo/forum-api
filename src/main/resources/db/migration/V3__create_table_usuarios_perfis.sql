create table usuarios_perfis(
    usuario_id bigint not null,
    perfil_id bigint not null,

    constraint fk_usuario
        foreign key (usuario_id)
        references usuarios(id),

    constraint fk_perfil
        foreign key (perfil_id)
        references perfis(id),

    primary key(usuario_id, perfil_id)

)