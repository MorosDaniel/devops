import org.devops.lb_analisissonarqube
import org.devops.lb_buildartefacto

def call(Map config){
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
                        def BuildArt = new GitOperations(this);
                        BuildArt.clone()
                    }
                }
            }

            stage('Install Dependencies') {
                steps {
                    script {
                        BuildArt.install()
                    }
                }
            }

            stage('Run Tests and Check Coverage') {
                steps {
                    script {
                        def AnalisisSonar = new SonarAnalyzer(this);
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
}