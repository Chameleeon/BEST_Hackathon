# EcoQuest

###

## Šta je EcoQuest?
EcoQuest je aplikacija napravljena sa ciljem podizanja ekološke svijesti stanovnika kroz gemifikaciju i nagrade. Nudi korisnicima njihov vlastiti svijet na kojem direktno mogu da primjete uticaj svojih djela na okolinu. EcoQuest je zasnovana na ideji četiri grčka boga od kojih svaki reprezentuje jednu od oblasti kroz koje svakodnevni čovjek može da utiče na svijet.

<img src="https://github.com/Chameleeon/BEST_Hackathon/blob/main/assets/login.jpg" width="200"> <img src="https://github.com/Chameleeon/BEST_Hackathon/blob/main/assets/registracija.jpg" width="200">

## Kako?
### Osnovna ideja
Svijet u našoj aplikaciji stoji na četiri stuba koje predstavljaju četiri grčka boga. Cilj korsnika je održati stubove u najboljem stanju i sačuvati svoj svijet od raspadanja. Takođe, napredovanjem otključava različite stvari koje može da postavi u svoj svijet i tako ga učini drugačijim od tuđeg. Sam svijet se proceduralno generiše prvi put kada se korisnik prijavi, odnosno kada igrač izađe iz okvira trenutno genersanog svijeta (s tim da je veličina svijeta ograničena). Ispunjavanjem zadataka iz određene kategorije, stub vezan za tu kategoriju se održava. U početku, svaki korisnik kreće sa srušenim stubovima i njegov cilj je da ih izgradi, a zatim održava ispunjavanjem izazova. Ukoliko korisnik ne ispuni nijedan zadatak unutar određenog vremenskog perioda, stub počinje da degradira i potreban je dodatni trud da se stub oporavi. Narušavanje ravnoteže se reflektuje u korisnikovom svjietu tako što se njegov svijet polako pretvara u pustinju, biljke i životinje izumiru. Ravnotežu je moguće povratiti ispunjavanjem određenog broja izazova.

<img src="https://github.com/Chameleeon/BEST_Hackathon/blob/main/assets/izazovi.jpg" width="200"> <img src="https://github.com/Chameleeon/BEST_Hackathon/blob/main/assets/setnja.jpg" width="200"> <img src="https://github.com/Chameleeon/BEST_Hackathon/blob/main/assets/transport.jpg" width="200"> <img src="https://github.com/Chameleeon/BEST_Hackathon/blob/main/assets/svijet.png" width="200">

* Hermes u grčkoj mitologiji predstavlja zaštitnika putnika, a u našoj aplikaciji predstavlja transport. Kroz ispunjavanje zadataka vezanih za transport (npr. putovanje na posao biciklom ili pješke umjesto autom ili autobusom), njegov stub se gradi i održava.
* Hefes u grčkoj mitologiji predstavlja boga vatre. U našoj aplikaciji on je vezan za energiju. Pametnim i ekološki odgovornim odlukama koje doprinose pametnijoj potrošnji energije korisnika moguće je zadovoljiti i ovog boga i održavati njegov stub.
* Had je predstavljao boga podzemlja. Kod nas je on povezan sa otpadom. Odgovornim odlaganjem otpada i dokazivanjem u aplikaciji korisnici mogu održavati treći stub.
* Demetra je predstavljala boginju ratarstva, zemljišta. U našoj aplikaciji ona predstavlja ishranu. Kupovanjem odgovarajućih namirnica, konzumiranjem ekološki odgovorne hrane moguće je održati i ovaj stub.
<img src="https://github.com/Chameleeon/BEST_Hackathon/blob/main/assets/stubovi.jpg" width="200">

### Nagrade
Osim što korisnik može da kreira i održava svoj vlastiti svijet i vidi posljedice svojih akcija na njemu, korisnik skuplja bodove koji se mogu zamijeniti za određene vaučere koji imaju vrijednost u stvarnom životu. Ideja je predstaviti aplikaciju kompanijama kao što su marketi sa "loyalty card" programom gdje bi se bodovima u aplikaciji mogli kupiti dodatni bodovi na kartici. Zauzvrat, marketi bi imali priliku promocije ekološki održivih proizvoda, zdrave hrane i slično kroz izazove u aplikaciji (izazovi koji ne bi bili obavezni ali bi mogli korisnike nagraditi dodatno).

Kroz različite izazove u različitim kategorijama korisnici mogu da skupljaju poene unutar aplikacije. Izvršavanjem izazova korisnici skupljaju bodove i održavaju stubove. Bodove je moguće zamijeniti unutar igrice za kozmetičke nadogradnje karaktera i svijeta. Moguće je graditi svijet kupovinom ekološki odgovornih objekata i stavljanjem njih u svijet. Takođe, određeni broj bodova mjesečno moguće je zamijeniti za nagrade u stvarnom životu.

# Struktura aplikacije
Aplikacija se sastoji iz četiri dijela - klijentske aplikacije, domenskog servera, servera baze podataka i servera na kojem se pokreće igrica.

## Klijentska aplikacija
Klijentska aplikacija je mobilna aplikacija napisana u React Native. Služi za interakciju sa korisnikom, komunicira sa domenskim serverom i prikazuje stanje svijeta igrača koje se čuva na serveru. Zadužena je za prikazivanje izazova korisniku i prikaz stanja stubova.

## Domenski server
Domenski server je realizovan kao Spring Boot aplikacija, zadužen je za održavanje komunikacije između klijentske aplikacije i servera baze podataka. Zadužen je za domensku logiku, obradu korisničkih zahtjeva, protok informacija, ažuriranje stanja stubova, verifikaciju validnosti ispunjenja zadataka i slično.

## Server baze podataka
Server baze podataka je realizovan u MySQL-u i čuva sve relevantne podatke. Komunicira sa domenskim serverom.

## Server igrice
Igrica koja predstavlja svijet korisnika je napravljena upotrebom Unity alata za razvoj igrica upotrebom WebGL-a. Igrica se pokreće na http serveru odakle je klijentska aplikacija dobavlja i prikazuje korisniku.


# Pokretanje
Kako bi se aplikacija ispravno pokrenula, potrebno je pokrenuti sve komponente redoslijedom kojim su navedene u ovom poglavlju.

## Server baze podataka
Potrebno je imati pokrenut MySQL server i zatim izvršiti DDL skripte koje se nalaze u repozitorijumu u [BP](https://github.com/Chameleeon/BEST_Hackathon/tree/main/bp) folderu.

## Domenski server
Za pokretanje domenskog servera potrebno je pozicionirati se u [root folder](https://github.com/Chameleeon/BEST_Hackathon/tree/main/server) servera i pokrenuti sljedeću komandu:
```bash
mvn spring-boot:run
```

## Server igrice
Za pokretanje servera koji pokreće igricu, potrebno je pozicionirati se u [WebGL Builds](https://github.com/Chameleeon/BEST_Hackathon/tree/main/Unity%20Game/WebGL%20Builds) folder unitar Unity Game foldera i zatim pokrenuti komandu:
```bash
python server.py
```

## Klijentska aplikacija
Na kraju potrebno je pokrenuti klijentsku aplikaciju. Potrebno je skinuti [Expo](https://expo.dev/go) na telefon a zatim pokrenuti web server. To je moguće uraditi pozicioniranjem u [root folder](https://github.com/Chameleeon/BEST_Hackathon/tree/main/FrontEnd) klijentske aplikacije i pokretanjem sljedeće komande:
```bash
npm install npx expo start
```





