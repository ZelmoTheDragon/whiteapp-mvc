# WhiteApp

Exemple architectural de projet avec les technologies Java EE.
Ce projet est sous licence **CeCILL** (**CE**A **C**NRS **I**NRIA **L**ogiciel **L**ibre),
une licence de logicielle libre compatible avec la **GNU GPL**.

> En savoir plus sur la licence [CeCILL](http://cecill.info/index.fr.html)

## Module

Il n'y a qu'un unique module.
   
## Architecture

L'architecture logicielle mis en place est **MVC** *(Model View Controller)*.

## Environnement

Ce projet est réalisé en **Java 11** *(OpenJDK)*. et **JavaEE 8**.
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