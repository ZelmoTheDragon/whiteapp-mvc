# WhiteApp

Exemple architectural de projet avec les technologies Java EE.

Ce projet est sous licence **CeCILL** (**CE**A **C**NRS **I**NRIA **L**ogiciel **L**ibre),
une licence de logiciel libre compatible avec la **GNU GPL**.

> En savoir plus sur la licence [CeCILL](http://cecill.info/index.fr.html)

## Objectifs

Mettre en oeuvre:

* Une architecture *MVC*
* La bibliothèque graphique *PrimeFaces*
* Payaram Micro
* Arquillian

## Module

Ce projet ce compose d'un seul module de type **WAR** *(Web ARchive)*.
   
## Architecture

L’architecture logicielle mise en place est **MVC** *(Model View Controller)*.

* *Model*
    * Représente les données métiers et les fonctions de persistance.
* *Controller*
    * Représente la logique de traitement et la gestion des exceptions.
* *View*
    * Représente la partie graphique permettant l’interaction humaine avec l'application.

## Environnement

Ce projet est réalisé en **Java 11** *(OpenJDK)*, **JavaEE 8** et **Payara 5.201**.
Il utilise l'outil **Maven** en version 3.6.2.

### Exécution

Récupération du projet:
~~~
    git clone https://github.com/ZelmoTheDragon/whiteapp-mvc.git
    mvn install
~~~

Exécution du projet:
~~~
    mvn payara-micro:start
~~~

OU

~~~
    java -jar target/whiteapp-micro-X.Y.Z.jar
~~~

> Où `X.Y.Z` désigne le numéro de version du projet.

Puis accéder à l'adresse:
~~~
http://localhost:8080/whiteapp
~~~
