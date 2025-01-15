@Library('devops@feature')_


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
                        def Build = library('devops').org.devops.lb_buildartefacto
                        Build.installNpm()
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