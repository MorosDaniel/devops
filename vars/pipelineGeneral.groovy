@Library('devops@feature')_

import org.devops.lb_buildartefacto

def build = new lb_buildartefacto()

def call() { 
    pipeline {
        agent any
        stages {
            stage ('Construccion') {
                steps{
                    script {
                        build.cloneRepository()
                    }
                }
            }
        }
    }
}