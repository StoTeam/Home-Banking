CREATE TABLE intestatario (
	id int auto_increment primary key
);

CREATE TABLE utente (
	id int auto_increment primary key ,
	nome varchar(255),
    cognome varchar(255),
    telefono char(11),
    email varchar(255) unique,
	pass varchar(1024),
    tipo_utente int,
    indirizzo varchar(255),
    codice_fiscale char(16),
    id_intestatario int,
    FOREIGN KEY (id_intestatario) REFERENCES intestatario(id)
);

CREATE TABLE amministratore (
	id int auto_increment primary key,
	nome varchar(255),
    cognome varchar(255),
    telefono char(11),
    email varchar(255) unique,
    pass varchar(1024),
    tipo_utente varchar(20),
    indirizzo varchar(255),
    livello_accesso varchar(20),
    area_competenza varchar(255)
);

CREATE TABLE aziende (
	id int auto_increment primary key ,
	nome varchar(255),
    cognome varchar(255),
    telefono char(11),
    email varchar(255) unique,
	passw varchar(1024),
    tipo_utente int,
    indirizzo varchar(255),
    ragione_sociale varchar(255),
    partita_iva char(22) unique,
    id_intestatario int,
    FOREIGN KEY (id_intestatario) REFERENCES intestatario(id)
);

CREATE TABLE conto (
	id int auto_increment primary key,
    codice_conto int unique,
    iban char(27) unique,
    saldo double,
    saldo_contabile double,
    id_intestatario int,
    FOREIGN KEY (id_intestatario) REFERENCES intestatario(id)
);

CREATE TABLE carta (
	id int auto_increment primary key,
	is_block boolean,
    spesa_mensile double,
    data_rilascio date,
    data_scadenza date,
    codice_sicurezza char(3),
    pin char(5),
    limite double,
    disponibilita double,
    uso_pin boolean,
    conto_id int,
    FOREIGN KEY (conto_id) REFERENCES conto(id)
);

CREATE TABLE movimento_conto (
	id int auto_increment primary key,
    tipo_movimento varchar(255),
    importo double,
    conto_id_m int,                       -- CodiceConto Mittente
    data_esecuzione Date,
    conto_id_d int,                       -- CodiceConto Destinatario
    data_arrivo Date,
    causale varchar(1024),
    carta_id int,
    FOREIGN KEY (conto_id_m) REFERENCES conto(id),
    FOREIGN KEY (conto_id_d) REFERENCES conto(id),
    FOREIGN KEY (carta_id) REFERENCES carta(id)
);

CREATE TABLE prestito (
	id int auto_increment primary key,
    importo double,
	dovuti double,
	pagati double,
	tan double,
	taeg double,
	tempo int,                           -- per quanto tempo dura il prestito
	is_fisso boolean,
	is_approvato boolean,
	data_inizio date,
	data_fine date,
	data_rata date,
	conto_id int,
    FOREIGN KEY (conto_id) REFERENCES conto(id)
);