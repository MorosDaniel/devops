package org.devops

class SonarAnalyzer implements Serializable {
    private def script

    SonarAnalyzer(script) {
        this.script = script
    }

    def testCoverage() {
        script.sh 'npm test'
    }

    def analisisSonar(String gitName) {
        def scannerHome = script.tool 'sonar-scanner'
        if (scannerHome) {
            script.withSonarQubeEnv('sonar-scanner') {
                script.sh """${scannerHome}/bin/sonar-scanner \
                -Dsonar.projectKey=${gitName} \
                -Dsonar.projectName=${gitName} \
                -Dsonar.sources=${script.env.source} \
                -Dsonar.tests=src/__tests__ \
                -Dsonar.exclusions='**/*.test.js' \
                -Dsonar.testExecutionReportPaths=./test-report.xml \
                -Dsonar.javascript.lcov.reportPaths=./coverage/lcov.info"""
            }
        } else {
            script.error 'SonarQube Scanner not found'
        }
    }
}