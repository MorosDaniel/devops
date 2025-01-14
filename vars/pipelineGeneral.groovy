@Library('devops@feature')_
import org.devops.lb_analisissonarqube
import org.devops.lb_buildartefacto

pipeline {
    agent any

    tools {
        rama = GIT_BRANCH_1
        urlgit = GIT_URL_1
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
                    cloneRepository()
                }
            }
        }

        stage('Install Dependencies') {
            steps {
                script {
                    BuildArt = new lb_buildartefacto();
                    BuildArt.installNpm()
                }
            }
        }

        stage('Run Tests and Check Coverage') {
            steps {
                script {
                    AnalisisSonar = new lb_analisissonarqube();
                    AnalisisSonar.testCoverage()
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    AnalisisSonar.analisisSonar()
                }
            }
        }
    }
}
