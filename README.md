# Backend CIC-Challenge Lösung von Ulrich Meisl 

 
## Aufgabenstellung 

Es soll ein REST-Service implementiert werden. 

Themen; 

* Drehorte in San Francisco 
* CO2 Emmisionen von öffentlichen Gebäuden in San Francisco 

Entspechen der Themenwahl sollen die Daten gelesen werden und gegebenenfalls gefiltert werden. 

[Link zur Aufgabenstellung](http://cic-challenge.eu-gb.mybluemix.net/challenge.html>CIC-Challenge) 


## Umsetzung 

Gewähltes Thema __CO2 Emissionen von öffentlichen Gebäuden in San Francisco__ 

### Einlesen der Daten 

Die Funktion zum Einlesen ```java static ArrayList<JSONObject> readJsonFromUrl(String url) throws IOException, JSONException ``` 
ist nur auf diese Challenge zugeschnitten. 
Sie liest die Daten über einen Inputstream ein und entfernt alle Einträge bei denen der CO2 Gehalt größer als 0 ist. 
Als Rückgabeparameter dient eine ArrayList in welcher alle JSONObjects stehen. 


### Filtern der Daten 

Da die Einlese Funktion für den Filter Probleme machte und die maximale Zeit sonst überschritten worden wäre, wurde nur ein sehr Rudimentärer Filter implementiert. 

Zum Verändern des Filters ist leider eine Änderung des Source Codes nötig. 

```java filterKey``` 
Je nachdem welcher Schlüssel gewählt wird entweder nach department "d", source_type "s" oder gar nicht (alles andere) gefiltert. 

  

```java filterValue``` 
Wenn als filterKey "s" oder "d" gewählt wurde, werden nur jene Objekte welche diesen wert beim gewählten Key haben in die Endgültige Liste übernommen. 
Sollte der es kein Objekt mit entsprechenden Wert geben so bleibt die Liste lehr. 
Alle gültigen filterValue stehen im Source-code, 


### Ausgabe der Daten 

Da der Link zum Einspielergebnis nicht zum Öffnen war, wurde nur eine Ausgabe über System.out implementiert. Diese erfolgt Zeilenweise für die Objekte in 
der String Repräsentation eines JSONObject. 

  
## Zeitbedarf 

  
| Aufgabe                      | Zeit    | 
| ---------------------------- | -------:| 
| Einlesen in JSON + JSON daten einlesen            |   1,5 h   | 
| Programmieren der Aufgabe |   3 h | 
| Dokumentation                |   0,5 h | 

--- 

## Verbesserungsmöglichkeiten 


* Die Einlese Funktion könnte so aufgeteilt werden, sodass sie Allgemein Daten einliest, welche dem Grundformat entspricht. 
* Der Filter soll mit dem Programmaufruf zum Einstellen sein. 
* Abfangen ungültiger Filter Werte 
* Die Ausgabe auf das geforderte Format bringen 
* Dokumentation: Stil und Umfang 

--- 
 

## Wie ging es mir bei der Challenge 

Am Anfang fühlte ich mich etwas Überfordert, da ich noch nie etwas mit JSON gemacht habe. 
Nach ein paar YouTube-Videos und einer Nacht darüber schlafen habe ich doch noch eine Lösung gefunden. 
Am meisten ärgert mich, dass der Filter nicht über den Programm Aufruf/die Console einstellbar ist, da wollte mein Code einfach nicht das was ich wollte. 
Auch glaube ich das ich das REST Prinzip nicht ganz verschtanden/umgesetzt habe. 

Ich fand im Großen und Ganzen den Umfang der Challenge sehr fair und schaffbar. 

---  

## Quellen 

JSON Lib 
<https://github.com/stleary/JSON-java> 

Und die Üblichen Verdächtigen YouTube-Tutorials, Java-API und Stackoverflow. 

Es wurde eigener Code geschrieben, die Angegebenen Quellen dienten nur als Anhaltspunkt und zur Auffrischung von Vergessenen Wissen. 

 