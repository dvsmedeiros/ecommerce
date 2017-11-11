insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0001', 'Autor 1');
insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0002', 'Autor 2');
insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0003', 'Autor 3');
insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0004', 'Autor 4');
insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0005', 'Autor 5');

insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0001', 'Editora 1');
insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0002', 'Editora 2');
insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0003', 'Editora 3');
insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0004', 'Editora 4');
insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0005', 'Editora 5');

insert into price_groups values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PRG0001', 'Grupo Preço 1', 0.05);
insert into price_groups values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PRG0002', 'Grupo Preço 2', 0.1);
insert into price_groups values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PRG0003', 'Grupo Preço 3', 0.5);

insert into category_types values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PROD', 'Produto');
insert into category_types values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'INACT', 'Inativação');

insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0001', 'Categoria 1', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0002', 'Categoria 2', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0003', 'Categoria 3', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0004', 'Categoria 4', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0005', 'Categoria 5', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0006', 'Fora de Mercado', (select ID from category_types where category_types.code = 'INACT'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0007', 'Outro', (select ID from category_types where category_types.code = 'INACT'));

insert into configurations values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'INACT_BOOKS', 'Inativa Livros RF0013', '60000');

insert into record_types values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'IN', 'Entrada');
insert into record_types values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'OUT', 'Saida');

insert into address_types values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'RES', 'Residencial');
insert into address_types values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'COM', 'Comercial');

insert into countries values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'BR', 'Brasil');

insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'SP', 'São Paulo', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AC', 'Acre', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AL', 'Alagoas', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AP', 'Amapá', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AP', 'Amapá', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AM', 'Amazonas', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'BA', 'Bahia', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CE', 'Ceará', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'DF', 'Distrito Federal', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'ES', 'Espirito Santo', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'GO', 'Goiás', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'MA', 'Maranhão', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'MT', 'Mato Grosso', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'MS', 'Mato Grosso do Sul', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'MG', 'Minas Gerais', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PA', 'Pará', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PB', 'Paraíba', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PR', 'Paraná', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PE', 'Pernambuco', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PI', 'Piauí', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'RJ', 'Rio de Janeiro', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'RN', 'Rio Grande do Norte', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'RS', 'Rio Grande do Sul', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'RO', 'Rondônia', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'RR', 'Rorâima', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'SC', 'Santa Catarina', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'SE', 'Sergipe', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));
insert into states values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'TO', 'Tocantins', (select ID from COUNTRIES where COUNTRIES.CODE = 'BR'));

insert into card_flags values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'VISA', 'Visa');
insert into card_flags values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'MASTER', 'Master Card');
insert into card_flags values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'ELO', 'Elo');

insert into status_order values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PROCESSING', 'EM PROCESSAMENTO');
insert into status_order values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PAYMENT', 'AGUARDANDO PAGAMENTO');
insert into status_order values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'SEPARATION', 'EM SEPARAÇÃO');
insert into status_order values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'TRANSPORTATION', 'EM TRANSPORTE');
insert into status_order values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'DELIVERY', 'ENTREGUE');
insert into status_order values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CANCELED', 'CANCELADO');
insert into status_order values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'EXCHANGE', 'EM TROCA');
insert into status_order values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'EXCHANGED', 'TROCADO');
insert into status_order values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'APPROVED', 'APROVADO');
insert into status_order values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'DISAPPROVED', 'REPROVADO');

insert into roles values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'ADMIN', 'Administrador');
insert into roles values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'USER', 'Usuário');
--insert into users values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, null, null, 'admin', 'admin')
--insert into users_roles values((select id from users where email = 'admin'), (select id from roles where code = 'ADMIN'));
--insert into users_roles values((select id from users where email = 'admin'), (select id from roles where code = 'USER'));