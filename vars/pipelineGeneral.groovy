@Library('devops@feature')_

package org.devops

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