pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Đang tải source code về từ Git...'
                checkout scm
            }
        }
        stage('Build') {
            steps {
                echo 'Bắt đầu quá trình Build...'
                // Thêm các lệnh build của bạn ở đây (vd: sh 'npm install' hoặc sh 'mvn clean install')
            }
        }
        stage('Test') {
            steps {
                echo 'Chạy Unit Test...'
                // Thêm các lệnh test của bạn ở đây
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploy ứng dụng thành công!'
            }
        }
    }
}
