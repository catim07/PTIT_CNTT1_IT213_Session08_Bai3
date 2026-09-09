# Session 08 - Bài 3: Thiết Lập Pipeline Tích Hợp Liên Tục (CI) Hoàn Chỉnh Với GitHub Actions

## 📌 Tổng Quan Pipeline CI (.github/workflows/ci-pipeline.yml)
Xây dựng luồng Tích hợp liên tục (Continuous Integration) tự động chạy mỗi khi có sự kiện `push` hoặc `pull_request` lên nhánh `main`:

```yaml
name: Java Microservices CI Pipeline

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  build-and-test:
    runs-on: ubuntu-latest

    services:
      postgres:
        image: postgres:15-alpine
        env:
          POSTGRES_DB: bank_db
          POSTGRES_USER: postgres
          POSTGRES_PASSWORD: rootpassword
        ports:
          - 5432:5432
        options: >-
          --health-cmd pg_isready
          --health-interval 10s
          --health-timeout 5s
          --health-retries 5

    steps:
      - name: Checkout Repository Code
        uses: actions/checkout@v3

      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Grant Execute Permission for Gradlew
        run: chmod +x gradlew

      - name: Run Unit & Integration Tests with Gradle
        run: ./gradlew test --no-daemon

      - name: Build Executable JAR Artifact
        run: ./gradlew bootJar --no-daemon

      - name: Upload Build Artifact
        uses: actions/upload-artifact@v3
        with:
          name: app-jar
          path: build/libs/*.jar
```

---

## 🛠️ Các Thành Phần Chính Trong Pipeline
1. **Containerized PostgreSQL Service**: Khởi tạo tự động container PostgreSQL 15 để phục vụ kiểm thử tích hợp DB.
2. **Automated Unit Tests**: Thực thi `./gradlew test` để đảm bảo 100% test case pass trước khi cho phép merge.
3. **Artifact Publishing**: Đóng gói tập tin `bootJar` và lưu trữ làm artifact tải xuống cho các bước CD (Continuous Deployment).

---

## 🚀 Kiểm Thử
```bash
./gradlew test
```
