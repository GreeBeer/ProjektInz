pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        sh './gradlew test'
      }
      post{
        always {
            junit 'build/test-results/test/*.xml'
        }
      }
    }
  }
}