package org.devops

def testCoverage(){
    sh 'npm test'
}

def analisisSonar(gitName){
    def scannerHome = tool 'sonar-scanner'
    if(scannerHome){
        withSonarQubeEnv('sonar-scanner'){
            sh "${scannerHome}/bin/sonar-scanner \
            -Dsonar.proyectKey=${gitName} \
            -Dsonar.proyectName=${gitName} \
            -Dsonar.sources=${env.source} \
            -Dsonar.tests=src/_test_ \
            -Dsonar.exclusions='**/*.test.js' \
            -Dsonar.testExecutionReportPaths=./test-report.xml \
            -Dsonar.javascript.lcov.reportPaths=./coverage/lcov.info"

        }
    } else{
        error 'SonarQube Scanner not found'
    }
}