pipeline{
    
    agent any
    environment {
        dockerhubCredentials = 'dockerhub-credentials'
        dockerImageTag = "salman1091/springboot-crud:latest"
    }
    tools{
        maven "maven_3_9_4"
    }
    stages{
        stage('Build Maven'){
            steps{
                script{

                    checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'git-credentials', url: 'https://github.com/salman0909/springboot-crud.git']])
                    sh 'mvn clean install'
                }
            }
        }
        stage('Building Docker Image'){
            steps{
                script{
                    sh 'docker build -t salman1091/springboot-crud .'
                }
            }
        } 
        stage('Pushing Docker image to DockerHub'){
            steps{
                script{
                    docker.withRegistry('', dockerhubCredentials) {
                        sh "docker push $dockerImageTag"
                    }
                }
            }
        }
        stage('Running the Container'){
            steps{
                script{
                    sh 'docker run -d -p 8081:8081 --name sb-crud $dockerImageTag'
                }
            }
        }
                    
        
    }
}
