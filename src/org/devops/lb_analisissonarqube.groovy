package org.devops

def cloneRepository(){
    git branch: "${env.nameBranch}", url: "${env.UrlGitHub}"
}

def installNpm(){
    sh 'npm install'
}