# ORM-innlevering
ORM innlevering for Avansert Java

For å kunne kjøre dette programvaret må du enten ha den nyeste world databasen på plass. Eller du må opprette en tom database og sette opp brukernavn, passord og database navn i properties filen for å teste funksjonaliteten.
 
Forklar hvordan du kan gå fram for å endre en av innleveringene dine til å bruke ORM, og fordeler/ulemper ved å gjøre det.

###ORM i tidligere innleveringer
Gitt at jeg ikke har implemeneter ORM i verken innlevering 1 eller innlevering 2 skal jeg ta for meg fordeler og ulemper ved bruk av ORM og hvordan jeg kunne implementert det i løsningen. 

#####Innlevering 2
Hvis jeg skulle implementer ORM hadde jeg gjort dette på innlevering 2. Grunnen for dette er dynamikken implementert på innlevering 1. Innlevering 1 er kodet veldig dynamisk og har ingen avhengighet til dataen i tabellene. I innlevering 2 er ting noe mer statisk. 

I innlevering 2 er print satt til størrelsen til tabellene. Dette er fordi det ikke var noe kriterie for å ha en 100% dynamisk løsning i denne innleveringen så jeg kan sette avhengighet bak dataen i txt filene. 

For å implementere ORM i innlevering 2 måtte jeg opprettet DTOs for alle tabellene. Etter at jeg hadde satt opp DTOs for innleveringen måtte jeg endret DataUploadAsThread så tråden klarte å lage objekter av elementene i txt filen. 

ORM hadde gjort denne innleveringen noe bedre for da hadde det også være enklere å implementer sist oppgave som var timeplan byggeren hvor klienten kan sende objekter over tråden. 

ORM hadde også gjort innlevering noe trygger fordi det hadde vært strengere rammer rundt hva administratoren kan legge inn i tekst filene. 

####Fordeler og ulemper
Dette er mange fordeler og ulemper bak ORM. Jeg skal kort gå gjennom et par fordeler og ulemper for ORM. 
#####Fordeler
1. En stor fordel med ORM er sikkerhet bak bruker input. Du setter faste rammer for hva som skal inn og kan håndtere feil data på en bedre måte fordi du alt har fastsatt hva som skal være i hver kolonne. 
2. ORM gjør spørringer veldig enkelt. Det er enkelt å gjennomføre søk og print funksjonalitet. 
3. ORM git objekter som resultat av spørringer. Detter gjør det veldig enkelt å bruke lambda for gjennomgang av objektene i resultatet.
4. Enkelere å sende objekter over tread. 

#####Ulemper
1. Veldig låst til rammene satt i DTOen. Dette gjør det vanskelig å implementere en dynamisk løsning. 
2. Vanskelig å kjøre spørringer mot database schema. Some gjøre det være å hente ut informasjon om selve databasen. Hvis du da kobler deg til en eksisterende database kan tabeller som ikke har DTOs ikke bli kjørt spørringer mot uten og implementere både ORM og JDBC. 
