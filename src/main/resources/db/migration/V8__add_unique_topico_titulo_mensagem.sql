alter table topicos
add constraint uk_topico_titulo_mensagem
unique (titulo, mensagem);