package org.devops

def cloneRepo(){
    git branch: "${env.nameBranch}", url: "${env.UrlGitHub}"
}

def installNpm(){
    sh 'npm install'
}