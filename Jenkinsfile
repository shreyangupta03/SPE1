pipeline {
    environment {
        DOCKERHUB_CRED = credentials("DockerHubCred")
        PATH = "/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:$PATH"
        DOCKER_PATH = "/usr/local/bin/docker" // Explicit Docker path
        ANSIBLE_PATH = "/Users/shreyangupta/myenv/bin/python"
    }
    tools {
        maven 'maven'
    }
    agent any
    stages {
        stage('Stage 1: Git Clone') {
            steps {
                git branch: 'main', url: 'https://github.com/shreyangupta03/SPE1.git'
            }
        }
        stage('Step 2: Maven Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Stage 3: Build Docker Image') {
            steps {
                script {
                    docker_image = sh "${DOCKER_PATH} build -t shreyangupta03/calculator:latest ."
                }
            }
        }
        stage('Stage 4: Push Docker Image to Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'DockerHubCred', usernameVariable: 'shreyangupta03', passwordVariable: 'Shreyanak1234')]) {
                        sh "${DOCKER_PATH} login -u ${shreyangupta03} -p ${Shreyanak1234}"
                        sh "${DOCKER_PATH} push shreyangupta03/calculator:latest"
                    }
                }
            }
        }
        stage('Stage 5: Clean Docker Images') {
            steps {
                script {
                    sh "${DOCKER_PATH} container prune -f"
                    sh "${DOCKER_PATH} image prune -f"
                }
            }
        }
        stage('Step 6: Ansible Deployment') {
            steps {
                sh "ansible-playbook -i Deployment/inventory Deployment/deploy.yml"
            }
        }
    }
}