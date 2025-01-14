package org.devops


class GitOperations implements Serializable {
    private def script

    GitOperations(script) {
        this.script = script
    }

    def clone() {
        try {
            script.git branch: "${script.env.nameBranch}", 
                      url: "${script.env.UrlGitHub}"
        } catch (Exception e) {
            script.error "Error al clonar repositorio: ${e.getMessage()}"
        }
    }

    def install() {
        try {
            script.sh 'npm install'
        } catch (Exception e) {
            script.error "Error durante npm install: ${e.getMessage()}"
        }
    }
}