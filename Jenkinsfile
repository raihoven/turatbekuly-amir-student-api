pipeline {
    agent any

    environment {
        STUDENT_NAME = 'Turatbekuly Amir'
        IMAGE_NAME   = 'turatbekuly-amir-student-api'
        IMAGE_TAG    = 'v1.0-turatbekuly-amir-37776'
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Student: ${STUDENT_NAME} - pipeline started"
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Building application for ${STUDENT_NAME}"
                sh 'chmod +x gradlew'
                sh './gradlew clean build -x test --no-daemon'
            }
        }

        stage('Test') {
            steps {
                echo "Running tests for ${STUDENT_NAME}"
                sh './gradlew test --no-daemon'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
                }
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .'
            }
        }

        stage('Verify') {
            steps {
                sh '''
                    docker image inspect ${IMAGE_NAME}:${IMAGE_TAG} > /dev/null
                    echo "VERIFY OK: image ${IMAGE_NAME}:${IMAGE_TAG} exists (student: ${STUDENT_NAME})"
                    docker images ${IMAGE_NAME}
                '''
            }
        }
    }

    post {
        success { echo "Pipeline SUCCESS for ${STUDENT_NAME}" }
        failure { echo "Pipeline FAILED for ${STUDENT_NAME}" }
    }
}
