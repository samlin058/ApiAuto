# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

ApiAuto is a Java-based API automation testing framework built on TestNG that supports multi-project/multi-domain interface testing. It features annotation-driven interface modeling, data factory-driven process orchestration, dependency injection, parameter/result validation, concurrent/async control, reporting, and multi-environment/database configuration management.

## Key Commands

### Build and Test Commands

**Run all tests via TestNG suite:**
```bash
mvn -Dfile.encoding=UTF-8 test
```

**Run specific test class:**
```bash
mvn -Dtest=Application.TestCase.LoanMarket.TestCase.Zy.Loan_CreditTest test
```

**Run data factory/process (main class):**
```bash
mvn -Dfile.encoding=UTF-8 exec:java
```

**Compile project:**
```bash
mvn compile
```

**Clean and rebuild:**
```bash
mvn clean compile
```

### IDE Execution

- Right-click `testng.xml` in IDE to run TestNG suite
- Right-click individual test classes to run single tests
- Reports generated in `test-output/html` directory

## Architecture Overview

### Core Layers

1. **Application Layer** (`src/main/java/Application`)
   - `DataFactory/<Domain>`: Business process orchestration and reusable scenarios
   - `DBoperation/<Domain>`: Database operations for test data
   - `TestCase/<Domain>`: TestNG test cases organized by business domain

2. **Data Layer** (`src/main/java/Data`)
   - `Apis/<Domain>`: Interface controllers with annotation-driven API definitions
   - `Ro/<Domain>`: Request/Response objects (POJOs)
   - `Enum`: Domain-specific enumerations

3. **Service Layer** (`src/main/java/Service`)
   - `Annotations`: Custom annotations for API modeling and processing
   - `Base`: Base test classes and common API utilities
   - `Util`: HTTP, JSON, Excel, DB, encryption, concurrency utilities
   - `Config`: Environment and login configuration
   - `Listen`: TestNG listeners and reporting

### Business Domains

The framework supports multiple business domains:
- **LoanMarket**: Loan marketplace APIs
- **BlackShark**: Asset management system
- **Goblin**: Collection system
- **Alchemist**: CRM/lead management
- **IndoArk**: Indonesian market operations

### Key Annotations

- `@Mapping`: HTTP method and path mapping
- `@Header`: Request headers
- `@Param`/`@PathParam`: URL query/path parameters
- `@Body`/`@FileBody`/`@JsonArray`: Request body types
- `@DataFactory`: Reusable data factory steps
- `@Dependence`: Inject previous step outputs
- `@Required`: Parameter validation

### Configuration Files

- `src/main/resources/host.properties`: Environment host configurations
- `src/main/resources/configuration.properties`: Framework parameters
- `src/main/resources/jdbc.properties`: Database connections
- `src/main/resources/login.json`: Authentication configurations
- `src/main/resources/log4j.properties`: Logging configuration

### Test Execution Flow

1. **Interface Definition**: Define APIs in `Data/Apis/<Domain>` with annotations
2. **Data Modeling**: Create request/response POJOs in `Data/Ro/<Domain>`
3. **Process Orchestration**: Build reusable scenarios in `Application/DataFactory/<Domain>`
4. **Test Creation**: Write TestNG tests in `Application/TestCase/<Domain>`
5. **Data Preparation**: Use `Application/DBoperation/<Domain>` for database setup
6. **Execution**: Run via TestNG suite or individual classes

### Key Patterns

**API Controller Pattern:**
```java
public class AssessmentController extends ApiBase {
    @Mapping(sub_path = "/credit-assessment/new", requestType = "post")
    public String init(AssessmentControllerInitRo ro) {
        return http(ro);
    }
}
```

**Test Case Pattern:**
```java
public class QueryControllerTest extends BaseTestCase {
    @Test
    public void testMethod() {
        // Use assertion methods from BaseTestCase
        assertEq(actualValue, expectedValue);
    }
}
```

**Data Factory Pattern:**
```java
public class XYJH5Process {
    public static void main(String[] args) {
        H5processByPartner("RUIDAI", H5ProcessStage.CREDIT_APPLY);
    }
}
```

### Reporting

- TestNG + ReportNG integration for HTML reports
- Reports generated in `test-output/html`
- Custom listeners in `Service.Listen.TestListener`

### Database Integration

- MySQL support via JDBC
- Domain-specific DB operations in `Application/DBoperation/<Domain>`
- Connection configuration in `jdbc.properties`

### Utility Classes

Common utilities available in `Service.Util`:
- `JsonHandler`: JSON processing
- `HttpHandler`: HTTP client operations
- `ExcelHandler`: Excel file operations
- `ConcurrentHandler`: Concurrent execution
- `PollingHandler`: Async result polling
- `FileHandler`: File operations

## Development Guidelines

1. **New API**: Add to appropriate domain in `Data/Apis/<Domain>` with proper annotations
2. **New Test**: Extend `BaseTestCase` and use built-in assertion methods
3. **New Domain**: Create parallel structure under respective domain folders
4. **Configuration**: Update environment-specific files in `src/main/resources`
5. **Data Factory**: Create reusable process flows in `Application/DataFactory/<Domain>`

## Environment Setup

- JDK 1.8+
- Maven 3.6+
- MySQL (for database-dependent tests)
- Access to target backend environments

## Troubleshooting

- **Tests not running**: Ensure TestNG suite configuration in `testng.xml`
- **Connection issues**: Verify `host.properties` and environment accessibility
- **Authentication failures**: Check `login.json` and `defaultLogin.json`
- **Database errors**: Validate `jdbc.properties` and database connectivity
- **Report generation**: Confirm TestNG listeners are registered in suite

## Dependencies

Key dependencies include TestNG, ReportNG, Apache HttpClient, FastJSON, Gson, Jackson, POI, SnakeYAML, Hutool, MySQL driver, Freemarker, and JavaParser. See `pom.xml` for complete list.
