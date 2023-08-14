pipeline{
    
    agent any
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
            
    }
}
