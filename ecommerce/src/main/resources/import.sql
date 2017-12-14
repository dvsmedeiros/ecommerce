insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0001', 'Elena Sterley');
insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0002', 'Roderick Ceney');
insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0003', 'Earvin Demaine');
insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0004', 'Leann Coat');
insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0005', 'Chrissy Illiston');
insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0006', 'Peadar Blaycock');
insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0007', 'Tabor Ricciardelli');
insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0008', 'Averell Bromilow');
insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0009', 'Charmine Struis');
insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0010', 'Mateo Gimson');
insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0011', 'Christiana Itzig');
insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0012', 'Kaiser Djokovic');
insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0013', 'Libbey Vesty');
insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0014', 'Karissa Hassewell');
insert into authors values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'AUT0015', 'Renate Bienvenu');

insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0001','Wolf, Konopelski and Rodriguez');
insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0002','Schneider Inc');
insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0003','Nader Group');
insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0004','Douglas-Hoeger');
insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0005','Bartoletti-Bartoletti');
insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0006','Tillman Group');
insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0007','Swift, Volkman and Will');
insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0008','Wolff-Spencer');
insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0009','Mueller-Macejkovic');
insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0010','Langworth, Cummings and Hartmann');
insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0011','Leuschke-Schamberger');
insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0012','White-Auer');
insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0013','Miller, Kirlin and Waters');
insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0014','Botsford Inc');
insert into publishers values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PUB0015','Durgan Inc');

insert into price_groups values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PRG0001', 'Grupo Preço 5%', 0.05);
insert into price_groups values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PRG0002', 'Grupo Preço 10%', 0.1);
insert into price_groups values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PRG0003', 'Grupo Preço 50%', 0.5);

insert into category_types values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'PROD', 'Produto');
insert into category_types values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'INACT', 'Inativação');

insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0001', 'Administração', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0002', 'Agropecuária', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0003', 'Artes', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0004', 'Informática', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0005', 'Autoajuda', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0006', 'Ciências Biológicas', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0007', 'Ciências Exatas', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0008', 'Ciências Humanas e Sociais', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0009', 'Contabilidade', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0010', 'Cursos e Idiomas', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0011', 'Dicionários e Manuais Conversação', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0012', 'Didáticos', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0013', 'Direito', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0014', 'Economia', (select ID from category_types where category_types.code = 'PROD'));
insert into categories values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'CAT0015', 'Engenharia e Tecnologia', (select ID from category_types where category_types.code = 'PROD'));

insert into configurations values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'INACTIVATION_BOOK_RATE', 'Intervalo de execução da task de inativação de livros sem estoque', '60000');
insert into configurations values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'MAX_COUPON_PROMOTION', 'Quantidade máxima de cupons de promoção', '1');
insert into configurations values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'MIN_SALE_FOR_INACTIVATION', 'Quantidade minima de venda para inativação', '10');

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

insert into cupons values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'RODRIGOAJUDANOIS', null, sysdate, 'PROMOTION', 59.99, null);
insert into cupons values (HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'LES2017', null, sysdate, 'PROMOTION', 15, null);

insert into roles values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'ADMIN', 'Administrador');
insert into roles values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, 'USER', 'Usuário');
insert into users values(HIBERNATE_SEQUENCE.nextval, sysdate, 1, null, null, 'admin', 'admin')
insert into users_roles values((select id from users where email = 'admin'), (select id from roles where code = 'ADMIN'));
insert into users_roles values((select id from users where email = 'admin'), (select id from roles where code = 'USER'));