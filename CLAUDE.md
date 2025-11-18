# CLAUDE.md

이 파일은 Claude Code (claude.ai/code)가 이 저장소에서 작업할 때 필요한 가이드를 제공합니다.

## 프로젝트 개요

Spring Boot 3.2.0과 Thymeleaf 템플릿 엔진을 사용하는 웹 애플리케이션입니다. Gradle과 Java 17로 빌드되며, 한글 콘텐츠를 제공합니다.

## 빌드 및 개발 명령어

### Gradle Wrapper 생성 (최초 1회)
Gradle이 설치되어 있다면:
```bash
gradle wrapper --gradle-version 8.5
```

### 애플리케이션 실행
```bash
./gradlew bootRun
# Windows의 경우: gradlew.bat bootRun
```
기본적으로 http://localhost:8080 에서 실행됩니다.

### 프로젝트 빌드
```bash
./gradlew clean build
```
실행 가능한 JAR 파일이 `build/libs/simple-webapp-1.0.0.jar`에 생성됩니다.

### 빌드된 JAR 실행
```bash
java -jar build/libs/simple-webapp-1.0.0.jar
```

### 테스트 실행
```bash
./gradlew test
```
현재 테스트 디렉토리가 존재하지 않습니다.

### 의존성 확인
```bash
./gradlew dependencies
```

## 아키텍처

**애플리케이션 진입점**: `WebApplication.java` - 표준 Spring Boot `@SpringBootApplication` 메인 클래스

**MVC 구조**:
- **컨트롤러**: `com.example.webapp.controller` 패키지에 위치
  - 모든 컨트롤러는 Spring MVC `@Controller` 어노테이션 사용 (`@RestController` 아님)
  - Thymeleaf 템플릿에 매핑되는 뷰 이름을 반환
- **뷰**: `src/main/resources/templates/`의 Thymeleaf 템플릿
  - 템플릿은 `.html` 확장자 사용
  - 개발 중 Thymeleaf 캐싱 비활성화 (`spring.thymeleaf.cache=false`)
- **정적 리소스**: `src/main/resources/static/`의 CSS 및 기타 정적 파일

**설정**:
- 주요 설정은 `application.properties`에 위치
- 서버 포트: 8080
- 로깅: root는 INFO 레벨, `com.example.webapp`는 DEBUG 레벨
- 개발 중 핫 리로드를 위한 DevTools 활성화

## 언어 및 콘텐츠

이 애플리케이션은 UI 콘텐츠와 메시지에 한글을 사용합니다. 새로운 기능 추가 시:
- 사용자 대면 텍스트는 한글로 작성
- 코드 주석도 한글로 작성
- 모델 속성 및 변수명은 영문 규칙 준수

## 현재 라우트

- `/` - 홈 페이지 (HomeController.java:14)
- `/about` - 소개 페이지 (HomeController.java:23)
