# ParaBank Selenium automation

This project uses Java 17, Maven, Selenium 4, TestNG, Cucumber, the Page Object
Model, and Allure. Each Cucumber scenario gets its own WebDriver session, so
scenarios can be selected and run independently. Registration is one scenario
(not a scenario outline) and creates one uniquely named test customer per run.

## Configure

1. Install Java 17+ and Maven, then make Chrome, Firefox, or Edge available.
   Selenium Manager resolves the matching browser driver automatically.
2. Set the ParaBank customer's password in `PARABANK_PASSWORD` or override it
   with `-Dpassword=...`. The username and all test data are in
   `src/test/resources/config.properties`; system properties take precedence,
   then environment variables, then the properties file.
3. Choose `browser` and `headless` in `config.properties`, or override them
   with `-Dbrowser=firefox -Dheadless=true`.

Do not store real account passwords in source control. The password property is
intentionally an environment-variable placeholder.

## Run

```powershell
mvn test
mvn test "-Dcucumber.filter.tags=@smoke and not @mutating"
mvn test "-Dcucumber.filter.tags=@registration"
```

To run a clean test execution and then start the Allure report server, use:

```powershell
.\run-tests-with-report.ps1
```

The script starts the report even when tests fail, so you can inspect the
execution summary and each scenario's status. It then returns the Maven test
exit code so failures are still visible to command-line or CI callers. Set
`PARABANK_PASSWORD` before running when the scenarios need authenticated access.
Pass Maven options to select scenarios, for example:

```powershell
.\run-tests-with-report.ps1 "-Dcucumber.filter.tags=@negative"
```

Use the feature tags to run individual areas, for example `@negative`,
`@mutating`, or `@smoke`. Mutating scenarios perform account registration,
transfers, bill payments, account opening, profile updates, and loan requests
on the configured ParaBank test account.

Allure raw results are written to `target/allure-results`. To view a report
after running tests manually, use the configured Maven plugin:

```powershell
mvn allure:serve
```

The report dashboard shows aggregate execution results and scenario/test-case
statuses. A static report can be generated with `mvn allure:report` and is
written to `target/site/allure-maven-plugin`.
