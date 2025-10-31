# Kurs_3_DB — система управления вакансиями, работодателями, работниками и навыками

Полнофункциональное веб‑приложение на Spring Boot 3 для управления сущностями:

- Работодатели и их вакансии
- Работники и их навыки
- Трудоустройства (история занятости работника на вакансиях)
- Навыки (привязка к вакансиям и работникам)

Интерфейс построен на Spring MVC + Thymeleaf, данные хранятся в PostgreSQL через Spring Data JPA. Есть страница статистики и поиск/фильтрация вакансий. Генерация схемы БД выполняется автоматически (Hibernate ddl‑auto=update).


## Технологии

- Java 21
- Spring Boot 3.5 (Web, Data JPA, Validation, Thymeleaf)
- PostgreSQL (JDBC driver)
- Lombok
- Springdoc OpenAPI (Swagger UI)
- Maven Wrapper (mvnw)


## Быстрый старт

### 1) Предварительные требования

- Установлены Java 21+ и доступ к PostgreSQL
- Порты и доступ к БД настроены

### 2) Настройка подключения к БД

Приложение читает параметры подключения из переменных окружения:

- DB_URL — JDBC URL, например `jdbc:postgresql://localhost:5432/kurs3db`
- DB_USERNAME — имя пользователя БД
- DB_PASSWORD — пароль

Либо задайте их перед запуском (Windows PowerShell):

```powershell
$env:DB_URL = "jdbc:postgresql://localhost:5432/kurs3db"
$env:DB_USERNAME = "postgres"
$env:DB_PASSWORD = "postgres"
```

Конфигурация в `src/main/resources/application.properties`:

```1:9:src/main/resources/application.properties
spring.application.name=Kurs_3_DB

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3) Запуск в dev‑режиме

```bash
./mvnw spring-boot:run
```

Windows (cmd):

```bat
mvnw.cmd spring-boot:run
```

После старта приложение будет доступно на `http://localhost:8080`.

### 4) Сборка и запуск JAR

```bash
./mvnw clean package -DskipTests
java -jar target/Kurs_3_DB-0.0.1-SNAPSHOT.jar
```


## Пользовательский интерфейс (Thymeleaf)

Основные маршруты UI (все возвращают HTML‑шаблоны):

- Работодатели: `/view/employer`
  - список: пагинация, создание, редактирование
- Вакансии: `/view/vacancy`
  - общий список с фильтрами и сортировками (см. ниже)
  - вакансии конкретного работодателя: `/view/vacancy/{employerId}`
  - создание вакансии для работодателя: `/view/vacancy/create/{employerId}`
  - редактирование: `/view/vacancy/edit/{id}`
- Работники: `/view/worker`
  - список, создание, редактирование, удаление
- Навыки: `/view/skill`
  - список, создание, редактирование, удаление
  - навыки вакансии: `/view/skill/{employerId}/{vacancyId}`
  - добавить/удалить навык вакансии: POST `/view/skill/{vacancyId}/skills/add`, `/view/skill/{vacancyId}/remove`
  - навыки работника: `/view/skill/{workerId}`
  - добавить/удалить навык работнику: POST `/view/skill/{workerId}/add/worker`, `/view/skill/{workerId}/remove/worker`
- Трудоустройства работника: `/view/employment/{workerId}` (список; добавление/удаление записей)
- Статистика: `/view/stats` (общее количество работников, вакансий, трудоустройств; топ‑вакансии по популярности)


### Поиск и фильтрация вакансий

Эндпоинт: `/view/vacancy`

Поддерживаемые query‑параметры:

- `post` — подстрочный поиск по названию должности
- `employer` — подстрочный поиск по названию работодателя
- `minSalary` — минимальная зарплата
- Пагинация и сортировка: `page`, `size`, `sort` (поле), `dir` (asc|desc)

Пример:

```
/view/vacancy?post=java&employer=acme&minSalary=80000&sort=salary&dir=desc&page=0&size=10
```


## Swagger / OpenAPI

Подключён Springdoc. После запуска откройте Swagger UI:

- `http://localhost:8080/swagger-ui.html`


## Архитектура проекта

Пакеты `ru.zyuzyukov.kurs_3_db`:

- `controllers` — слой MVC‑контроллеров (`@Controller`) для HTML‑страниц (`EmployerController`, `VacancyController`, `WorkerController`, `SkillController`, `EmploymentController`, `StatsController`).
- `db.entity` — JPA‑сущности: `Employer`, `Vacancy`, `Worker`, `Skill`, `Employment`.
  - Связи:
    - Employer 1‑N Vacancy
    - Vacancy M‑M Skill
    - Worker M‑M Skill
    - Employment связывает Worker и Vacancy, содержит даты (`date_open`, `date_closed`)
- `db.repositories` — Spring Data JPA репозитории, включая спецификации и кастом‑запросы (например, популярные вакансии через `findVacanciesByPopularity`).
- `db.service` — сервисный слой с базовыми CRUD‑операциями (`BaseService`) и бизнес‑логикой (добавление/удаление навыков, поиск вакансий по критериям, операции с трудоустройствами и др.).
- `dto` и `mapper` — DTO‑объекты и мапперы для передачи данных между слоями и шаблонами.
- `resources/templates` — Thymeleaf‑шаблоны для всех разделов (employer, vacancy, worker, skill, employment, stats).


## Модель данных (кратко)

- `Employer(id, name, active, vacancyList)`
- `Vacancy(id, employer, salary, description, post, active, vacancySkills)`
- `Worker(id, name, employments, workerSkills)`
- `Skill(id, name, vacancies, workers)`
- `Employment(id, worker, vacancy, date_open, date_closed)`


## Инициализация данных (опционально)

В проекте есть компонент `DataGenerator` (`db.generator.DataGenerator`) для генерации демонстрационных данных. По умолчанию вызов отключён (закомментирован в конструкторе). При необходимости можно раскомментировать вызов `generateData()` для авто‑заполнения БД тестовыми данными.


## Сборка, тесты, качество

- Сборка: `./mvnw clean package`
- Тесты: `./mvnw test`
- Целевая версия Java: 21 (см. `pom.xml`)


## Переменные окружения

- `DB_URL` — пример: `jdbc:postgresql://localhost:5432/kurs3db`
- `DB_USERNAME` — пример: `postgres`
- `DB_PASSWORD` — пример: `postgres`


## Лицензия

Проект образовательный; при необходимости добавьте текст лицензии в `LICENSE`.


