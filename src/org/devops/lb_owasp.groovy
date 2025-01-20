package org.devops

def analisisOwasp(proyectGitName){
    sh """ docker run --rm -v ProyectOwasp:/zap/wrk/:rw \
         --user root --network=${env.NameNetwork} \
         -t zaproxy/zap-weekly \
         zap-full-scan.py \
         -t ${env.dominio} \
         -r ProyectOwasp.html -I
        """
}