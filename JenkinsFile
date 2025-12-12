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
                sh "mvn -B clean compile"
            }
        }

        stage('Test') {
            steps {
                echo "Lanzando mvn test"
                sh "mvn -B test"
            }
        }

        stage('Package') {
            steps {
                echo "Lanzando mvn package"
                sh "mvn -B package"
            }
        }

        stage('Move jar') {
            steps {
                echo "Eliminando directorio versiones...."
                sh '''
                if [ -d versiones ]; then
                  rm -rf versiones
                fi
                '''
            }
            post {
                success {
                    echo "Se crea el directorio versiones con la última versión de la api"
                    sh '''
                    set -e
                    mkdir -p versiones
                    # buscar jar que contenga la VERSION y que no sea -original
                    JAR=$(ls target/*${VERSION}*.jar 2>/dev/null | grep -v 'original' | head -n1 || true)
                    if [ -z "$JAR" ]; then
                      echo "No se encontró JAR con la versión ${VERSION} en target. Listado target:"
                      ls -la target || true
                      exit 1
                    fi
                    echo "Copiando $JAR a versiones/"
                    cp "$JAR" versiones/
                    '''
                }
                failure {
                    echo "Move jar: pasos previos fallaron; no se realizan acciones posteriores."
                }
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

