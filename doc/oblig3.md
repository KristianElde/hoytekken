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

    

## Prioriteringer til nå

    

## Prioriterte oppgaver fremover

    

# Produkt og kode

## Utbedring av feil

    Kjente feil vi listet opp under Oblig2:
    - Bug med kontroller bevegelse. Kommer av samtidig input left/right, hvor direction blir satt tilbake til
    static. Fikses med en getter av direction og sjekke hvilken retning spiller beveger seg.
    - Tester vil ikke kjøre. 
    - Game Over skjerm kan bytte tilbake til Main menu, men restarter ikke faktisk spillet i form av å lage nye
    spillere. Fikses ved å legge til funksjonalitet for å restarte spillet.
    - Block fungerer ikke enda, må implementeres med en state for å holde en kontinuerlig block.
    - Hopping må kun tillates når man står på bakken. Nå kan man fly ved å hoppe i luften.

    Hva har vi gjort:
    - Fikset bugen i kontrolleren hvor samtidig input left/right ikke ga ønsket oppførsel. Dette fikset vi slik
    som forklart, ved å sjekke direction med en getter før vi endret tilbake til static.
    - Tester kjører som de skal, funnet en løsning som fungerer med Mock klasser i Mockito og headless application.
    - Byttet fra GameOver skjerm tilbake til Main menu kaller nå på Hoytekken::create, dette lager en ny modell,
    ny kontroller og nye skjermer. Slik blir spillet restartet riktig.
    - Block fungerer nå, implementert et lignende konsept som med ForceDirection, KeyDown endrer block til true
    og KeyUp endre block tilbake til false.
    - Implementert dobbelt hopp slik at spiller kan hoppe max to ganger. Også implementert en Collsion detector som
    håndterer alle kollisioner, spesifikt til hopping så resettes jumpcounter tilbake til 0 når spillerens
    føtter kolliderer med et annet objekt.

    Andre feil som ble oppdaget:
    - cmd linjen for å kjøre prosjektet som en jar fil hadde en skrivefeil, dette er fikset nå.

## README.md

    

## Klassediagram

    

## Kode

     

## kjente feil

    
