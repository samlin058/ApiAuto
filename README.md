### ApiAuto 测试框架

一个基于 Java + TestNG 的接口自动化测试框架，支持多项目/多域接口的统一建模、用例组织与数据工厂驱动式的流程编排，内置注解式接口定义、依赖数据注入、参数/结果校验、并发与异步控制、报表输出、以及多环境与数据库配置管理。

---

## 功能特性

- **注解式接口建模**：通过 `Service/Annotations` 下的注解（如 `@Mapping`、`@Header`、`@Param`、`@Body`、`@PathParam`、`@JsonArray`、`@Required`、`@FileBody` 等）描述接口、参数与校验规则。
- **数据工厂与流程编排**：`Application/DataFactory` 下按业务域（如 `LoanMarket`、`BlackShark`、`Alchemist` 等）封装可复用的步骤与场景（如 `XYJH5Process`），支持独立运行与在用例中复用。
- **依赖注入与前后置**：支持 `@Dependence(s)` 注入上一步产出到下一步请求，`Service/Annotations/AnnotationProcessor` 提供编译期/运行时处理。
- **统一基础能力**：`Service/Base` 提供请求构造、签名/加密、断言、公共前置；`Service/Util` 提供 HTTP、Excel、JSON、时间、MD5、DB、并发、轮询等工具。
- **多环境与账号配置**：`resources/host.properties`、`configuration.properties`、`login.json`、`defaultLogin.json` 管理环境/账号；`jdbc.properties` 管理数据库连接。
- **报表与监听**：集成 TestNG 与 ReportNG，`Service/Listen/TestListener` 输出执行日志与统计，报告默认在 `test-output/html`。
- **模板与代码生成**：`resources/TemplateFile/*.ftl` 与若干 POJO（如 `InterfaceGenerationModel`）可用于生成 Ao/Ro/To 结构代码与方法骨架。
- **多项目支持**：`Data/Apis` 与 `Data/Ro` 下按业务域拆分（LoanMarket/BlackShark/IndoArk/Alchemist/Goblin 等），同一框架复用。

---

## 目录结构（核心）

- `src/main/java/Application`
  - `DataFactory/<Domain>`：数据工厂与业务流程编排（例如 `LoanMarket/XYJH5Process.java`、`H5ProcessContext.java`）。
  - `DBoperation/<Domain>`：与测试数据相关的数据库操作封装（如 `LoanMarket/Partner.java`、`User.java`）。
  - `TestCase/<Domain>`：按场景/接口组织的 TestNG 用例（如 `LoanMarket/TestCase/...`）。
- `src/main/java/Data`
  - `Apis/<Domain>`：接口定义 Controller（每个方法代表一个接口、配合注解描述请求）。
  - `Ro/<Domain>`：请求/响应对象（Request Object/Response Object）。
  - `Enum`：领域枚举。
- `src/main/java/Service`
  - `Annotations`：注解定义与处理器。
  - `Base`：基础用例基类、通用 API 封装。
  - `Config`：配置初始化（如登录、环境）。
  - `Listen`：TestNG 监听器与报告集成。
  - `Pojo`：与模板/生成/请求结构相关的通用数据模型。
  - `Util`：HTTP/JSON/Excel/反射/DB/加密/并发等工具类。
- `src/main/resources`
  - `host.properties`：环境域名/Host。
  - `configuration.properties`：框架级通用配置。
  - `jdbc.properties`：数据库配置。
  - `login.json`、`defaultLogin.json`：账号/鉴权信息。
  - `openApi.json`：开放接口/路由等配置（如适用）。
  - `TemplateFile/*.ftl`：Freemarker 模板（Ao/Ro/To 生成）。
  - 若干 Excel 模板与示例数据（资产/标签等）。
- `testng.xml`：测试套件与监听器配置。
- `pom.xml`：Maven 依赖与插件（含 `exec-maven-plugin`）。

---

## 环境依赖

- JDK 1.8+
- Maven 3.6+
- 可选：MySQL（如需 DB 相关用例）、本地或可访问的目标后端环境

主要依赖：TestNG、ReportNG、Apache HttpClient、FastJSON/Gson、Jackson、POI、SnakeYAML、Hutool、MySQL 驱动、Freemarker、JavaParser 等（详见 `pom.xml`）。

---

## 配置说明

- `src/main/resources/host.properties`：配置环境地址与路由，可按键值区分不同环境（如 dev/test/stage）。
- `src/main/resources/configuration.properties`：框架级参数（如并发、超时、重试、默认环境等）。
- `src/main/resources/jdbc.properties`：数据库 URL/用户名/密码等。
- `src/main/resources/login.json` 与 `defaultLogin.json`：不同角色或系统的登录配置。
- `src/main/resources/log4j.properties`：日志级别与输出。

