pipeline {
    agent any  // ou agent { label 'linux' } si tu as un label spécifique

    stages {
        stage('Scan') {
            steps {
                withSonarQubeEnv('sq1') {  // exactement 'sq1' comme dans la vidéo
                    sh './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
                    // ou simplement sh 'mvn sonar:sonar' si tu préfères (plus simple)
                }
            }
        }

        // Ajoute si tu veux le Quality Gate comme extension
        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
