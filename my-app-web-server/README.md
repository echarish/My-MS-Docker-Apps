# my-docker-kube-app-1

This project works on creating a microservie sample on docker and kubernates

#### Some important commands that I use are below

**Starting and Running Node App**
* _My_Book_API_MS=localhost:8080 PORT=3000 npm run watch_
* process.env.My_Book_API_MS
* process.env.PORT 


**DOCKER**

* _docker build -t echarish/my-app ._
* _docker run -p 49160:3000 -d echarish/my-app_
* _docker run -p 49160:3000 -d echarish/my-app -e My_Book_API_MS=localhost:8080_
* _docker logs <container id>_
* _docker exec -it <container id> /bin/bash_


**TO PUSH INTO GCP**
* docker tag [SOURCE_IMAGE] [HOSTNAME]/[PROJECT-ID]/[IMAGE]
  * _docker tag echarish/my-app asia.gcr.io/firstdemoproject-250304/my-app_
* _docker push asia.gcr.io/firstdemoproject-250304/my-app_

**I am using Minikube for my kubernates**

Some important commands for minikube
* _minikube start_
  * This is to start minikube
* _minikube dashboard_
  * If you like working with dashboard GUI
* _minikube tunnel_
  * If you create a service loadbalancer run above command to get external IP enabled
  
* Use 'minikube start -p <name>' to create a new cluster, or 'minikube delete' to delete this one.


Some important commands for cubectl
* _cubectl create -f <File_Name>_
  * File_Name can be your deployement.yaml or service.yaml etc



