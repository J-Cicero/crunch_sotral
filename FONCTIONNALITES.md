# Fonctionnalités métier (hors CRUD basique)

## Calcul temps réel & prédictions
- **Capteurs GPS** : ingestion des positions véhicules via `/api/capteurs/position` (trackingId véhicule, lat/long, vitesse, horodatage). Les dernières positions par bus/ligne/arrêt sont exposées via les endpoints `/api/capteurs/dernieres`, `/dernieres/ligne/{trackingId}`, `/dernieres/arret/{trackingId}`.
- **Missions actives uniquement** : les prédictions et positions exploitées ne considèrent que les bus ayant une mission `ACTIVE` (filtrage dans `PredictionServiceImpl` et `CapteurServiceImpl`).
- **Prédiction d’arrivée** : calcul du temps restant par arrêt avec vitesse moyenne glissante (5 derniers points) et buffer de sécurité. Création/mise à jour de `Prediction` liée au bus, ligne et arrêt via trackingId.
- **Nettoyage périodique** : le scheduler `PredictionScheduler` rafraîchit les prédictions toutes les 30s et supprime celles dont le bus n’a plus de mission active.

## Gestion des états de course/mission
- **Missions** : endpoints `/api/missions` avec trackingId (busVehicule/conducteur) pour créer, lister, fermer (statut). Les prédictions ne sont déclenchées que pour les missions `ACTIVE`.
- **Courses** : endpoints `/api/courses` avec trackingId mission; statuts disponibles dans `StatutCourse`. À utiliser pour marquer une course `EN_COURS` ou `TERMINEE`; les prédictions ne s’appuient pas sur les courses terminées (filtrage indirect via missions actives).

## Sécurité & JWT
- Authentification JWT (HS256) via `JwtService`, filtres `JwtAuthorizationToken`. Swagger documente les endpoints publics/protégés.
- Rôles supportés : `ADMIN`, `USAGER`, `CONDUCTEUR` (héritage `User` SINGLE_TABLE).

## Accès données par trackingId
- Toutes les ressources exposent leurs `id` internes en lecture, mais les opérations d’écriture/requêtes utilisent les `trackingId` (UUID) : lignes, bus, véhicules, arrêts, missions, courses, prédictions, capteurs, associations bus-véhicule, conducteurs.

## Swagger / OpenAPI
- Swagger UI : `http://localhost:8080/api/swagger-ui/index.html` (profil `local` recommandé, H2 en mémoire).
