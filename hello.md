# Gestion du trafic aérien

On souhaite gérer le trafic aérien au-dessus d'un aéroport et déterminer l'ordre d'atterrissage des avions en
approche.

## Définitions

Un aéroport est défini par :

- Un nom

- Une position exprimée en latitude, longitude et altitude

- Une piste d'atterrissage

Une piste d'atterrissage est défini par :

- Une longueur

- Un temps minimum de dégagement exprimé en secondes (i.e. le temps qu'il faut à un avion pour libérer la
  piste afin qu'un autre avion puisse atterrir)

Un avion est défini par :

- Un numéro de vol

- Une position exprimée en latitude, longitude et altitude

- Une heure d'arrivée estimée (heures, minutes, secondes)

- Un poids en tonnes

- Un niveau de carburant en litres

## Exercice 1: Classes, constructeurs et attributs

- Créer la classe Position en respectant les bornes des valeurs :

- Une altitude ne peut être négative ou supérieure à 40 000 mètres

- Une latitude s'exprime en angle entre -90° (nord) et 90° (sud)

- Une longitude s'exprime en angle entre -180° (ouest) et 180° (est)

- Créer les classes Airport, LandingStrip et Airplane

Bonus (2pt bonus) : le principe de Primitive Obsession est une mauvaise pratique consistant à manipuler
des types primitifs (integers, String, etc) pour représenter des concepts métiers (par exemple, utiliser un
double pour la latitude ou une string pour le numéro d'un vol).

On se tourne alors vers le principe de Primitive Wrapping et de Value Object : on encapsule les primitives
dans une classe afin que la nature de la donnée soit portée par son propre type. On aura donc des classes
comme Distance pour représenter une distance en mètres, km, etc. entre deux points. Altitude pour
représenter une altitude. FlightIdentifier pour le numéro d'un vol, etc.

L'intérêt est que nous pourrons définir uniquement les opérations qui ont un sens (il n'y a pas besoin
d'additionner deux numéros de vol, par exemple) et que nous pourrons éviter les erreurs bêtes mais
courantes (on ne peut pas comparer une longitude et une latitude, par exemple)

## Exercice 2: méthodes

Quand un avion est en phase d'approche (i.e. qu'il est à moins de 50km de l'aéroport), l'aéroport le détecte
et l'ajoute à la file d'attente pour atterrissage.

A tout instant, un aéroport doit être capable de donner le numéro de vol du prochain avion qui peut atterrir

Règle N°1 : Les avions atterrissent dans l'ordre d'arrivée au-dessus de l'aéroport

- Ecrire la méthode distance() de la classe Position qui retourne la distance en mètres entre deux positions

- Ecrire les méthodes de la classe Airport:

- nextLanding() qui retourne le numéro de vol du prochain avion à atterrir

- Le prochain avion à atterrir est celui dont l'heure d'arrivée estimée est la plus proche de l'heure
  courante.

- Si aucun avion n'est dans la file d'attente, elle retourne une valeur nulle

- approach() qui ajoute un avion à la liste d'attente si celui-ci est suffisamment proche (et ne fait rien dans
  le cas contraire)

Imprimé le : 23/07/21 12:01
- Ecrire les tests :

- Vérifier qu'un avion qui survole un aéroport est ajouté à la file d'attente

- Vérifier qu'un avion qui ne survole PAS un aéroport n'est PAS ajouté à la file d'attente

- Vérifier que les avions sont bien

## Exercice 3 : composition

Les règles d'atterrissage vont évoluer dans les prochains exercices. Pour faciliter l'ajout/modifications des
règle, nous allons créer un moteur conçu pour évoluer sans trop d'impact sur le reste du code.

Règle N°2 : L'avion dont l'heure d'arrivée estimée est la plus proche de l'heure courante atterrit en premier.
En cas de conflit entre deux avions, la règle N-1 s'applique.

- Créer une classe LandingRules

- Créer une méthode evaluate() qui prend une liste d'avions et qui retourne le prochain à atterrir

- Modifier la classe Airport pour utiliser LandingRules

- Déplacer la règles N°1 (celle du premier exercice) dans la classe LandingRules

- Ecrire les tests validant le fonctionnement

- Utiliser la classe FakeClock pour maîtriser le temps dans les tests et vous simplifier la vie

- Il faudra sans doute modifier les premiers tests pour qu'ils soient toujours valides

Penser à la classe LocalTime pour gérer le temps

## Exercice 4 : interfaces

La difficulté ici est de pouvoir gérer l'heure courante dans les tests car certains tests risqueraient de ne pas
passer systématiquement ou ne seraient pas clairs pour un relecteur.

Exemple de problèmes liés à l'heure :

Vol n°1, heure d'arrivée 19h30

Vol n°2, heure d'arrivée 22h

Heure courante : 23h59

Résultat : le vol n°2 est le premier à atterrir

Deux minutes plus tard, l'heure courante sera 0h02. Le vol le plus proche n'est plus si évident...

C'est pour cela que nous évitons au maximum de gérer les mécanismes du ssytèmes (heure, accès réseau,
base de données, etc.) directement dans le code : on délègue ces mécanismes à des classes spécifiques
que nous pourrons surchargées.

