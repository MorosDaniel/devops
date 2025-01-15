@Library('devops@feature')_

def call() { 
    pipeline {
        agent any
        stages {
            stage ('Construccion') {
                steps{
                    script {
                        def build = new org.devops.lb_buildartefacto()
                        build.cloneRepository()
                    }
                }
            }
            stage ('Instalar dependencias') {
                steps{
                    script {
                        def build = new org.devops.lb_buildartefacto()
                        build.installNpm()
                    }
                }
            }
            stage ('Construccion') {
                steps{
                    script {
                        def sonar = new org.devops.lb_analisissonarqube()
                        sonar.testCoverage()
                    }
                }
            }
            stage ('Construccion') {
                steps{
                    script {
                        def sonar = new org.devops.lb_analisissonarqube()
                        sonar.analisisSonar(GIT_URL_1)
                    }
                }
            }
        }
    }
}