package org.devops

def publicarImage(proyectGitName){
    withCredentials([usernamePassword(credentialsId: "${env.TOKEN_ID}",
    passwordVariable: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USERNAME')]) {
        sh "docker login -u ${env.DOCKERHUB_USERNAME} -p ${env.DOCKERHUB_PASSWORD}"
        sh "docker tag ${proyectGitName} ${env.DOCKERHUB_USERNAME}/${proyectGitName}"
        sh "docker push ${env.DOCKERHUB_USERNAME}/${proyectGitName}"
    }
}