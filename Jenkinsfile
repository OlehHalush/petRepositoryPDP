pipeline {
    agent any

    environment {
        MAVEN_HOME = tool 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout allureAndTestNg
            }
        }

        stage('Build') {
            steps {
                script {
                    // Set Maven executable to the one configured in Jenkins
                    def mavenHome = tool 'Maven'
                    def mavenBin = "${mavenHome}/bin/mvn"
                    env.PATH = "${mavenBin}:${env.PATH}"

                    // Build the project
                    sh 'mvn clean install'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Run all tests using TestNG
                    sh 'mvn test'
                }
            }
        }
    }
}