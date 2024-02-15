# Rapport – innlevering 1

## Oppgave A0: Organisering

- Gruppenavn er laget: Karan med varan
- Discord-server er laget.
- Ifht. bakgrunn går alle datateknologi og har tatt mye av de samme fagene. Av de som er relevant er det INF100, INF101, INF102, INF122, INF113. Jonas har jobbet som utvikler, dog ikke innen spill, men stiller med erfaring fra prosjektmetodikk og utvikling. Kristian har verv innen webkom som gir han erfaring med git og teamarbeid.

**Team-medlemmer:**

- Kristian Elde Johansen
- Lauritz Angeltveit
- Mathias Hop Ness
- Vetle Larsen
- Jonas Bergaas
- Sune Eriksson Lianes

## Oppgave A2: Konsept

- Kort og enkelt er konseptet et "fighting" spill der to spillere kjemper mot hverandre til en av spillerne dør.

- Figurer og kontroller

  - To karakterer der den ene av dem er styrt av den som spiller, mens den andre er en AI.
  - Kontroller for å styre karakteren til høyre/venstre og hopping.

- Todimensjonal verden

  - Verden skal bestå av plattform(er) der hensikten er at man kan gå, stå og hoppe på disse.
  - Spillerne beveger seg på de synlige plattformene. Går man utenfor disse vil man falle ned.
  - En eller annen bakgrunn

- "Fighting"

  - Karakterene kjemper mot hverandre.
  - Det finnes HP (healthpoints) for hver av spillerne. Når en spiller går tom for HP, taper den spilleren "fighten" og en vinner kåres.

- Interaksjoner

  - Bevege seg.
  - Slå og skade spiller ved slag.

- Utfordringer

  - Bevege seg på plattformen(e) uten å dette ut.
  - Bekjempe spilleren man kjemper mot.

- Eventuelle forbedringer
  - Diverse hindringer? Vegger man må hoppe over på plattformene? Flyvende objekter man må holde seg unna for å ikke miste liv?
  - Spillerne kan samle opp "power-ups" som kan gi dobbel løpehastighet, dobbelthopp, midlertidig udødelig.
  - Plattformer som beveger seg ila. kampen.
  - Ulike levler som ser noe annerledes ut, plattformene og hindringene kan se annerledes ut.
  - 2 player, altså ingen AI.
  - Forskjellige interaksjoner (spark...).

## Oppgave A3: Prosesser for teamet

- **Møter og hyppighet av dem**

  - Møter blir arrangert hver torsdag fra klokken 14:15 - 16:00 i booket grupperom (209M1) på høytek.

- **Kommunikasjon mellom møter**

  - Vil hovedsaklig foregå på discord serveren.

- **Arbeidsfordeling (Endring kan forekommer)**

  - Alle er utviklere

  - Sune: Møte ansvarlig
  - Jonas: Trello ansvarlig
  - Lauritz: Lyd, grafikk ansvarlig
  - Kristian: Test ansvarlig
  - Vetle: Struktur ansvarlig
  - Mathias: Kodestil ansvarlig

- **Oppfølging av arbeid**

  - Trello brukes aktivt for å logge, systematisere og kontrollere arbeid.

- **Regler for git**
  - Om man skal redigere eller legge til features, så skal man opprette en egen branch kun for den ene featuren. Branchen må ha et beskrivende navn på det du skal utføre.
  - Når man commiter, så skal man ha en god beskrivelse på hva du har gjort.
  - Når du skal merge, så må en annen reviewe merge requesten din, altså ikke godkjenn requesten selv.
  - Etter merge er gjennomført, så skal branchen fjernes.

## Oppgave A3: Oversikt over MVP

- **kort beskrivelse av det overordnede målet for applikasjonen**
  - Målet for MVP er å lage et funksjonelt spill med grunnleggende features. Spillet på dette stadiget må ha oversiktlig struktur og god dokumentasjon slik at det blir enkelt å legge til forbedringer og videreutvikle spillet.
- **Krav til Minimum Viable Product (MVP)**

  1. Vise enkelt spillebrett
  2. Vise spiller og motspiller
  3. flytte spiller
  4. hoppe, enkel fysikk/gravity, interagere med terreng
  5. spiller kan slå
  6. spiller kan dø, falle av plattform på brettet
  7. Spiller kan ta skade
  8. Start-skjerm/"winner" skjerm, enkel instruks skjerm

- **Prioritert liste over brukerhistorier med tilhørende akseptansekrav**

1. Som Utvikler trenger jeg å kunne skille objekter på brettet for å kunne styre spillerens bevegelsesoppførsel.

2. Som spiller må jeg kunne skille mellom de to spillerene på brettet slik at jeg kan vite hvilken spiller jeg kontrollerer.
3. Som spiller trenger jeg gode instrukser for å vite hvordan jeg skal spille spillet

4. Som tester/utvikler er det viktig at koden er oversiktlig og godt dokumentert slik at jeg kan legge til/endre og teste koden

- **For hver brukerhistorie, skal dere ha akseptansekriterier og arbeidsoppgaver, samt beskrivelse av hvilke krav brukerhistorien oppfyller (dette lager dere kun for historier dere er ferdige med, holder på med, eller skal til å begynne med)**

- **En prioritert liste over hvilke brukerhistorier dere vil ha med i første iterasjon (altså frem til levering av denne oppgaven).**


## A4:
 
* Prosjektoppsettet kompilerer og viser ting på skjermen for alle.
* Vi har prøvd litt parprogrammering og programmering hver for oss, for å teste ut rammeverket. Blant annet fulgt guiden på libdgx-siden, prøve med andre lyder og bilder, og legge til kontroller.
* Dette er gjort i egne mapper, har dermed ikke endret noen av koden i selve repoet.