def call(String warName = "target/*.war", 
         String tomcatUser = "ec2-user", 
         String tomcatHost = "34.207.147.216", 
         String tomcatPath = "/opt/tomcat/webapps/", 
         String appName = "app",
         String sshCredsId = "ec2-key") {  // Jenkins credential ID for the .pem key

    echo "Deploying WAR to Tomcat on remote host ${tomcatHost}..."

    sshagent([sshCredsId]) {
        // Copy WAR to remote host
        sh """
            scp -o StrictHostKeyChecking=no ${warName} ${tomcatUser}@${tomcatHost}:/home/${tomcatUser}/${appName}.war
        """

        // Deploy on remote host
        sh """
            ssh -o StrictHostKeyChecking=no ${tomcatUser}@${tomcatHost} "
                mv /home/${tomcatUser}/${appName}.war ${tomcatPath}${appName}.war && \
                if [ -d ${tomcatPath}${appName} ]; then rm -rf ${tomcatPath}${appName}; fi && \
                chown -R ${tomcatUser}:${tomcatUser} ${tomcatPath}${appName}.war && \
                systemctl restart tomcat || true
            "
        """
    }

    echo "Deployment to Tomcat complete!"
}
