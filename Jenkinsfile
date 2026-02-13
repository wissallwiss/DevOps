pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }
    stages {
        stage('GIT') {
            steps {
                git branch: 'DevOps',
                url: 'https://github.com/wissallwiss/DevOps.git'
              
            }
        }
    }
}