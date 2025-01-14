package org.devops

def cloneRepository(){
    git branch: "${GIT_BRANCH_1}", url: "${GIT_URL_1}"
}

def installNpm(){
    sh 'npm install'
}