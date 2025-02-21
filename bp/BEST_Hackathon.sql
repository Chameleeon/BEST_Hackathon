drop schema if exists BEST_Hackathon;
create schema BEST_Hackathon;
use BEST_Hackathon;

create table korisnik 
(
	IdKorisnika int auto_increment primary key,
    Ime varchar(20) not null,
    Prezime varchar(20) not null,
    KorisnickoIme varchar(30) not null,
    UkupnoBodova int not null default 0,
    Svijet text not null,
    Karakter text not null
);

create table vrsta_stuba
(
	IdVrste int auto_increment primary key,
    NazivKategorije varchar(30) not null
);

create table angazovanje
(
	IdKorisnika int,
	IdVrste int,
    primary key (IdKorisnika, IdVrste),
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
    BrojBodova int not null
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