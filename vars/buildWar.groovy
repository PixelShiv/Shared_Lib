def call() {
    echo "Building WAR file using Maven..."
    sh "mvn clean package -DskipTests"
}
