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
$ mvn clean install -Pqulince
```
