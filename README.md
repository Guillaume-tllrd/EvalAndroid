# 📱 Application Pokémon 

Application Android native développée en **Kotlin** et **Jetpack Compose**, permettant de consulter une liste de Pokémons et leurs statistiques détaillées via une API REST.

## 🏗 Architecture & Conception

Ce projet respecte les principes de la **Clean Architecture** combinée au pattern **MVVM (Model-View-ViewModel)**. L'objectif est de garantir la séparation des responsabilités, la testabilité et la maintenance du code.


### Détail des couches (Layers)

1.  **Couche Data (`com.example.evalandroid.data`)**
    * **Responsabilité :** Gestion des données brutes et accès réseau.
    * **Composants :**
        * `remote` : Configuration du client HTTP (Ktor) et sérialisation JSON.
        * `dto` : Objets de transfert (Data Transfer Objects) reflétant la structure exacte de l'API.
        * `repository` : Implémentation de l'interface du domaine. Transforme les DTOs en objets métiers via des Mappers.

2.  **Couche Domain (`com.example.evalandroid.domain`)**
    * **Responsabilité :** Cœur du métier, indépendant de toute librairie ou framework (Android/Ktor).
    * **Composants :**
        * `Model` (Pokemon, Stats) : Classes de données "propres" utilisées par l'UI.
        * `Repository Interface` : Contrat définissant les opérations disponibles, permettant l'inversion de dépendance.

3.  **Couche UI (`com.example.evalandroid.ui`)**
    * **Responsabilité :** Affichage et interaction utilisateur.

4.  **Injection de Dépendances (`com.example.evalandroid.di`)**
    * Utilisation de **Koin** pour gérer l'instanciation des objets et les singletons (Repository, HttpClient, Managers).

---

## 📂 Structure du projet

Voici l'organisation des paquets (packages) pour faciliter la navigation :

```text
com.example.evalandroid
├── data                # Accès aux données
│   ├── dto             # Modèles JSON (ApiTypeDto, PokemonDto...)
│   ├── mapper          # Convertisseurs DTO -> Domain
│   ├── remote          # Configuration Ktor
│   └── repository      # Implémentation logique (PokemonRepositoryImpl)
│
├── di                  # Modules Koin (AppModule, DataModule)
│
├── domain              # Règles métier 
│   └── pokemon         # Entités (Pokemon) et Interfaces (Repository)
│
├── system              # Services système (SoundManager)
│
└── ui                  # Interface Utilisateur
    ├── navigation      # Gestion des routes (Destinations)
    ├── features         # Écrans (Home, Details) et ViewModels
    └── theme           # Thème graphique Compose
    C'est la touche finale indispensable. Un bon README est ce que le correcteur regarde souvent en premier.

Pour valider la compétence "Présenter la structure du projet de manière efficace", il ne suffit pas de lister les dossiers. Il faut expliquer le flux de données (Data Flow) et les choix technologiques (Clean Architecture, MVVM).

Voici un modèle complet, professionnel et prêt à copier-coller (en format Markdown) pour ton fichier README.md.

Copie ce contenu dans ton fichier README.md à la racine du projet :
Markdown

# 📱 Application Pokémon (EvalAndroid)

Application Android native développée en **Kotlin** et **Jetpack Compose**, permettant de consulter une liste de Pokémons et leurs statistiques détaillées via une API REST.

## 🏗 Architecture & Conception

Ce projet respecte les principes de la **Clean Architecture** combinée au pattern **MVVM (Model-View-ViewModel)**. L'objectif est de garantir la séparation des responsabilités, la testabilité et la maintenance du code.

### Schéma global du flux de données
Le projet suit un flux de données unidirectionnel (UDF) :

`API (Ktor)` ➡️ `Data (Repository)` ➡️ `Domain (Entités)` ➡️ `UI (ViewModel -> State -> Compose)`

### Détail des couches (Layers)

1.  **Couche Data (`com.example.evalandroid.data`)**
    * **Responsabilité :** Gestion des données brutes et accès réseau.
    * **Composants :**
        * `remote` : Configuration du client HTTP (Ktor) et sérialisation JSON.
        * `dto` : Objets de transfert (Data Transfer Objects) reflétant la structure exacte de l'API.
        * `repository` : Implémentation de l'interface du domaine. Transforme les DTOs en objets métiers via des Mappers.

2.  **Couche Domain (`com.example.evalandroid.domain`)**
    * **Responsabilité :** Cœur du métier, indépendant de toute librairie ou framework (Android/Ktor).
    * **Composants :**
        * `Model` (Pokemon, Stats) : Classes de données "propres" utilisées par l'UI.
        * `Repository Interface` : Contrat définissant les opérations disponibles, permettant l'inversion de dépendance.

3.  **Couche UI (`com.example.evalandroid.ui`)**
    * **Responsabilité :** Affichage et interaction utilisateur.
    * **Pattern :** Utilisation de **Jetpack Compose** avec gestion d'état réactive (`StateFlow`).
    * **Cycle de vie :** Optimisation via `collectAsStateWithLifecycle` pour économiser les ressources en arrière-plan.

4.  **Injection de Dépendances (`com.example.evalandroid.di`)**
    * Utilisation de **Koin** pour gérer l'instanciation des objets et les singletons (Repository, HttpClient, Managers).

---

## 📂 Structure du projet

Voici l'organisation des paquets (packages) pour faciliter la navigation :

```text
com.example.evalandroid
├── data                # Accès aux données
│   ├── dto             # Modèles JSON (ApiTypeDto, PokemonDto...)
│   ├── mapper          # Convertisseurs DTO -> Domain
│   ├── remote          # Configuration Ktor
│   └── repository      # Implémentation logique (PokemonRepositoryImpl)
│
├── di                  # Modules Koin (AppModule, DataModule)
│
├── domain              # Règles métier (Agnostique)
│   └── pokemon         # Entités (Pokemon) et Interfaces (Repository)
│
├── system              # Services système (SoundManager)
│
└── ui                  # Interface Utilisateur
    ├── navigation      # Gestion des routes (Destinations)
    ├── screens         # Écrans (Home, Details) et ViewModels
    └── theme           # Thème graphique Compose

