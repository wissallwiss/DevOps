pipeline {
    agent { label 'linux'}
    options{
        buildDiscarder(logRotator(numTokeepStr: '5'))
    }
   stages {
        stage('Scan') {
            steps {
                withSonarQubeEnv(insrallationName: 'sq1') { 
                    sh './mvnw sonar:sonar'
                }
            }
        }
    }
}
