pipeline {
  agent any

  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }

  stages {

    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build + SonarQube (skip tests)') {
      steps {
        withSonarQubeEnv('sq1') {
          // Si ton Jenkins tourne sur Linux : OK avec sh
          sh 'mvn -B clean verify -DskipTests sonar:sonar'

          // Si un jour tu exécutes vraiment sur un agent Windows, remplace par :
          // bat 'mvn -B clean verify -DskipTests sonar:sonar'
        }
      }
    }

    // Optionnel : seulement si tu as configuré le webhook SonarQube -> Jenkins
    stage('Quality Gate') {
      steps {
        timeout(time: 5, unit: 'MINUTES') {
          waitForQualityGate abortPipeline: true
        }
      }
    }
  }

  post {
    always {
      // Ne plante pas si pas de rapports de tests (vu qu’on skip)
      junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true

      // Archive les jars si existants
      archiveArtifacts artifacts: 'target/*.jar', fingerprint: true, allowEmptyArchive: true
    }
  }
}
