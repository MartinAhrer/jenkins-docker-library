package at.softwarecraftsmen.docker

def createIf(name) {
    def id = sh(returnStdout: true, script: "docker volume ls --filter name=${name} -q").trim()
    if (!id) {
        id=sh (script: "docker volume create --name ${name}", returnStdout: true).trim()
    }
    return id
}
