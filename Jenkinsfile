pipeline {
    agent any

    tools {
        maven 'Maven' // Use the name you provided when configuring Maven in Jenkins
        allure 'Allure'
    }

    environment {
        GIT_REPO_URL = 'https://github.com/OlehHalush/petRepositoryPDP'
        GIT_BRANCH = 'allureAndTestNg'
        GIT_CREDENTIALS_ID = 'ce798702-4f25-4484-841f-9ec6323680da'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Checkout the specified branch from the Git repository
                    checkout([$class: 'GitSCM', branches: [[name: GIT_BRANCH]], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: GIT_CREDENTIALS_ID, url: GIT_REPO_URL]]])
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Run all tests using TestNG
                    sh 'mvn clean test -Dremote=${remote} -Ddriver.type=${driver_type} -DsuiteXmlFile=${suite_xml_file}'
                }
            }
        }

        stage('Generate Allure Report') {
            steps {
                script {
                    sh '${ALLURE_HOME}/bin/allure generate target/reports/allure-results -o target/reports/allure-reports'
                }
                archiveArtifacts 'target/reports/allure-reports/**'
                allure includeProperties: false, jdk: '', properties: [], reportBuildPolicy: 'ALWAYS', results: [[path: 'target/reports/allure-results']]
            }
        }
    }
}
