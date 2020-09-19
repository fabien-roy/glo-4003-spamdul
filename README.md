# SPAMD-UL

**This is our project for course GLO-4003 at Laval University. We are team 4 : L'inquisition espagnol.**

**Requests supported by SPAMD-UL are specified on [this project's GitHub Pages (TODO : Add link to GitHub Pages)](#).**

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

The app will be running on [http://localhost:4567 (TODO : Verify if this link is good)](http://localhost:4567).

### Run tests

Tests are located in `src/test/java/ca/ulaval/glo4003`. They are all checked pre-commit and during the CI pipeline. Coverage report are generated using Jacoco during the report built phase.

To run unit tests, use :

```
mvn test
```

### Apply code style

Code style is verified at compilation. To apply [Google Java Code Style](https://google.github.io/styleguide/javaguide.html) throughout the source code, use : 

```
mvn git-code-format:format-code -DglobPattern=**/*
```

### API documentation generation

**TODO : This might not be used**

As said above, all requests for this app are listed on our GitHubPages. We used RAML 1.0. To render documentation, you must install `npm` dependencies and start the script : 

```
cd /docs
npm install
npm start
```
