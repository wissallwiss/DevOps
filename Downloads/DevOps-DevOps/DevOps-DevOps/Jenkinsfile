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
          // utilise le token injecté par Jenkins
          sh 'mvn -B clean verify -DskipTests sonar:sonar -Dsonar.login=$SONAR_AUTH_TOKEN'
        }
      }
    }
  }

  post {
    always {
      junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
      archiveArtifacts artifacts: 'target/*.jar', fingerprint: true, allowEmptyArchive: true
    }
  }
}
