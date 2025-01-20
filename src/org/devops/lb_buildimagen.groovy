package org.devops

def buildImageDocker(proyectGitName){
    echo "build"
    sh "docker build -t danielrodriguez2802/${proyectGitName} ."
}