This project is intended to be introduction to AI using spring ai libraries.

This project uses in-memory storage to store past chats and feeds them as system prompt for future chats.
Also chatClient is being used instead of chatModel to provide a level of abstraction which makes it easier to config for all chat models.
The current project was tested using llama3.1 and mxbai-embed-large models running locally on ollama.

The project exposes swagger endpoint on [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
1. ChatController for general chatbot functionality
2. TextReaderController for basic RAG capability(It uses GNU license in resources as input and in-memory vector store)
3. WikiController which uses wikipedia for information retrival

To build the project: `./mvnw clean install`

Pending:  
1. Dockerization of entire project including ollama