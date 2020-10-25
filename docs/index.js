#!/usr/bin/env node

'use strict';

const raml2html = require('raml2html');
const path = require('path');
const fs = require('fs');

const ramlFile = path.join(__dirname, 'api.raml');
// This style is copied from the default raml2html style, but was made dark theme because it is
// our duty as developers to fight against all light themes.
// All rights to https://github.com/raml2html/default-theme
const config = raml2html.getConfigForTemplate('./themes/dark-theme/index.nunjucks');

const logError = (error) => console.log('error : ' + error);

raml2html.render(ramlFile, config).then(
  result => {
    let htmlFile = path.join(__dirname, 'index.html');
    fs.writeFile(htmlFile, result, error => {
      if (error) logError(error);
    })
  },
  error => {
    logError(error);
  }
);

