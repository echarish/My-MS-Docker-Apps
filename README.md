# My-MS-Docker-Apps

There are three folders

* my-app-web-server
  * This is Node JS app that creates a view for the service and only hosts pages that in turn connect to Micorservice that is book-api
* my-book-api
  * This is book API microservice that does CRUD operations on book based on calls made by UI
* my-ms-app-docker-connector
  * This holds a compose file that can be used to deploy above two services in docker and compose them as a service

I will also be using minikube to deploy above on my local kubernates

I will finalling deploying these microservices on GCP
