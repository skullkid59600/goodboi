# Appli 'Goodboi'

* Groupe : Chiara Brichot, Jean-François Gautreau, Adrien Lecomte, Thais Révillon
* M1 III, Université Catholique de Lille.

***

Goodboi est une application sous Android destinée à voter pour votre #doggo préféré, tinder-like.

***

Changelog du 09/06/2021

Page de swipe : 
 - Correction de l'affichage de la page swipe pour les petits écrans.
 - Les images se mettent à jour en fonction des 2 chiens en compétition.
 - Les boutons de vote (like) fonctionnent et mettent à jour les scores.
 - Le classement (variable dogs) est trié par ordre de points.

 - On affiche dans les logs l'url d'une image de chien au chargement de l'activité depuis l'API : https://dog.ceo/api/breeds/image/random
 - Ajout de la localisation dans le profil.
 - Le fragment Classement affiche le podium.
 - Possibilitée de se déconnecter depuis la page profil.
 - Correction de l'application en plein écran.

Erreurs encore présentes :
 - Lorsqu'on essaie de faire plusieurs appeles API l'URL aléatoire, la même image est récupéré plusieurs fois.
 - Le classement ne se met pas à jour correctement.
 - Plantage de l'application si la demande d'accès au GPS est déjà validé précédemment (appareil photo aussi dans certains cas).
 
