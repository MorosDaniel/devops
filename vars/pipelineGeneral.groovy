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

        stages {
            stage('Clone Repository') {
                steps {
                    script{
                        git branch: "${env.GIT_BRANCH_1}", url: "${env.GIT_URL_1}"
                    }
                }
            }

            stage('Install Dependencies') {
                steps {
                    script {
                        def BuildArt = new lb_buildartefacto();
                        BuildArt.installNpm()
                    }
                }
            }

            stage('Run Tests and Check Coverage') {
                steps {
                    script {
                        def AnalisisSonar = new lb_analisissonarqube();
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