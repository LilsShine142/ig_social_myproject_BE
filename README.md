# ig_social_myproject_BE
Backend dự án cá nhân website social media 

## Yêu cầu hệ thống

- Java 21+
- Maven 3.8+
- PostgreSQL 17+
- Node.js 18+ 

## Cài đặt Backend

### 1. Cấu hình Database
```bash
# Tạo database
createdb ig_social_myproject
# Cấu hình ứng dụng
Tạo file application-local.properties trong src/main/resources
spring.datasource.url=jdbc:postgresql://localhost:5432/ig_social_myproject
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

# Build và chạy
mvn clean install
mvn spring-boot:run -Dspring-boot.run.profiles=local

# Hoặc chạy trực tiếp từ IDE
