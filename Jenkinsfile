pipeline {
    agent any
    tools {
        maven 'maven' // Utilise l'installation Maven configurée dans Jenkins
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Prepare Maven Wrapper') {
            steps {
                sh 'chmod +x ./mvnw'
                sh './mvnw --version'
            }
        }
        
        // ✅ Étape ajoutée : Compilation du projet
        stage('Compile') {
            steps {
                sh './mvnw clean compile'
            }
        }
        
        stage('Scan SonarQube') {
            steps {
                withSonarQubeEnv('sq1') { // Remplacez 'sq1' par le nom de votre serveur SonarQube dans Jenkins
                    sh './mvnw sonar:sonar'
                }
            }
        }
        
        // stage('Quality Gate') { ... } // Optionnel
    }
}