- Créer une interface Clock possédant une méthode now()

- Créer une classe SystemClock implémentant l'interface Clock et qui retourne l'heure du système

- Créer une classe FakeClock implémentant l'interface Clock et qui retourne une heure fixe (soit tout le
  temps la même, soit la valeur d'un attribut qu'on peut modifier)

- Modifier LandingRules pour manipuler un objet Clock plutôt que l'heure courante directement.

- Ajouter les tests nécessaires

## Exercices 5 : interfaces fonctionnelles

Une classe comme LandingRules doit pouvoir évoluer facilement (i.e. on doit pouvoir ajouter et retirer des
règles facilement). Le truc parfait serait de pouvoir lui passer les règles que l'on souhaite en paramètre de
son constructeur.

- Créer une interface fonctionnelle Rule qui prend en paramètre une liste d'avions et retourne un numéro de
  vol (ou rien)

Imprimé le : 23/07/21 12:01
Objectif du projet (à la fin du projet les étudiants sauront réaliser un...)
On souhaite gérer le trafic aérien au-dessus d'un aéroport et déterminer l'ordre d'atterrissage des avions
en approche.
Détails du projet
- Créer une classe par règle en implémentant l'interface Rule

- Passer les règles n°5 et n°6 en tant que lambda

Règle n°5 : un avion dont l'altitude par rapport à l'aéroport n'est pas entre 800 et 3000 mètres ne peut pas
atterrir. Appliquer les règles N-1 sur les avions ayant la bonne altitude

Reègles n°6 : un avion dont le niveau de carburant est très bas est prioritaire. Le seuil doit être paramétrable
(libre à vous de choisir comment)

Bonus (1pt bonus) Règles N°6 : un avion a besoin d'au moins une piste libre pour atterrir. Quand un avion
atterrit sur la piste, le suivant ne peut atterrir pendant la durée du temps de dégagement de la piste

## Exercice 6 : collections

Cet exercice est à faire en utilisant au maximum l'API Stream pour la gestion des collections

En cas de problème, les avions doivent atterrir sur un aéroport le plus vite possible

- Créer une classe Airports qui gèrent plusieurs aéroports

- Ajouter une méthode emergencyLanding() qui prend en paramètre un avion et retourne le nom d'un
  aéroport sur lequel atterrir

Critères de choix :

- L'avion peut atteindre l'aéroport

- un avion consomme 2 litres de kérosène par 100km par 100kg

- L'aéroport disponible (i.e. pas d'avion en attente)

- L'aéroport dont aucun avion n'a un niveau de carburant très bas

- L'aéroport dont l'altitude est comprise entre 800m et 3000m