def call() {
    def volume = sh(returnStdout: true, script: 'docker volume ls --filter name=dot_gradle -q').trim()
    if (!volume) {
        sh 'docker volume create --name dot_gradle'
    }
}
