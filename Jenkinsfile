pipeline {
    agent any  // ou { label 'linux' } si tu as un agent spécifique configuré

    stages {
        stage('Scan') {
            steps {
                withSonarQubeEnv('sq1') {  // ← Pas de 'installationName:', juste le nom en premier paramètre (comme dans la vidéo et la doc officielle)
                    sh './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:sonar'  // version auto (plus stable que hardcodée)
                    // Alternative simple : sh './mvnw clean sonar:sonar'
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
