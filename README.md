# PoissonDiscMapGenerator

Zadaniem programu jest generacja fikcyjnej mapy przy użyciu algorytmów Bridsona oraz Bowyera-Watsona. 

Najpierw generowane są wierzchołki sieci, wygenerowane w pseudo-losowy sposób. Następnie, cała płaszczyzna jest dzielona na trójkąty, które na koniec są kolorowane w taki sposób, aby przypominać mapę.

Użytkownik może wybrać:
 - minimalną odległość między wierzchołkami, 
 - maksymalną liczbę próbek*
 - szybkość symulacji 
 - maksymalną liczbę wierzchołków. 
 Możliwe jest także przełączenie programu w tryb losowej generacji nowych wierzchołków, dla porówniania. 
 
 Po wygenerowaniu mapy, można zapisać plik w formacie PNG.
 
 Czasem, przy małych minimalnych odległościach między wierzchołkami, program nie wykonuje się poprawnie. Jest to najprawdopodobniej kwestia dokładności komparatora, używanego przy wybieraniu kolejnych wierzchołków, na których narysowany będzie trójkąt. Nie udało mi się całkowicie wyeliminować tego problemu, ale możliwie zmniejszyłam prawodopodobieństwo jego wystąpienia.
  
  * algorytm Bridsona generuje kolejne wierzchołki przy użyciu tych, które już istnieją. Maksymalna liczba próbek określa to, ile razy przeprowadzana jest próba znalezienia nowego wierzchołka, który spełnia warunki algorytmu, zanim wierzchołek generujący zostanie uznany za nieaktywny. Po zwiększeniu tego parametru, generowana siatka powinna być gęstsza i bardziej regularna

## Installation

### Requirements:
- javafx
- JDK 8 or later
- Maven

### Run
Clone the repository
```
git clone https://github.com/aksek/PoissonDiscMapGenerator.git
```
Install the application
```
mvn clean javafx:compile
mvn clean javafx:run

```

