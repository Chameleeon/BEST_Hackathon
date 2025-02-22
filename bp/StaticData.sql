use BEST_Hackathon;

insert into grcki_bog(Ime, Opis) values ("Hefest", "Hefest is the Greek god of artisans, blacksmiths, carpenters, craftsmen, fire, metallurgy, metalworking, sculpture and volcanoes."), 
("Hermes", "Hermes is the ancient Greek god of trade, wealth, luck, fertility, animal husbandry, sleep, language, thieves, and travel."), 
("Hades", "Hades is the god of the underworld."),
("Demeter", "Demeter is the Olympian goddess of the harvest and agriculture, presiding over crops, grains, food, and the fertility of the earth.");
insert into vrsta_stuba(NazivVrste, IdGrckogBoga) values ("Energija", 1), ("Transport", 2), ("Otpad", 3), ("Prehrana", 4);

insert into korisnik(Ime, Prezime, KorisnickoIme, UkupnoBodova, SaltedHashLozinka, Svijet, Karakter) values ("Marko", "Markovic", "markan", 0, "sifra123", "Srednji svijet", "Vitez"),
("Petar", "Petrovic", "pero123", 0, "sifra123", "Srednji svijet", "Vitez");

insert into izazov(Naziv, Opis, VrijemeOd, VrijemeDo, BrojBodova) values ("Setnja na posao", "U definisanom vremenskom intervalu, otidjite na posao/fakultet pjeske.", "2023-10-01T10:00:00", "2023-10-07T18:00:00", 5);

insert into izazov(Naziv, Opis, VrijemeOd, VrijemeDo, BrojBodova, IdVrste) values
("Voznja biciklom na posao", "U definisanom vremenskom intervalu, otidjite na posao/fakultet biciklom.", "2023-10-01T10:00:00", "2023-10-07T18:00:00", 5, 2),
("Voznja autobusom na posao", "U definisanom vremenskom intervalu, otidjite na posao/fakultet autobusom.", "2023-10-01T10:00:00", "2023-10-07T18:00:00", 4, 2),
("Voznja trotinetom na posao", "U definisanom vremenskom intervalu, otidjite na posao/fakultet trotinetom.", "2023-10-01T10:00:00", "2023-10-07T18:00:00", 5, 2);