@Library('devops@feature')_

def call() { 
    pipeline {
        agent any

        tools {
            nodejs "NodeJS"
            jdk "jdk"
            maven  "maven3"
        }

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
            stage ('test') {
                steps{
                    script {
                        def sonar = new org.devops.lb_analisissonarqube()
                        sonar.testCoverage()
                    }
                }
            }
            stage ('analisis en sonar') {
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