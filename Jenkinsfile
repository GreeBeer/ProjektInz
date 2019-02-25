pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        sh ./gradlew test
      }
    }
    stage('Deploy') {
      steps {
        echo 'Deploying....'
      }
    }
  }
}