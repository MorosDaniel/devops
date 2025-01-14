@Library('devops@feature')_

pipeline {
    agent any

    tools {
        nodejs "NodeJS" // NodeJS configurado en Jenkins
        jdk "jdk"
        maven  "maven3"
    }

    environment {
        PROJECT_NAME = "${env.GIT_URL.tokenize('/').last().replace('.git', '')}"
        BRANCH_NAME = 'feature' // Cambia si usas otro nombre de rama
        SOURCE_PATH = './src' // Define el directorio donde se encuentran los archivos fuente
        SCANNER_HOME=tool 'sonarscanner'
    }

    stages {
        stage('Clone Repository') {
            steps {
                script{
                    lb_buildartefacto.clone()
                }
            }
        }

        stage('Install Dependencies') {
            steps {
                script {
                    lb_buildartefacto.install()
                }
            }
        }

        stage('Run Tests and Check Coverage') {
            steps {
                script {
                    lb_analisissonarqube.testCoverage()
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    lb_analisissonarqube.analisisSonar()
                }
            }
        }
    }
}
