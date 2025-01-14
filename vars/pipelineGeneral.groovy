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
                script {
                    env.nameBranch = BRANCH_NAME
                    env.UrlGitHub = env.GIT_URL
                    org.devops.clone() // Llama a la función de clonado
                }
            }
        }

        stage('Install Dependencies') {
            steps {
                script {
                    org.devops.install() // Llama a la función de instalación
                }
            }
        }

        stage('Run Tests and Check Coverage') {
            steps {
                script {
                    org.devops.testCoverage() // Ejecuta las pruebas y genera el informe de cobertura
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv('sonar-server') {
                    sh ''' $SCANNER_HOME/bin/sonar-scanner \
                    -Dsonar.projectName=Petclinic \
                    -Dsonar.java.binaries=. \
                    -Dsonar.projectKey=Petclinic '''
                }
            }
        }
    }
}
}