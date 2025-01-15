@Library('devops@feature')_

import org.devops.lb_buildartefacto

def call() {
    pipeline {
        
        agent any

        tools {
            nodejs "NodeJS"
            jdk "jdk"
            maven  "maven3"
        }

        environment {
            BRANCH_NAME = 'feature' 
            SCANNER_HOME=tool 'sonarscanner'
        }

        stages {
            stage('Clone Repository') {
                steps {
                    script{
                        lb_buildartefacto.cloneRepository(env.GIT_BRANCH_1,env.GIT_URL_1)
                    }
                }
            }

            stage('Install Dependencies') {
                steps {
                    script {
                        Build.installNpm()
                    }
                }
            }

            stage('Run Tests and Check Coverage') {
                steps {
                    script {
                        Sonar.testCoverage()
                    }
                }
            }

            stage('SonarQube Analysis') {
                steps {
                    script {
                        Sonar.analisisSonar()
                    }
                }
            }
        }
    }
}