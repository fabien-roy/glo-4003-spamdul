# SPAMD-UL

[![Build](https://github.com/GLO4003UL/a20-eq4/workflows/Build/badge.svg)](https://github.com/GLO4003UL/a20-eq4/actions?query=workflow%3A%22Build%22)
[![Deploy](https://github.com/GLO4003UL/a20-eq4/workflows/Deploy/badge.svg)](https://github.com/GLO4003UL/a20-eq4/actions?query=workflow%3A%22Deploy%22)
[![Codecov](https://codecov.io/gh/GLO4003UL/a20-eq4/branch/develop/graph/badge.svg?token=1L5N0NP4T7)](https://codecov.io/gh/GLO4003UL/a20-eq4)
[![Dependabot](https://badgen.net/badge/Dependabot/enabled/green?icon=dependabot)](https://dependabot.com/)

**This is our project for course GLO-4003 at Laval University. We are team 4 : L'inquisition espagnole.**

**Requests supported by SPAMD-UL are specified on [this project's GitHub Pages](https://glo4003ul.github.io/a20-eq4/).**

**Our project is hosted on [https://glo4003-a20-eq4.herokuapp.com/api](https://glo4003-a20-eq4.herokuapp.com/api).**

## Project setup

### Install dependencies and build project

Maven is used as a built automation tool, as well as a dependency manager. To build the application, use : 

```
mvn clean install
```

### Execute app

To execute the application, use : 

```
mvn exec:java
```

The app will be running on [http://localhost:8080/api](http://localhost:8080/api).

### Run tests

Tests are located in `src/test/java/ca/ulaval/glo4003`. They are all checked pre-commit and during the CI pipeline. Coverage report are generated using Jacoco during the report built phase.

To run unit tests, use :

```
mvn test
```

### Report code coverage

To report code coverage after testing the app, use : 

```
mvn jacoco:report
```

This will generate `target/site/jacoco/index.html`, which can be opened in any browser.

### Apply code style

Code style is verified at each commit. To apply [Google Java Code Style](https://google.github.io/styleguide/javaguide.html) throughout the source code, use : 

```
mvn git-code-format:format-code -Dgcf.globPattern=**/*
```

To simply check code style, use :

```
mvn git-code-format:validate-code-format -Dgcf.globPattern=**/*
```

### API documentation generation

As said above, all requests for this app are listed on our GitHubPages. We used RAML 1.0. To render documentation, you must install `npm` dependencies and start the script : 

```
cd /docs
npm install
npm start
```
