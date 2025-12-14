pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven-3'
    }

    environment {
        VERSION = "1.1.0"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Haciendo checkout del repositorio..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Lanzando mvn clean compile"
                bat "mvn -B clean compile"
            }
        }

        stage('Test') {
            steps {
                echo "Lanzando mvn test"
                bat "mvn -B test"
            }
        }

        stage('Package') {
            steps {
                echo "Lanzando mvn package"
                bat "mvn -B package"
            }
        }

        stage('Move jar') {
            steps {
                echo "Preparando directorio versiones..."
                bat '''
                if exist versiones (
                    rmdir /s /q versiones
                )
                mkdir versiones
                '''
                bat '''
                for %%f in (target\\*%VERSION%*.jar) do (
                    echo Copiando %%f a versiones
                    copy %%f versiones
                    goto :end
                )
                echo No se encontró el JAR con la versión %VERSION%
                exit /b 1
                :end
                '''
            }
        }
    }

    post {
        always {
            echo "Pipeline finalizado."
        }
        success {
            echo "BUILD SUCCESS"
        }
        failure {
            echo "BUILD FAILED"
        }
    }
}

