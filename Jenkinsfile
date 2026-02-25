pipeline {
    agent any

    stages {
        stage('Prepare Maven Wrapper') {
            steps {
                sh 'chmod +x ./mvnw'  // ← rend mvnw exécutable
                sh './mvnw --version' // ← test rapide pour confirmer
            }
        }

        stage('Scan') {
            steps {
                withSonarQubeEnv('sq1') {
                    sh './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:sonar'
                    // ou la version plus simple : sh './mvnw clean sonar:sonar'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