配置修改后建议在 IDE 或命令行重新执行测试，以确保新配置生效。

---

## 运行方式

### 1) 通过 TestNG 套件

- 在 IDE（IntelliJ IDEA/Eclipse）右键 `testng.xml` 运行；或使用 TestNG 插件运行。
- `testng.xml` 已注册监听器：`org.uncommons.reportng.HTMLReporter` 与 `Service.Listen.TestListener`。
- 默认包含的样例类：`Application.TestCase.LoanMarket.TestCase.HalfCapitalTest`（可在 `testng.xml` 中增删）。

命令行运行（若未显式配置 surefire 插件时，推荐 IDE 方式）：

```bash
mvn -Dfile.encoding=UTF-8 test
```

如需指定套件文件（需在 surefire 中配置或使用 IDE）：

```bash
mvn -Dsurefire.suiteXmlFiles=testng.xml -Dfile.encoding=UTF-8 test
```

### 2) 运行数据工厂/流程（独立 Main）

`pom.xml` 配置了 `exec-maven-plugin`，默认主类：

```
Application.DataFactory.LoanMarket.XYJH5Process
```

命令行执行：

```bash
mvn -Dfile.encoding=UTF-8 exec:java
```

如需切换为其他流程，请在 `pom.xml` 的 `mainClass` 修改为对应的流程类。

### 3) 直接运行单个测试类

- 在 IDE 中右键类（如 `Application/TestCase/LoanMarket/TestCase/Zy/Loan_CreditTest.java`）运行。
- 或使用 Maven 指定测试：

```bash
mvn -Dtest=Application.TestCase.LoanMarket.TestCase.Zy.Loan_CreditTest test
```

### 4) 报告与日志

- TestNG 与 ReportNG 报告：`test-output/html`（执行后生成）。
- 框架执行日志：依据 `log4j.properties` 输出配置。

---

## 编写用例指南（简要）

1. **定义接口（Controller）**：在 `Data/Apis/<Domain>` 下新增或维护接口类与方法。
   - 典型注解：
     - `@Mapping(method = "POST", value = "/path")`
     - `@Header(name = "Authorization", value = "Bearer ...")`
     - `@Param(name = "id", required = true)` / `@PathParam("orderId")`
     - `@Body` / `@FileBody` / `@JsonArray`
     - `@Required`（字段必填/断言用）

2. **定义请求/响应对象（Ro）**：在 `Data/Ro/<Domain>` 下维护请求与响应 POJO，字段与接口参数一一对应。

3. **编排数据与场景（DataFactory）**：在 `Application/DataFactory/<Domain>` 组织可复用步骤与完整业务流程（如注册-授信-绑卡-下单-还款）。

4. **编写测试（TestCase）**：在 `Application/TestCase/<Domain>` 下创建用例类，继承 `Service/Base/BaseTestCase` 或 `BaseTestCase2`，调用接口与/或数据工厂步骤，添加断言。

5. **数据库与外部依赖**：在 `Application/DBoperation/<Domain>` 下添加/复用 DB 操作以准备或校验测试数据；连接信息见 `jdbc.properties`。

6. **并发与轮询**：使用 `Service/Util/ConcurrentHandler` 与 `PollingHandler` 进行并发压测或异步结果轮询。

---

## 注解速览

- `@Mapping`：HTTP 方法与路径映射
- `@Header`：请求头
- `@Param` / `@PathParam`：URL 查询参数 / 路径参数
- `@Body` / `@FileBody` / `@JsonArray`：请求体（JSON/文件/数组）
- `@Required`：参数校验（为空校验/断言）
- `@DataFactory` / `@DataFactorys`：引入数据工厂步骤
- `@Dependence` / `@Dependences`：步骤输出作为下一步输入
- `@Async`：异步执行

---

## 常见问题

- **套件不生效/不执行？** 优先在 IDE 右键 `testng.xml` 运行；或在 `pom.xml` 中启用并配置 surefire 的 `suiteXmlFiles`。
- **报表未生成？** 确认监听器已在 `testng.xml` 注册，且使用 TestNG 套件方式执行。
- **连接失败或 401？** 检查 `host.properties`、`login.json` 与真实环境鉴权策略。
- **DB 相关用例失败？** 校验 `jdbc.properties`、目标库连通性与初始化数据。

---

## 许可证

内部项目或按仓库声明为准。


