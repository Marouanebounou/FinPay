use sql7816780;
create table prestataire(
	id_pre int primary key,
    nom varchar(20),
    typePre varchar(30),
    email varchar(30),
    passwordPer varchar(30)
);
create table statistiaue(
	id_stat int primary key,
    id_fact int,
    id_client int,
    id_pre int,
    totalAmount decimal(10 ,2),
    totalComission decimal(10 , 2)
);

create table paiment(
	id_pai int primary key,
    date_pai date,
    balance decimal(10 , 2),
    id_fact int
);

alter table statistiaue
add constraint fk_prestataire
foreign key(id_pre) references prestataire(id_pre);




