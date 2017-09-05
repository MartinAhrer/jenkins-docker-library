/**
 * Run simple 'one-shot' build step commands inside a docker container.
 * @param map arguments
 * @param command a command to be executed inside a container.
 * @return result of {@code sh} execution.
 */
def call(Map map = [:], String command) {
    def args = [image: 'buildpack-deps:jessie', workspace: "${env.JENKINS_AGENT_WORKSPACE}/${env.JOB_NAME}", returnStdout: false] << map

    def dockerRunOptions = "--rm -v ${args.workspace}:/workspace --workdir=/workspace"
    def dockerEnvOptions = ""
    for (Map.Entry variable : args.environment) {
        if (variable.value) {
            dockerEnvOptions <<= " -e \"${variable.key}=${variable.value}\""
        } else {
            dockerEnvOptions <<= " -e ${variable.key}"
        }
    }
    def dockerVolumeOptions = ""
    for (Map.Entry volume: args.volumes) {
        dockerVolumeOptions <<= " -v ${volume.key}:${volume.value}"
    }
    return sh(script: "docker run ${dockerRunOptions} ${dockerVolumeOptions} ${dockerEnvOptions} ${args.image} ${command}", returnStdout: args.returnStdout)
}
