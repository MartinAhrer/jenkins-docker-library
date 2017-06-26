def call() {
    def volume = sh(returnStdout: true, script: 'docker volume ls --filter name=dot_m2 -q').trim()
    if (!volume) {
        sh 'docker volume create --name dot_m2'
    }
}
