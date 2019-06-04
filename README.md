# daddit
A light-weight Reddit-type web application.

![Daddit](https://github.com/kristiangrundstrom/redditmini/blob/master/src/main/resources/public/images/Dattit-logo-new.png)

**Xin Gao, William Jelvin, Marcus D Jensen, Kristian Grundström**

Daddit är en Dad Joke-applikation där man som användare kan läsa och skriva egna DadJokes. 
Applikationen använder sig av RESTful services för att läsa och skriva data till en H2-databas.

De funktioner man hittar i applikationen är
- Man kan registrera sig som en ny användare med valfritt användarnamn och
lösenord
- En registrerad användare kan skapa egna inlägg.
- Ett inlägg kan innehålla flera olika kategorier.
- En användare kan lägga en röst på ett inlägg som denne finner underhållande (+1)
eller om skämtet är av undermålig kvalitet lägga en negativ röst (-1). Endast en röst
per användare och inlägg. Man kan ändra sin röst åt båda hållen om man exempelvis
röstat fel.
- Alla användare kan lista de hetaste tio inläggen som är baserat på antal röster, alla
inlägg eller endast inlägg från respektive användare i databasen.
- Det finns en sökfunktion där man som användare kan söka efter nyckelord som man
finner i rubrik eller innehåll.
- Det finns en administratör som även befogenheter att radera inlägg.
Fördefinierade användare är ​daddy1 ​, ​daddy2 ​och ​daddy3 ​som alla har lösenordet “​daddy​”
samt ​BigDaddy ​som är administratören med lösenordet “​chef ​”.
