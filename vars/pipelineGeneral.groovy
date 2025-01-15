@Library('devops@feature')_
def Sonar = library('devops@feature').org.devops.lb_buildartefacto
def Build = library('devops@feature').org.devops.lb_buildartefacto

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
                    
                    Build.cloneRepository()
                }
            }
        }

        stage('Install Dependencies') {
            steps {
                script {
                    Build.installNpm()
                }
            }
        }

        stage('Run Tests and Check Coverage') {
            steps {
                script {
                    Sonar.testCoverage()
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    Sonar.analisisSonar()
                }
            }
        }
    }
}
