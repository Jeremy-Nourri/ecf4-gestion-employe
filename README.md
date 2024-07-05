# ECF4 API Gestion Employés  

## Description

Cette application est une API REST pour la gestion des employés.  
Elle permet de créer, récupérer, mettre à jour et supprimer des informations sur les employés, les départements et les postes au sein d'une entreprise.

## Stack  

Java 11 ou supérieur : Le projet utilise Java 11, comme indiqué dans le fichier pom.xml pour les propriétés maven.compiler.source et maven.compiler.target.  
Maven : Le système de gestion de dépendances utilisé pour construire l'application.  
Serveur de base de données MySQL : Le projet nécessite une base de données MySQL pour stocker les informations. 
Jakarta EE et Hibernate : Le projet utilise Jakarta EE et l'ORM (Object-Relational Mapping) Hibernate.  
Tomcat : Un serveur d'applications compatible avec Jakarta EE pour déployer l'application WAR générée par Maven.

## Installation  


1. Clonez le dépôt :  
   ```
     git clone https://github.com/Jeremy-Nourri/ecf4-gestion-employe.git
   ```
2. Créez une base de données MySQL vide avec le nom `gestionemploye` ou un autre nom de votre choix.  
.
3. Configurez le fichier `src/main/resources/hibernate.properties` pour qu'il corresponde à votre base de données MySQL.  
.
4. Sur IntelliJ IDEA :
- Cliquez sur les ***trois points*** en haut, à droite de l'icône vert (Debug), sous configuration, cliquez sur ***Edit***.  
- Dans la fenêtre qui s'ouvre, cliquez sur le **+** en haut à gauche, puis sur ***Tomcat Server*** et ***Local***.  
- Dans ***Application server***, sélectionnez le dossier d'installation de Tomcat. 
- Dans le champ URL de déploiement, entrez `http://localhost:8080/ecf4_gestion_employes_war_exploded/api/employees`.
- Cliquez sur ***Deployment***  puis sur le **+** en haut à droite, sélectionnez ***Artifact*** et ***ecf4-gestion-employe:war exploded***.  
- Cliquez sur ***Apply*** puis sur ***Ok***.  

5. Cliquez sur l'icône vert **|>** Run pour lancer l'application.  
.
6. Hibernate va créer les tables nécessaires dans la base de données. Si vous souhaitez ajouter des données de test, vous pouvez exécuter le script SQL `src/main/resources/employees.sql` pour ajouter des données dans les tables.

## Utilisation  

L'API est accessible à l'adresse `http://localhost:8080/ecf4-gestion-employe/api/employees`.

L'API expose les points de terminaison suivants :

- **GET**  `/all` :  

   - **Description :** Récupère la liste de tous les employés.  
   - **Réponse :** Un tableau JSON contenant tous les employés avec leurs informations.  

Exemple de réponse :
```
[
	{
		"id": 1,
		"firstName": "John",
		"lastName": "Doe",
		"email": "john.doe@example.com",
		"phone": "1234567890"
	},
	{
		"id": 2,
		"firstName": "Jane",
		"lastName": "Smith",
		"email": "jane.smith@example.com",
		"phone": "0987654321"
	}
]
```
___
- **GET** `/{id}` :  
   - **Description :** Récupère les informations d'un employé spécifique.  
   - **Paramètres :** id (long) - L'identifiant de l'employé.
   - **Réponse :** Un objet JSON contenant les informations de l'employé.

Exemple de réponse :
```
{
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "
}
```
___
- **POST** `/create` :
   - **Description :** Crée un nouvel employé.
   - **Paramètres :** Un objet JSON contenant les informations de l'employé à créer.

Exemple de corps de requête :
```
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phone": "1234567890",
  "department": {
    "name": "ingénierie logiciel"
  },
  "position": {
		"title": "développeur back"
  }
}
```  
 **Réponse :** Un objet JSON contenant les informations de l'employé créé.

Exemple de réponse :
```
{
	"id": 1,
	"firstName": "John",
	"lastName": "Doe",
	"email": "john.doe@example.com",
	"phone": "1234567890"
}
```
___
- **PUT** `/update/{id}` :
   - **Description :** Met à jour les informations d'un employé spécifique.
   - **Paramètres :** id (long) - L'identifiant de l'employé.
   - **Corps de la requête :** Un objet JSON contenant les informations mises à jour de l'employé.
Exemple de corps de requête :

```
{
  "firstName": "Jo",
  "lastName": "jo",
  "email": "john.doe@example.com",
  "phone": "1234567890",
  "department": {
    "name": "finance marketing"
  },
  "position": {
		"title": "développeur"
  }
}
```
- **Réponse :** Un objet JSON contenant les informations de l'employé mis à jour.

Exemple de réponse :
```
{
	"id": 1,
	"firstName": "Jo",
	"lastName": "jo",
	"email": "john.doe@example.com",
	"phone": "1234567890"
}
```
___

- **DELETE** `/delete/{id}` :
   - **Description :** Supprime un employé spécifique.
   - **Paramètres :** id (long) - L'identifiant de l'employé.
   - **Réponse :** Une string contenant un message de confirmation ou d'erreur.
   - **Exemple de réponse :** `Employee with id 1 deleted`

___

- **GET** `/department/{name}` :
    - **Description :** Récupère la liste de tous les employés d'un département spécifique.
    - **Paramètres :** name (String) - Le nom du département.
    - **Réponse :** Un tableau JSON contenant tous les employés du département avec leurs informations.

___

- **GET** `/position/{title}` :
    - **Description :** Récupère la liste de tous les employés d'un poste spécifique.
    - **Paramètres :** title (String) - Le titre du poste.
    - **Réponse :** Un tableau JSON contenant tous les employés du poste avec leurs informations.

___

## Auteur

Jeremy Nourri





