pipeline {
  agent any

  options {
    buildDiscarder(logRotator(numToKeepStr: '5'))
  }

  stages {
    stage('Sonar') {
      steps {
        withSonarQubeEnv('sq1') {
          sh 'mvn -B clean verify sonar:sonar'
        }
      }
    }
  }
}
