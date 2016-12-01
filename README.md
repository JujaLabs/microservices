Test travis
Microservices for juja online training cources

## How to contribute
Fork repository, make changes, send us a pull request. We will review your changes and apply them to 'master' branch.
Before sending us your pull request please run full Maven build:  
install simple-slack-api dependency from another microservice to your local repository:

```
$ cd simple-slack-api
$ gradle clean install
$ cd ../
```
and maven full build:

```
$ mvn clean install -Pqulice
```
[travis-ci.org](http://www.travis-ci.org)<br/>
[![Build Status](https://travis-ci.org/JuniorsJava/microservices.svg?branch=master)](https://travis-ci.org/JuniorsJava/microservices)

# User storie description
You can find functional requirements in user stories (US) format by the [reference](https://docs.google.com/spreadsheets/d/1WaUEXPRBYdqgN5GBG46V4_gQ350cn4P7EfobnORq_vc/edit#gid=0)
