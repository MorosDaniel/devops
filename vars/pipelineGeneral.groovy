@Library('devops@feature')_

import org.devops.lb_buildartefacto

def build = new lb_buildartefacto()

pipeline {
    agent any
    stages {
        stage ('Construccion') {
            when {
                expression { build.cloneRepository() }
            }
            steps {
                echo "Hecho manito"
            }
        }
    }
}