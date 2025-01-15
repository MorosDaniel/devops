@Library('devops@feature')_

def call() { 
    pipeline {
        agent any
        stages {
            stage ('debug') {
                steps{
                    script {
                        echo "Branch: ${env.GIT_BRANCH_1}"
                        echo "GitHub URL: ${env.GIT_URL_1}"
                    }
                }
            }
            stage ('Construccion') {
                steps{
                    script {
                        def build = new org.devops.lb_buildartefacto()
                        build.cloneRepository()
                    }
                }
            }
        }
    }
}