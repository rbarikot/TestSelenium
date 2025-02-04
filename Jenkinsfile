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
        stage{
        steps {
            script {
                            def images = ["selenium/video:ffmpeg-4.3.1-20221219 ", "selenium/node-edge:4.7.2-20221219 ", "selenium/node-chrome:4.7.2-20221219" ,"selenium/node-firefox:4.7.2-20221219","selenium/hub:4.7.2-20221219"]  // Replace with actual image names from docker-compose.yml
                            def runningContainers = sh(script: "docker ps --format '{{.Image}}'", returnStdout: true).trim().split("\n")

                            def isRunning = images.every { img -> runningContainers.contains(img) }

                            if (isRunning) {
                                echo "Docker containers are already running. Skipping docker-compose up."
                                env.SKIP_COMPOSE = "true"
                            } else {
                                echo "Containers are not running. Proceeding with docker-compose up."
                                env.SKIP_COMPOSE = "false"
                            }
                        }
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
                    bat "mvn clean test -Dbrowser=%BROWSER% -Dtrigger=%TRIGGER%"
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
