# FinPay

## 1. Nom du projet

**Nom du projet :** FinPay – Plateforme de Gestion des Paiements Électroniques

---

# 2. Présentation du projet

FinPay est une application de gestion des paiements électroniques permettant de centraliser les transactions entre les clients, les prestataires de services et la plateforme financière. Elle permet de gérer les clients, les prestataires, les factures, les paiements et le calcul automatique des commissions. Son objectif principal est de fournir une solution sécurisée pour suivre les opérations financières, générer des documents et produire des rapports exploitables.

---

# 3. Problématique

Le problème identifié est que les entreprises utilisant des paiements numériques ont besoin d’un système centralisé pour suivre leurs factures, paiements et commissions sans utiliser plusieurs outils séparés.

La solution proposée permet de gérer l’ensemble du cycle de paiement, depuis la création d’une facture jusqu’à l’enregistrement du paiement, le calcul des commissions et la génération de rapports financiers.

---

# 4. Fonctionnalités principales

- Ajouter, modifier, supprimer et rechercher des clients.
- Ajouter, modifier, supprimer et rechercher des prestataires.
- Créer et gérer les factures.
- Filtrer les factures par statut ou prestataire.
- Enregistrer les paiements électroniques.
- Gérer les paiements partiels.
- Calculer automatiquement les commissions FinPay.
- Mettre à jour automatiquement le statut des factures.
- Générer des factures PDF.
- Générer des reçus de paiement PDF.
- Exporter les factures prestataires en Excel.
- Générer des rapports financiers mensuels.
- Exporter les factures impayées.

---

# 5. Technologies utilisées

| Technologie | Utilisation dans le projet |
|-------------|----------------------------|
| Java | Développement de l’application |
| JDBC | Connexion et communication avec la base de données |
| SQL | Création des requêtes et manipulation des données |
| MySQL / PostgreSQL | Stockage des informations financières |
| Maven | Gestion du projet et des dépendances |
| JUnit 5 | Réalisation des tests unitaires |
| iText | Génération des documents PDF |
| Apache POI | Création des fichiers Excel |
| Git/GitHub | Gestion du code source |

Nous avons utilisé **Java** pour développer la logique métier de l’application.

Nous avons utilisé **JDBC** pour établir la connexion avec la base de données relationnelle et exécuter les requêtes SQL.

Nous avons utilisé **iText** pour générer automatiquement les factures et reçus au format PDF.

Nous avons utilisé **Apache POI** pour créer les exports Excel contenant les rapports financiers.

---

# 6. Installation et lancement

## 6.1 Prérequis

Pour utiliser ce projet, vous devez disposer de :

- Java JDK 17 ou supérieur
- Maven
- MySQL ou PostgreSQL
- Git
- IntelliJ IDEA ou Eclipse
- Un terminal de commande

---

## 6.2 Cloner le dépôt

```bash
git clone https://github.com/VOTRE_COMPTE/finpay.git