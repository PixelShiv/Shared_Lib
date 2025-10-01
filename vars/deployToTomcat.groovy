def call(String warName = "target/*.war", String tomcatPath = "/opt/tomcat/webapps") {
    echo "Deploying WAR to Tomcat..."
    sh "sudo cp ${warName} ${tomcatPath}/"
    echo "Deployment complete!"
}

