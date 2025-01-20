@Library('devops@feature')_

def call() { 
    pipeline {
        agent any

        tools {
            nodejs "NodeJS"
        }
        environment {
            SONARNAME = 'react-test-jenkinsfile'
        }

        stages {
            stage ('Construccion Imagen Docker') {
                steps{
                    script {
                        def build = new org.devops.lb_buildimagen()
                        build.buildImageDocker(SONARNAME)
                    }
                }
            }
            stage ('Publicacion Imagen en Docker') {
                steps{
                    script {
                        def publicacion = new org.devops.lb_publicardockerhub()
                        publicacion.publicarImage(SONARNAME)
                    }
                }
            }
            stage ('Despliegue imagen Docker') {
                steps{
                    script {
                        def despliegue = new org.devops.lb_deploydocker()
                        despliegue.despliegueContenedor(SONARNAME)
                    }
                }
            }
            stage ('analisis en OWASP') {
                steps{
                    script {
                        def sonar = new org.devops.lb_owasp()
                        sonar.analisisOwasp(SONARNAME)
                    }
                }
            }
        }
    }
}