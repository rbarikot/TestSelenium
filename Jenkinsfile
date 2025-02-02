pipeline {
    agent{
       node
       {
       label 'master'
       }
    }

    environment {
        // Define path to your Maven installation and any other environment variables
        MAVEN_HOME = 'C://Users//rbarik//Desktop//DA RC//Software//apache-maven-3.9.8-bin//apache-maven-3.9.8'  // Adjust this path if necessary
        GRID_URL = 'http://localhost:4444/wd/hub' // URL of your Selenium Grid Hub
        JAVA_HOME='C://Program Files//Java//jdk-17'
    }

    tools {
        maven 'Maven'  // Adjust according to your Maven installation
        jdk 'JDK'  // Adjust according to your Java version
    }

    stages {
        stage('check Git version') {
                steps {
                    sh 'git --version'
                }
        }
        stage('Clone Repository') {
            steps {
                script {
                    checkout([$class: 'GitSCM',
                        branches: [[name: '*/master']],  // Replace with your branch
                        userRemoteConfigs: [[url: 'https://github.com/rbarikot/TestSelenium.git']]
                    ])
                }
            }
        }

        stage('Start Docker Compose') {
            steps {
                script {
                    // Start Selenium Grid Docker containers
                    sh 'docker-compose -f docker-compose-v3.yml up -d'
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    // Run your tests using Maven
                    sh 'mvn clean test'
                }
            }
        }

        stage('Stop Docker Compose') {
            steps {
                script {
                    // Stop Selenium Grid Docker containers
                    sh 'docker-compose -f docker-compose-v3.yml down'
                }
            }
        }

        stage('Publish Allure Report') {
            steps {
                allure includeProperties: false, reportBuildPolicy: 'ALWAYS', results: [[path: 'allure-results']]
            }
        }
    }

    post {
        always {
            // Cleanup actions like removing Docker containers after test execution
            sh 'docker-compose -f docker-compose-v3.yml down'
        }
        success {
            echo "Tests completed successfully!"
        }
        failure {
            echo "Tests failed. Check the logs for errors."
        }
    }
}
