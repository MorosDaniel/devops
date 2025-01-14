package org.devops

def cloneRepository(){
    git branch: "${env.GIT_BRANCH_1}", url: "${env.GIT_URL_1}"
}

def installNpm(){
    sh 'npm install'
}