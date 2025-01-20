package org.devops

def despliegueContenedor(proyectGitName){
    sh "docker pull danielrodriguez2802/react-test-jenkinsfile"
    sh """ docker run -d --name ${proyectGitName} \
    --network=${env.NameNetwork} -p 5174:5174 \
    --user root danielrodriguez2802/${proyectGitName}
    """
}

