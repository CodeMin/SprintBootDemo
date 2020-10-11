# Spring Boot Demo Project

## This is a demo project which shows how to build a Java service using Spring Boot to provide RESTful APIs. It uses below open source tech stacks:

### Database: MySQL
* Community Server: https://dev.mysql.com/downloads/mysql/
* Workbench: https://dev.mysql.com/downloads/workbench/

### Cache: Redis
* Installation: https://redis.io/download
* Command reference: https://redis.io/commands

### Message Queue
Use Rabbit MQ to handle high concurrent requests (2000 QPS).
* Install RabbitMQ on Mac
```shell script
brew install rabbitmq
```
* Enable RabbitMQ management plugin
```shell script
cd /usr/local/Cellar/rabbitmq/3.8.9_1/
sudo sbin/rabbitmq-plugins enable rabbitmq_management
```
* Add environment variable to `~/.bash_profile`
```shell script
export RABBIT_HOME="/usr/local/Cellar/rabbitmq/3.8.9_1/"
export PATH=$PATH:$RABBIT_HOME/sbin
```
`source ~/.bash_profile` to make the environment variable take effect immediately.
* Start RabbitMQ
```shell script
sudo rabbitmq-server -detached
```
Note without `sudo` you will get the error `[error] Error when reading /Users/{username}/.erlang.cookie: eacces` 
* Access RabbitMQ management portal: http://localhost:15672/
* Stop RabbitMQ
```shell script
sudo rabbitmqctl stop
```

### Unit Testing
* JUnit 5
* Mockito

### Scheduled Task
* Print the current time every 1 minute

### Aspect-Orient Programming
* Permission check to API

### Design Pattern
* Watcher pattern: if a user is created, listeners will take actions accordingly , e.g. send text message, email, etc.

### API Document: Swagger
* http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#