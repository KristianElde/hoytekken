# Rapport – innlevering 4

**Team:** _Karan med varan_ – _Sune_ – _Mathias_ – _Kristian_ – _Vetle_ – _Jonas_ – _Lauritz_

# Rapport – innlevering 3

**Team:** _Karan med varan_ – _Sune_ – _Mathias_ – _Kristian_ – _Vetle_ – _Jonas_ – _Lauritz_

# Prosjektrapport

## 1. Roller

### 1.1. Prosjektleder


### 1.2. Møteleder



### 1.3. Trello-ansvarlig

  

### 1.4. Testansvarlig



### 1.5 Lyd og grafikk

  

### 1.6 Strukturansvarlig



### 1.7 Kodestilansvarlig



## 2. Prosjektmetodikk

   

## 3. Gruppedynamikk

    

## 4. Kommunikasjon

  

## 5. Retrospektiv

    

## 6. Arbeidsfordeling


## 7. Forbedringspunkter fra retrospektiv



# Krav og spesifikasjon

## Progresjon i forhold til MVP

    Vi har nå dekket alle kravene i MVP'en. Vi har også kommet et stykke forbi dette, og arbeidet går nå i hovedsak ut på å 
    forbedre features som allerede er implementert i sin enkleste form.

    Ettersom at vi har kommet forbi MVP stadiet er det ikke lenger aktuelt å endre på prioriteringsrekkefølgen for 
    MVP-kravene. Vi har heller ikke sett behovet for å legge til noen krav.


## Prioriteringer til nå

    Siden forrige innlevering har en av de høyest prioriterte oppgavene vært å forbedre test-coverage. Vi er veldig fornøyde 
    med å kunne si at vi har økt test-coverage fra 0% til nesten 70% siden forrige innlevering!

    Vi har også prioritert animasjoner høyt siden forrige innlevering. Vi har nå animasjoner for spark, slag og blokk. Disse 
    animasjonene fungerer nå som de skal, og de forbedrer helhetsopplevelsen av spillet betraktelig.

    En tredje ting som har vært viktig siden sist er implementering av power ups. Dette fungerer nå på et basic nivå.

    Vi har også lagt inn lyder som samsvarer med angrepshandlingene til spillerne.
   

## Prioriterte oppgaver fremover


# Produkt og kode

## Utbedring av feil

    Kjente feil vi listet opp under Oblig3:
    - Spillere kan klistre seg fast på siden av objekter hvis de har beveger seg mot objektet. (Usikkert om dette er
    noe vi ønsker å beholde som en feature, eller om vi ønsker endre dette slik at spilleren faller)
    - Powerups kan spawne inni platformer og ellers "unreachable" områder
    - Powerups kan gi mer en max health, mer enn max liv osv
    - Man kan slå/sparke motspiller uansett hvilken vei man ser.
    - AI faller lett av platform
    - Per nå ingen måte å endre fra 2 spiller modus til 1 spiller mot ai (Vil implementere en ekstra skjerm
    for å velge antall spillere og ønsket map)
    - Noen deler av koden i modellen kan flyttes inn i de aktuelle klassene de håndterer, controllen er også inkonsisten
    på enkelte tilfeller i forhold til kommunikasjon med modell eller direkte til spiller.

    Hva har vi gjort:
    - Spillere kan fortsatt klistre seg til vegger (beholdt som en feature), men vi har gjort endring slik at spiller ikke får jumpcounter blir resatt når dette skjer. 
    - PowerUps spawner ikke lenger inni platformer. Vi sjekker betingelser for å se om senter av powerupens body er inni et platform objekt når vi velger en random posisjon på brettet. Hvis det er en kollisjon her så blir posisjoneringsmetoden kalt på nytt.
    - (Fikse at powerups ikke overskrider max verdier)!!!!!!
    - Forbedret AI, satt ned hastigheten for å gjøre den mindre "intens", har også lagt til flere valg for random behavior for å gjøre AI litt mer intelligent.
    - Ryddet i modellen og generelt i kodebasen, delt opp kompleks kode i flere hjelpemetoder og ryddet i noen konstruktører. Har også fjernet kode var overflødig.


    Andre feil som ble oppdaget:
    - 
    

## README.md

    Readme.md er opp til dato for spesifkasjoner for å kjøre programmet.
    Programmet kompilerer og kjører på både mac os og Windows, ikke hatt mulighet
    til å teste dette på Linux, men pakke/fil struktur er sjekket for caseing mtp små/store bokstaver så spillet
    skal kompilere på Linux også.

## Klassediagram

    Klassediagram ligger her: [UML](/doc/hoytekken_uml.png)
    Vi har valgt å ta ut alle enums, for å gjøre det mer oversiktlig.

## Kode

    Prosjektet følger MVC (model-view-controller) struktur. I tillegg har prosjektet en Eventbus som gjør det enkelt å kommunisere Events mellom "fjerne" klasser. Eventbusen ble veldig viktig for å håndtere museklikk i viewet samtidig som vi ønsket å beholde en streng MVC struktur hvor kontrolleren håndterer alt av input.

    - Controller klasse håndterer alt av input.
        - Kommuniserer med modellen for og utfører handlinger på spillet basert på hvilken input som ble registrert

    - View er en består av en abstrakt superklasse, BaseScreen som extender libgdx sin Screen klasse, og subklasser.
    Hver av subklassene representerer en Screen med en tilhørende GameState.
        - Screen klassene tegner alt visuelt.
        - GameLoopen kommer fra render metoden, her kaller den på update i model som oppdaterer resten av mekanikken i spillet.


    - Modellen holder på alt av mekanikk og logikk i spillet. Denne kobles opp mot controller og Screeens
    med Viewable- og ControllableModelInterface som dikterer hvilke deler av prosjektet som skal ha tilgang til hvilke deler 
    av modellen.
        - Selve model klassen knytter alle components sammen.
            - Sprites -- Player, PowerUps
            - Box2d -- World generator
            - ContactListener/Collision detector som lager events hver gang objekter i Box2d physics world kolliderer
            - Eventbus

    - Hoytekken Game/ApplicationListener. Denne klassen knytter alle delene av prosjektet sammen og kjøres i main.
        - create() oppretter
            - spritebatchen for spillet
            - modellen
            - controller
            - View/Screen
            - EventBus

    - Testing. Vi har jobbet mye med testing siden forrige oblig og fått opp testcoverage. Vi fant en løsning for å få testet store deler av viewet, og har lagt til flere tester for edge cases på andre områder.
    som ikke er dekket av testene.
    - Alt av kode, klasser, interface, metoder, osv... er dokumentert med Javadoc.

## kjente feil

   
    
