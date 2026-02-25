pipeline {
    agent any  // ou agent { label 'ton-label' } si tu as des agents spécifiques
    
    tools {
        maven 'M2_HOME'  // garde ça si c'est bien configuré dans Global Tool Configuration
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Code déjà checkout automatiquement par Jenkins (via SCM)'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean verify'  // compile + lance les tests unitaires
            }
        }

        stage('SonarQube Analysis') {
            steps {
                   withSonarQubeEnv('sq1') {  // ← mets EXACTEMENT le nom que tu as donné à ton serveur Sonar dans Manage Jenkins > System > SonarQube servers
                    sh 'mvn sonar:sonar'
                }
            }
        }

        // Quality Gate (optionnel pour l'instant – décommente quand le reste marche)
        // stage('Quality Gate') {
        //     steps {
        //         timeout(time: 3, unit: 'MINUTES') {
        //             waitForQualityGate abortPipeline: true
        //         }
        //     }
        // }
    }

    post {
        always {
            // Pour éviter l'erreur FilePath missing : on met un check
            script {
                if (currentBuild.result != 'ABORTED' && fileExists('target/surefire-reports/TEST-*.xml')) {
                    junit '**/target/surefire-reports/*.xml'
                } else {
                    echo 'Aucun rapport de test trouvé ou build avorté → skip junit'
                }
            }
        }
    }
}