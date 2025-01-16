package org.devops

def testCoverage(){
    sh 'npm test'
}

def analisisSonar(gitName){
    def scannerHome = tool 'sonar-scanner'
    if(scannerHome){
        withSonarQubeEnv('sonar-scanner'){
            sh """${scannerHome}/bin/sonar-scanner \
            -Dsonar.host.url='http://localhost:9000/' \
            -Dsonar.login='squ_6a9fb265f6ed96871fc6ac04c1aca9272525e691' \
            -Dsonar.projectKey=${gitName} \
            -Dsonar.projectName=${gitName} \
            -Dsonar.sources=${env.source} \
            -Dsonar.tests=src/__test__ \
            -Dsonar.exclusions='**/*.test.js' \
            -Dsonar.testExecutionReportPaths=./test-report.xml \
            -Dsonar.javascript.lcov.reportPaths=./coverage/lcov.info"""

        }
    } else{
        error 'SonarQube Scanner not found'
    }
}