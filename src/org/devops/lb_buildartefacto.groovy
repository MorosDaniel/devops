package org.devops

def cloneRepository(){
    echo "Clonando repositorio desde la librería compartida"
    git branch: "${GIT_BRANCH_1}", url: "${GIT_URL_1}"
}

def installNpm(){
    sh 'npm install'
}