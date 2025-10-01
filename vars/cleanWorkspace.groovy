def call() {
    echo "Cleaning workspace..."
    deleteDir()  // Jenkins pipeline step: wipes workspace
}
