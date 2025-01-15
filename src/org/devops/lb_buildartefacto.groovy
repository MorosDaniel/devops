package org.devops

def cloneRepository(){
    echo "Clonando repositorio desde la librería compartida"
    git branch: "${env.nameBranch}", url: "${env.UrlGitHub}"
}

def installNpm(){
    sh 'npm install'
}