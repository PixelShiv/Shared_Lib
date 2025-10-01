def call(String warName = "target/*.war", String tomcatPath = "/opt/tomcat/webapps") {
    echo "Deploying WAR to Tomcat..."
    sh "cp ${warName} ${tomcatPath}/"
    echo "Deployment complete!"
}

