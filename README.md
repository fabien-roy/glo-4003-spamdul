# SPAMD-UL

**This is our project for course GLO-4003 at Laval University. We are team 4 : L'inquisition espagnole.**

**Requests supported by SPAMD-UL are specified on [this project's GitHub Pages](https://glo4003ul.github.io/a20-eq4/).**

**Our project is hosted on [https://glo4003-a20-eq4.herokuapp.com (TODO : Add link to deployed app)](#).**

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

The app will be running on [http://localhost:8080](http://localhost:8080).

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
mvn git-code-format:format-code
```

### API documentation generation

As said above, all requests for this app are listed on our GitHubPages. We used RAML 1.0. To render documentation, you must install `npm` dependencies and start the script : 

```
cd /docs
npm install
npm start
```
