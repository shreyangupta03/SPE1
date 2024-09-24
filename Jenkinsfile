pipeline{
    agent any
    tools {
        maven 'mvn'
    }
    environment{
        docker_image = ""
        registry="neogenkai/calculator"
        dpass=""
    }
    stages{
        stage('Stage 1: Git Clone'){
            steps{
                git branch: 'main',
                url:'https://github.com/Shreyansh-Rai/Calc_SPE_MiniProj'
            }
        }
        stage('Step 2: Maven Build'){
            steps{
                sh 'mvn clean install'
            }
        }
        stage('Stage 3: Build Docker Image'){
            steps{
                script{
                    docker_image = sh '/usr/local/bin/docker build -t' + registry + ':latest .'
                }
            }
        }
        stage('Stage 4: Push docker image to hub') {
    steps {
        script {
            withCredentials([string(credentialsId: 'docker-hub-credentials-id', variable: 'dpass')]) {
                sh '/usr/local/bin/docker login -u "neogenkai" -p ' + dpass
                sh '/usr/local/bin/docker push ' + registry + ':latest'
            }
        }
    }
}
        stage('Stage 5: Clean docker images'){
            steps{
                script{
                    sh '/usr/local/bin/docker container prune -f'
                    sh '/usr/local/bin/docker image prune -f'
                }
            }
        }
        stage('Step 6: Ansible Deployment'){
            steps{
                sh '/Users/shreyanshrai/Library/Python/3.12/bin/ansible-playbook Deployment/deploy.yml -i Deployment/inventory -e image_name=$registry'
            }
        }
    }
}