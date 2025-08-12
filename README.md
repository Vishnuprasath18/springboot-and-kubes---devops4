# Java App using Kubernetes (vProfile-style)

## Explanation
- This is a tiny website made with Spring Boot (Java). It has pages like `/`, `/health`, `/profile`, `/status`.
- We pack the app into a box called a Docker image so it runs anywhere.
- We give the image to Minikube (your tiny local Kubernetes) which runs many boxes for us:
  - one box for the app, and other boxes for helpers (MySQL, Memcached, RabbitMQ, Elasticsearch).
- You open the app in your browser using a URL that Minikube gives you.

### To Run
1) Build the image (already done here): `docker build -t vprofile:local .`
2) Start Minikube: `minikube start`
3) Give Minikube your image: `minikube image load vprofile:local`
4) Tell Kubernetes to run everything: `minikube kubectl -- apply -f kubernetes/`
5) Get the app URL: `minikube service vproapp-service --url`
6) Open that URL in a browser. You’ll see the simple homepage and links to `/health`, `/profile`, `/status`.

### Change the homepage text
- Edit `src/main/java/com/example/vprofile/VprofileApplication.java` → method `home()` returns a simple HTML string.
- Rebuild and redeploy:
  - `docker build -t vprofile:local .`
  - `minikube image load vprofile:local`
  - `minikube kubectl -- rollout restart deployment vproapp-deployment`

### Push this project to your GitHub
1) Make a new empty repo on GitHub (note the URL, like `https://github.com/<you>/vprofile.git`).
2) In PowerShell inside the project folder:
```
git init
git add -A
git commit -m "Initial app: Spring Boot + Docker + Minikube"
git branch -M main
git remote add origin https://github.com/<your-username>/<your-repo>.git
git push -u origin main
```

If you get an auth popup, sign in to GitHub. If push fails, make sure the repo exists and the URL is correct.

This project is a sample Java Spring Boot application, containerized with Docker, and ready to deploy on a local Kubernetes cluster (Minikube). It mimics a real-world, multi-tier app with MySQL, Memcached, RabbitMQ, and Elasticsearch.

---

## Project Structure

```
Java app using kube/
├── src/                  # Java source code (Spring Boot)
├── Dockerfile            # For building the app image
├── kubernetes/           # All K8s manifests
│   ├── vproapp-dep.yml
│   ├── vproapp-svc.yml
│   ├── db-dep.yml
│   ├── db-svc.yml
│   ├── mc-dep.yml
│   ├── mc-svc.yml
│   ├── rmq-dep.yml
│   ├── rmq-svc.yml
│   ├── es-dep.yml
│   ├── es-svc.yml
│   └── app-secret.yml
├── README.md
└── .gitignore
```

---

## Prerequisites
- [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- [Minikube](https://minikube.sigs.k8s.io/docs/start/)
- [kubectl](https://kubernetes.io/docs/tasks/tools/)
- [Java 11+](https://adoptium.net/)
- [Maven](https://maven.apache.org/) (for building)
- [Git](https://git-scm.com/)
- [Docker Hub account](https://hub.docker.com/)

---

## 1. Build the Java App

```
mvn clean package
```

---

## 2. Build and Push Docker Image

```
docker build -t <your-dockerhub-username>/vprofile:V1 .
docker login
docker push <your-dockerhub-username>/vprofile:V1
```

---

## 3. Configure Kubernetes Manifests

- Edit `kubernetes/vproapp-dep.yml` and set the image to your Docker Hub image.
- Edit `kubernetes/app-secret.yml` to set your DB and RabbitMQ passwords (base64 encoded).
- You can adjust DB, Memcached, RabbitMQ, and Elasticsearch settings in their respective manifests.

---

## 4. Start Minikube and Deploy

```
minikube start
kubectl apply -f kubernetes/
```

---

## 5. Access the App

```
minikube service vproapp-service
```

---

## 6. Cleanup

```
kubectl delete -f kubernetes/
minikube stop
```

---

## Where to Configure Things
- **Secrets:** `kubernetes/app-secret.yml`
- **DB Settings:** `src/main/resources/application.properties`
- **Docker Image:** `kubernetes/vproapp-dep.yml`
- **Other Services:** `kubernetes/` directory

---

## Questions?
Open an issue or ask your AI assistant! 
