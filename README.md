# E-Commerce Store Automation

This project contains a Java-based UI automation suite for an e-commerce shopping flow using:

- Java 21
- Maven
- TestNG
- Cucumber
- Selenium WebDriver

It is configured to run on GitHub Actions and to publish test artifacts for failed runs.

## Tech stack

- Java 21
- Maven
- TestNG
- Cucumber
- Selenium 4
- ChromeDriver via WebDriverManager

## Project structure

- `pom.xml` — Maven configuration and dependencies
- `src/test/java` — Java test code
- `src/test/resources/features` — Cucumber feature files
- `.github/workflows/ci.yml` — GitHub Actions CI pipeline
- `target/` — generated build/test artifacts

## Local execution

From the project root, run:

```powershell
cd "E:\Projects\E-Commerce Store"
mvn test
```

To run only the Cucumber runner:

```powershell
cd "E:\Projects\E-Commerce Store"
mvn test -Dtest=TestRunner
```

## GitHub CI

This project is set to run automatically on push and pull request to the following branches:

- `main`
- `master`
- `develop`

The workflow uploads:

- Cucumber HTML report
- TestNG XML/HTML reports
- failure screenshots

## Sample test flow

The current scenario logs in to SauceDemo using:

- Username: `standard_user`
- Password: `secret_sauce`

Then it verifies that the inventory page is displayed.

## Notes

- Local runs open the browser visibly for manual observation.
- GitHub Actions runs in headless mode on the runner.
- Screenshots are stored under `screenshots/` when a step fails.
