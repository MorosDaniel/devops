@Library('devops@feature')_

def call() { 
    pipeline {
        agent any
        stages {
            stage ('Construccion') {
                steps{
                    script {
                        lb_buildartefacto.cloneRepository()
                    }
                }
            }
        }
    }
}