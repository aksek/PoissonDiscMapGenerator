# PoissonDiscMapGenerator

Zadaniem programu jest generacja fikcyjnej mapy przy użyciu algorytmów Bridsona oraz Bowyera-Watsona. Najpierw generowane są wierzchołki sieci, wygenerowane w pseudo-losowy sposób. Następnie, cała płaszczyzna jest dzielona na trójkąty, które na koniec są kolorowane w taki sposób, aby przypominać mapę.
Użytkownik może wybrać minimalną odległość między wierzchołkami, maksymalną liczbę próbek (algorytm Bridsona generuje kolejne wierzchołki przy użyciu tych, które już istnieją. Maksymalna liczba próbek określa to, ile razy przeprowadzana jest próba znalezienia nowego wierzchołka, który spełnia warunki algorytmu, zanim wierzchołek generujący zostanie uznany za nieaktywny), szybkość symulacji oraz maksymalną liczbę wierzchołków. Możliwe jest także przełączenie programu w tryb losowej generacji nowych wierzchołków, dla porówniania. 

## Installation

### Requirements:
- javafx
- JDK 8 or later
- Maven

### Run
Clone the repository
```
git clone https://github.com/aksek/MapGenerator.git
```
Install the application
```
mvn clean javafx:compile
mvn clean javafx:run

```
