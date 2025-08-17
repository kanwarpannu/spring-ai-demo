This project is intended to be introduction to AI using spring ai libraries.

This project uses in-memory storage to store past chats and feeds them as system prompt for future chats.
Also chatClient is being used instead of chatModel to provide a level of abstraction which makes it easier to config for all chat models.
The current project was tested using llama3.1 and mxbai-embed-large models running locally on ollama.

The project exposes swagger endpoint on [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
1. ChatController for general chatbot functionality
2. TextReaderController for basic RAG capability(It uses GNU license in resources as input and in-memory vector store)
3. WikiController which uses wikipedia for information retrival

# Requirements
1. Java 21 in local (For build stage)
2. Docker Desktop (For run docker stage)
3. 1 Gbps internet (slower can lead to timeout in run stage, default time is 5min in application.properties file)
4. Minimum 16GB RAM and Good CPU (higher RAM and GPU will make running app faster)

# Step 1. Build jar
To build the project (if ollama not locally installed and running on port 11434): `./mvnw clean install -DskipTests`  
OR  
To build the project (if ollama locally installed and running on port 11434): `./mvnw clean install`  

# Step 2. Build the image
`docker build -t spring-ollama:latest .`  
(Time approx 60 - 300 seconds dependent on internet speed) 

# Step 3. Run it
`docker run -d --name spring-ollama -p 8080:8080 -p 11434:11434 spring-ollama:latest`  
(Time approx 60 - 300 seconds dependent on internet speed for downloading models llama3.1 and mxbai-embed-large models) 

The Docker run command above can be modified according to local gpu, the above command uses CPU only for max compatibility. For more info try [Docker hub page for ollama](https://hub.docker.com/r/ollama/ollama)