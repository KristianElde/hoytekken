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

- **Liste over brukerhistorier med tilhørende akseptansekrav**

- (A) Som Utvikler trenger jeg å kunne skille objekter, platformer og bakgrunn på spillbrettet for å kunne bestemme reglene for bevegelsesmønsteret til karakterene når de interagerer med de "fysiske" objektene på skjermen. For å oppfylle dette kravet er det viktig at en skriver kode som følger objektorienterte prinsipper. Det er også viktig at det visuelle på skjermen har tydelige skiller mellom hva som er hva. MVP-krav som inngår i brukerhistorie: 1, 2, 3, 4

- (B) Som spiller må jeg kunne skille mellom de to karakterene på brettet slik at jeg kan vite hvilken spiller jeg kontrollerer. For å oppfylle dette kravet, må karakterene ha forskjellige farger og/eller utsende. MVP-krav som inngår i brukerhistorie: 2, 3, 4

- (C) Som spiller trenger jeg instrukser for hvordan jeg skal bevege spiller og interagere med spillebrettet for å vite hvordan jeg skal spille spillet. For å oppfylle dette kravet må vi implementere visuelle instrukser på skjermen som forklarer hvilke taster man skal bruke for å gjøre ulike handlinger og instruksjoner om hva som er målet med spillet.

- (D) Som utvikler/tester er det viktig at at alle som skriver kode følger grunnleggende prinsipper som gjør det enkelt for forskjellige utviklere å lese og gjøre endringer på koden. For å oppfylle dette kravet må metoder og klasser være dokumentert og det blir brukt beskrivende navn på variabler og metoder, koden må også overholde regler om abstraksjon/innkapsulering og følge model-view-controller strukturen. MVP-krav som inngår i brukerhistorie: Ikke spesifikt til mvp

- (E) Som spiller er det viktig at jeg får visuell bekreftelse når de to spillkarakterene interagerer med hverandre i form av slag/ta skade. For å oppfylle dette kravet trenger vi å implementere en form for animasjon ved vellykkede slag og/eller en form for HUD med en "healthbar" som tilhører hver av de to spillerene. MVP-krav som inngår i brukerhistorie: 5, 7

- (F) Som spiller, ønsker jeg å vite når karakteren min er dø og når spillet starter eller er ferdig. 
For å oppfylle dette kravet er det viktig at når en av spillerne går tom for liv eller faller utenfor plattformen skal det komme en visuell bekreftelse på skjermen som gjør det tydelig at spillet er ferdig og hvem som har vunnet "kampen". Disse bekreftelsene vil kommer i form av "Start"- og "Winner/Gameover"- skjermer. MVP-krav som inngår i brukerhistorie: 6, 8


- **Prioriter liste over brukerhistorier til første iterasjon.**



## Oppgave A4: Kode
 
* Prosjektoppsettet kompilerer og viser krokodillen på skjermen for alle.
* Vi har prøvd litt parprogrammering og programmering hver for oss, for å teste ut rammeverket. 
* Startet med å følge guiden på libdgx-siden (https://libgdx.com/wiki/start/a-simple-game) for å lage et enkelt spill.
* Lagt til andre bilder og lyder for å sjekke hvordan det fungerer.
* Lagt til kontroller ved mus og keyboard.
* Dette er gjort i egne mapper, har dermed ikke endret noen av koden i selve repoet.
