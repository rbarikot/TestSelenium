pipeline {
    agent any
     tools {
        maven 'mvn'  // Adjust according to your Maven installation
        jdk 'JDK'  // Adjust according to your Java version
        allure 'Allure'
    }
    parameters {
                    string(name: 'BROWSER', defaultValue: 'chrome', description: 'Browser to use for testing')
                    string(name: 'TRIGGER', defaultValue: 'local', description: 'Browser to use for testing')
                    }

    stages {
        stage('check Git version') {
                steps {
                    bat 'git --version'
                }
        }
        stage('Clone Repository') {
            steps {
                git branch: 'master' , url:'https://github.com/rbarikot/TestSelenium.git'
            }
        }

        stage('Start Docker Compose') {
            steps {
                script {
                    // Start Selenium Grid Docker containers
                    bat 'docker-compose -f docker-compose-v3.yml up -d'
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    // Run your tests using Maven
                    bat 'mvn clean test -Dbrowser=chrome -Dtrigger=remote'
                }
            }
        }

        stage('Stop Docker Compose') {
            steps {
                script {
                    // Stop Selenium Grid Docker containers
                    bat 'docker-compose -f docker-compose-v3.yml down'
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
            bat 'docker-compose -f docker-compose-v3.yml down'
        }
        success {
            echo "Tests completed successfully!"
        }
        failure {
            echo "Tests failed. Check the logs for errors."
        }
    }
}
