# ApiAuto 测试框架

![Java](https://img.shields.io/badge/Java-1.8%2B-blue)
![TestNG](https://img.shields.io/badge/TestNG-7.1.0-green)
![Maven](https://img.shields.io/badge/Maven-3.6%2B-orange)

ApiAuto 是一个基于 **Java + TestNG** 的企业级接口自动化测试框架，专为多项目、多业务域的复杂系统测试而设计。框架提供注解式接口建模、数据工厂驱动、依赖注入、并发控制等高级特性，支持统一接口测试、流程编排和报表输出。

## ✨ 核心特性

### 🏗️ 架构特性
- **注解式接口建模** - 通过声明式注解快速定义接口和校验规则
- **数据工厂驱动** - 支持可复用的业务流程编排和场景组合
- **依赖注入机制** - 智能注入前置步骤输出到后续请求
- **多域统一架构** - 一套框架支持多个业务系统的接口测试

### 🛠️ 功能特性
- **统一基础能力** - HTTP请求、JSON处理、Excel操作、数据库连接、并发控制
- **多环境配置** - 支持开发、测试、预发等多环境无缝切换
- **智能报表系统** - TestNG + ReportNG 集成，提供详细执行报告
- **代码生成工具** - Freemarker模板支持自动生成API对象结构
- **异步轮询机制** - 支持异步接口结果轮询和超时控制

### 📊 质量保障
- **参数校验** - 内置字段必填、格式、范围等校验机制
- **结果断言** - 提供等于、大于、包含等多种断言方式
- **并发测试** - 支持多线程并发场景的性能和稳定性测试
- **日志追踪** - 完整的执行日志和错误追踪机制

## 🏢 业务域支持

框架支持多个核心业务系统的接口测试：

| 业务域 | 系统名称 | 主要功能 |
|--------|----------|----------|
| **LoanMarket** | 贷款市场 | 信贷申请、授信评估、放款管理 |
| **BlackShark** | 资产管理系统 | 资产处置、质检管理、标签系统 |
| **Goblin** | 催收系统 | 案件查询、催收作业、还款处理 |
| **Alchemist** | CRM系统 | 客户管理、任务分配、活动配置 |
| **IndoArk** | 印尼业务 | 海外业务处理、多语言支持 |

## 📁 项目结构

```
src/main/java/
├── Application/                    # 应用层
│   ├── DataFactory/               # 数据工厂 - 业务流程编排
│   │   ├── LoanMarket/           # 贷款市场流程
│   │   ├── BlackShark/           # 资产管理系统流程
│   │   └── ...
│   ├── DBoperation/              # 数据库操作
│   └── TestCase/                 # 测试用例
│
├── Data/                          # 数据层
│   ├── Apis/                     # 接口控制器
│   ├── Ro/                       # 请求响应对象
│   └── Enum/                     # 枚举定义
│
└── Service/                      # 服务层
    ├── Annotations/             # 自定义注解
    ├── Base/                    # 基础类库
    ├── Util/                    # 工具类库
    └── Listen/                  # 监听器

src/main/resources/                 # 资源配置
├── host.properties               # 环境配置
├── configuration.properties      # 框架配置
├── jdbc.properties              # 数据库配置
├── login.json                   # 登录配置
└── TemplateFile/               # 代码生成模板
```

## 🚀 快速开始

### 环境要求

- **JDK 1.8+**
- **Maven 3.6+**
- **可选**: MySQL 5.7+ (数据库相关测试)
- **可选**: IntelliJ IDEA / Eclipse

### 安装依赖

```bash
# 克隆项目
git clone <repository-url>
cd ApiAuto

# 编译项目
mvn clean compile
```

### 配置环境

1. **配置目标环境** (`src/main/resources/host.properties`)
```properties
LoanMarket=http://loan-market.test.example.com/
BlackShark=http://black-shark.test.example.com/
```

2. **配置数据库** (`src/main/resources/jdbc.properties`)
```properties
jdbc.url=jdbc:mysql://localhost:3306/testdb
jdbc.username=test
jdbc.password=test123
```

3. **配置登录信息** (`src/main/resources/login.json`)
```json
{
  "username": "test_user",
  "password": "test_pass"
}
```

### 运行测试

#### 1. 运行完整测试套件
```bash
# 命令行运行
mvn -Dfile.encoding=UTF-8 test

# 或使用IDE
# 右键 testng.xml -> Run As -> TestNG Suite
```

#### 2. 运行单个测试类
```bash
mvn -Dtest=Application.TestCase.LoanMarket.TestCase.Zy.Loan_CreditTest test
```

#### 3. 运行数据工厂流程
```bash
# 运行默认流程
mvn -Dfile.encoding=UTF-8 exec:java

# 指定特定流程
mvn exec:java -Dexec.mainClass="Application.DataFactory.LoanMarket.XYJH5Process"
```

#### 4. 并发测试
```java
// 在数据工厂中使用并发
ConcurrentHandler.runConcurrent(
    () -> H5process("test_user", H5ProcessStage.LOAN_APPLY),
    10  // 并发数
);
```

## 📝 开发指南

### 1. 定义接口控制器

```java
@Service
public class UserController extends ApiBase {

    @Mapping(sub_path = "/user/login", requestType = "POST")
    @Header(name = "Content-Type", value = "application/json")
    public String login(@Body UserLoginRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/user/{id}", requestType = "GET")
    public String getUser(@PathParam("id") String userId) {
        return http(new BaseRo());
    }
}
```

### 2. 创建请求对象

```java
@Data
public class UserLoginRo {
    @Required
    private String username;

    @Required
    private String password;

    private String deviceId;
}
```

### 3. 编写数据工厂

```java
public class UserProcess {
    public static String loginProcess(String username, String password) {
        UserLoginRo ro = new UserLoginRo();
        ro.setUsername(username);
        ro.setPassword(password);

        UserController controller = new UserController();
        return controller.login(ro);
    }
}
```

### 4. 创建测试用例

```java
public class UserLoginTest extends BaseTestCase {

    @Test
    public void testUserLogin() {
        // 准备测试数据
        String username = "test_user";
        String password = "test123";

        // 执行测试
        String result = UserProcess.loginProcess(username, password);

        // 验证结果
        assertContains(result, "success");
        assertEq(getJsonValue(result, "$.code"), "200");
    }
}
```

## 🔧 核心注解

| 注解 | 用途 | 示例 |
|------|------|------|
| `@Mapping` | HTTP方法和路径映射 | `@Mapping(sub_path="/api/test", requestType="POST")` |
| `@Header` | 请求头设置 | `@Header(name="Authorization", value="Bearer token")` |
| `@Param` | URL查询参数 | `@Param(name="page", required=true)` |
| `@PathParam` | 路径参数 | `@PathParam("userId")` |
| `@Body` | 请求体 | `@Body` |
| `@Required` | 参数必填校验 | `@Required` |
| `@DataFactory` | 数据工厂注入 | `@DataFactory(className="UserFactory", method="createUser")` |
| `@Dependence` | 依赖注入 | `@Dependence(step="login", field="token")` |

## 📊 报表系统

### 报告位置
- **HTML报告**: `test-output/html/index.html`
- **XML报告**: `test-output/testng-results.xml`
- **日志文件**: 根据`log4j.properties`配置输出

### 报告内容
- 测试用例执行结果统计
- 失败用例详细错误信息
- 执行时间分析
- 截图和日志附件（如配置）

## 🔧 配置详解

### 环境配置 (`host.properties`)
```properties
# 测试环境
test.LoanMarket=http://loan-market.test.example.com/
test.BlackShark=http://black-shark.test.example.com/

# 预发环境
stage.LoanMarket=http://loan-market.stage.example.com/
stage.BlackShark=http://black-shark.stage.example.com/
```

### 框架配置 (`configuration.properties`)
```properties
# 超时设置
request.timeout=30000
connect.timeout=10000

# 重试机制
retry.count=3
retry.interval=1000

# 并发设置
thread.pool.size=10
```

## 🐛 故障排除

### 常见问题

| 问题 | 解决方案 |
|------|----------|
| **测试不执行** | 检查`testng.xml`配置，确保监听器已注册 |
| **连接超时** | 验证`host.properties`中的URL是否可达 |
| **401未授权** | 检查`login.json`中的认证信息是否正确 |
| **数据库连接失败** | 验证`jdbc.properties`配置和数据库服务状态 |
| **报表未生成** | 确认使用TestNG Suite方式执行，检查文件权限 |

### 调试技巧

1. **启用详细日志**
   ```properties
   # log4j.properties
   log4j.rootLogger=DEBUG, stdout, file
   ```

2. **单个用例调试**
   ```bash
   mvn -Dtest=MyTestClass#myTestMethod test
   ```

3. **跳过测试**
   ```bash
   mvn -DskipTests compile
   ```

## 📈 性能优化

### 并发测试
```java
// 使用ConcurrentHandler进行并发测试
ConcurrentHandler.runConcurrent(
    () -> processLoanApplication(userId),
    50,  // 并发线程数
    60000 // 超时时间(ms)
);
```

### 异步轮询
```java
// 使用PollingHandler轮询异步结果
String result = PollingHandler.poll(
    () -> checkLoanStatus(loanId),
    30000,  // 超时30秒
    2000    // 每2秒轮询一次
);
```

## 📚 相关资源

- [TestNG 官方文档](https://testng.org/doc/)
- [Apache HttpClient 文档](https://hc.apache.org/httpcomponents-client-ga/)
- [FastJSON 文档](https://github.com/alibaba/fastjson/wiki)
- [Maven 官方文档](https://maven.apache.org/guides/)

## 🤝 贡献指南

1. Fork 项目
2. 创建功能分支 (`git checkout -b feature/amazing-feature`)
3. 提交更改 (`git commit -m 'Add some amazing feature'`)
4. 推送到分支 (`git push origin feature/amazing-feature`)
5. 创建 Pull Request

## 📄 许可证

本项目采用内部使用许可 - 详见 [LICENSE](LICENSE) 文件

---

**ApiAuto** - 让接口自动化测试更简单、更高效！ 🚀
