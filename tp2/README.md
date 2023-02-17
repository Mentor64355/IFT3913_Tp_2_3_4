# Ift3913_Tp2

etudiant: Maya Moussaoui matricule: 20157653 etudiant: Tassadit Bibi matricule: 20221732

lien vers le dossier github: https://github.com/Tassa06/Ift3913_Tp2.git

Pour la Question 1:

Pour la classe Metric1Q1 qui calcule la densite de commentaire de chaque classe, la commande a rouler est:
"py Metric1Q1.py path_du_dossier_a_analyser"
qui donnera en sortie un fichier csv nomme metric1Q1.csv contenant le nom de la classe et sa DC

Pour la classe Metric2Q1 qui calcule le nombre de classes documentées/nombre de classes totales.
Nous avons choisis de compter les classes documente comme etant toutes les classes dont la densite de commentaire > 1/5 (ce chiffre est arbitraire, on a juste pensé que si 1/5 du fichier sont des explications, ca devait etre suffisant pour etre concideré comme bien documenté)
la commande a rouler est:
"py Metric2Q1.py path_du_dossier_a_analyser"
se qui printera dans la ligne de commande la reponse

Pour la question 2:

La premiere métrique de la question 2 est LCOM, qui est calcule par le fichier cohesion.jar
Pour rouler le jar, aller dans le terminal,
deplacez vous a l'endroit vous avez placé le fichier jar
tapez la commande
"java -jar cohesion.jar ABSOLUTE_path_du_dossier_a_analyser"
se qui donera en sortie un fichier csv contenant le nom des classes java et leur LCOM

Pour la deuxieme métrique, nous avons decide d'utiliser la classe Egon implemente dans le tp1
(nous avont remis toutes les classes necessaire a faire rouler Egon ici aussi donc pas besoin de chercher dans tp1 si vous n'avez pas le lien)
voici les instruction:
rouler la fonction javac pour toutes les classes

    javac Jls.java
    javac Nvloc.java
    javac Icsec.java
    javac Egon.java

ensuite:
UTILISATION : \*\*\* toutes les operations suivantes doivent etre effectuees sur la console

appelez en premier Jls afin d'avoir le fichier csv (jls.csv) en sortie qui va etre pris comme argument pour Icsec
Entre : Jls prend comme argument le chemin d'acces du dossier qui contient le code Java
Sortie : jls.csv

appelez ensuite Icsec afin d'avoir le fichier csv (Iscec.csv) en sorte qui va etre pris comme troisieme argument pour Egon
Entre : Icsec prend comme arguments le chemin d'acces d'un dossier et le fichier jls.csv
Sortie : Icsec.csv

appelez maintenant Egon afin d'avoir le fichier csv (egon.csv) en sortie qui contient les classes divines
Entre : arg[0]: chemin d'acces d'un dossier qui continet le code java - arg[1]: le seuil en % - arg[2]: fichier de sortie de Icsec (Icsec.csv)
Sortie : egon.csv dans le cas ou on trouve des classes divines sinon un message," aucune classe suspectee divine ", va s'afficher. 3.1. essayez avec les seuils suivant : 1%,5%,10%

Pour la question 3 : 

la premiere metrique correspond au nombre d'erreurs totales detectees, celle-ci est calculee a partir du fichier cyc.py. 
pour rouler le fichier "cyc.py" utilisez les instructions suivantes dans la console  :
- cd path_dossier_qui_contient_cyc.py
- python cyc.py path_dossier_jfreechart_src_main
un rapport html et xml vont etres generes, toute fois, le nombre d'erreur va etre indique dans le terminal ("voir l'image "cycTotal") 

la deuxieme metrique se rattache a la couverture de tests,
NOTE IMPORTANTE : ******* SVP utiliser jfreechart-master join dans notre dossier etant donne qu'on a ajoute une dependance dans pom.xml *******
- cd path_dossier_qui_contient_fichier_pom.xml_dans_jfreechart-master 
- mvn clean install
un rapport html sera genere dans le dossier target dans jfreechart-master. le pourcentage de couverture va etre calcule manuellement dans le rapport

Pour la question 4 :

la premiere metrique correspond a la complexite cyclomatique calcule a partir du fichier cyc.py. Le rapport est deja genere, car on a deja execute cyc.py a la question 3

la deuxieme metrique correspond au pourcentage de couverture de method non teste, aussi trouve a la question 3. 
a ca on rajoute le nombre de classes non testees, qu'on retrouve aussi dans le rapport produit par mvn index.html qu'on retrouve dans target
les fichiers de sortie pour Q3 et Q4 sont les memes et sont situe A:
- la premiere metrique:  https://github.com/Tassa06/Ift3913_Tp2/blob/fab42a47a63e47a06d1ab9b0fb588bb3bac52156/CC_html
- deuxieme metrique : https://github.com/Tassa06/Ift3913_Tp2/blob/fab42a47a63e47a06d1ab9b0fb588bb3bac52156/jfreechart-master/jfreechart-master/target/site/jacoco/index.html
