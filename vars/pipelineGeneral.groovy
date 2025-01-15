@Library('devops@feature')_

def call() { 
    pipeline {
        agent any
        stages {
            stage ('Construccion') {
                steps{
                    script {
                        echo "Branch: ${env.nameBranch}"
                        echo "GitHub URL: ${env.UrlGitHub}"
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