# =========================
# 1️⃣ BUILD STAGE
# Cette étape sert à compiler le projet Java avec Maven
# =========================
FROM maven:3.9.11-eclipse-temurin-21 AS build

# Dossier de travail dans le conteneur
WORKDIR /app

# Copier le fichier pom.xml (pour télécharger les dépendances Maven)
COPY pom.xml .

# Copier le code source de l’application
COPY src ./src

# Compiler le projet et générer le fichier JAR
# -DskipTests : accélère le build en ignorant les tests
RUN mvn clean package -DskipTests


# =========================
# 2️⃣ RUNTIME STAGE
# Cette étape sert uniquement à exécuter l’application
# =========================
FROM eclipse-temurin:21-jre-alpine

# Dossier de travail dans le conteneur
WORKDIR /app

# Copier uniquement le JAR généré depuis l’étape build
# Cela permet d’avoir une image finale légère et sécurisée
COPY --from=build /app/target/*.jar ms-settings.jar

# Exposer le port utilisé par l’application Spring Boot
EXPOSE 8080

# Commande exécutée au démarrage du conteneur
ENTRYPOINT ["java", "-jar", "ms-settings.jar"]
