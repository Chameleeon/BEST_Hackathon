drop schema if exists BEST_Hackathon;
create schema BEST_Hackathon;
use BEST_Hackathon;

create table korisnik 
(
	IdKorisnika int auto_increment primary key,
    Ime varchar(20) not null,
    Prezime varchar(20) not null,
    KorisnickoIme varchar(30) not null,
    SaltedHashLozinka varchar(600) not null,
    UkupnoBodova int not null default 0,
    Svijet mediumtext not null,
    Karakter mediumtext not null
);

create table grcki_bog
(
	IdGrckogBoga int auto_increment primary key,
    Ime varchar(30) not null,
    Opis text
);

create table vrsta_stuba
(
	IdVrste int auto_increment primary key,
    NazivVrste varchar(30) not null,
    IdGrckogBoga int not null,
    constraint FK_vrsta_stuba_grcki_bog
    foreign key (IdGrckogBoga)
    references grcki_bog(IdGrckogBoga)
    on update cascade on delete cascade
);

create table korisnikov_stub
(
	IdKorisnikovogStuba int auto_increment primary key,
	IdKorisnika int,
	IdVrste int,
    StanjeStuba double not null default 0,
    PoslednjaAktivnost timestamp,
    constraint FK_angazovanje_korisnik
    foreign key (IdKorisnika)
    references korisnik(IdKorisnika)
    on update cascade on delete cascade,
    constraint FK_angazovanje_vrsta_stuba
    foreign key (IdVrste)
    references vrsta_stuba(IdVrste)
    on update cascade on delete cascade
);

create table izazov
(
	IdIzazova int auto_increment primary key,
    Naziv varchar(30) not null,
    Opis varchar(100) not null,
    VrijemeOd timestamp not null,
    VrijemeDo timestamp not null,
    BrojBodova int not null,
    IdVrste int,
    constraint FK_izazov_vrsta_stuba
    foreign key (IdVrste)
    references vrsta_stuba(IdVrste)
    on update cascade on delete cascade
);

create table aktivnost
(
	IdAktivnosti int auto_increment primary key,
    VrijemeAktivnosti timestamp not null,
    BrojBodova int not null,
    IdKorisnika int not null,
    IdVrste int not null,
    IdIzazova int,
    constraint FK_aktivnost_korisnik
    foreign key (IdKorisnika)
    references korisnik(IdKorisnika)
    on update cascade on delete cascade,
    constraint FK_aktivnost_vrsta_stuba
    foreign key (IdVrste)
    references vrsta_stuba(IdVrste)
    on update cascade on delete cascade,
    constraint FK_aktivnost_izazov
    foreign key (IdIzazova)
    references izazov(IdIzazova)
    on update cascade on delete cascade
);

