def call(String warName = "target/*.war", 
         String tomcatUser = "ec2-user", 
         String tomcatHost = "34.207.147.216", 
         String tomcatPath = "/opt/tomcat/webapps/", 
         String appName = "app") {

    echo "Deploying WAR to Tomcat on remote host ${tomcatHost}..."

    // Copy WAR to remote host
    sh """
        scp -o StrictHostKeyChecking=no ${warName} ${tomcatUser}@${tomcatHost}:/home/${tomcatUser}/${appName}.war
    """

    // Deploy on remote host
    sh """
        ssh -o StrictHostKeyChecking=no ${tomcatUser}@${tomcatHost} "
            sudo mv /home/${tomcatUser}/${appName}.war ${tomcatPath}${appName}.war && \
            if [ -d ${tomcatPath}${appName} ]; then sudo rm -rf ${tomcatPath}${appName}; fi && \
            sudo chown -R tomcat:tomcat ${tomcatPath}${appName}.war && \
            sudo systemctl restart tomcat || true
        "
    """

    echo "Deployment to Tomcat complete!"
}

