# My-MS-Docker-Apps

**There are three folders**

* **_my-app-web-server_**
  * This is Node JS app that creates a view for the service and only hosts pages that in turn connect to Micorservice that is book-api
* **_my-book-api_**
  * This is book API microservice that does CRUD operations on book based on calls made by UI
* **_my-ms-app-docker-connector_**
  * This holds a compose file that can be used to deploy above two services in docker and compose them as a service

**I will also be using minikube to deploy above on my local kubernates**

Kube commands coming soon

**I will finalling deploying these microservices on GCP**

GCP commands coming soon
