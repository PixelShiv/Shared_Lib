def call(String repoUrl, String branch = "main", String credsId = "") {
    echo "Cloning repository: ${repoUrl}, branch: ${branch}"
    checkout([
        $class: 'GitSCM',
        branches: [[name: "*/${branch}"]],
        userRemoteConfigs: [[url: repoUrl, credentialsId: credsId]]
    ])
}
