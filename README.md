This project is intended to be introduction to AI using spring ai libraries.

This project uses in-memory storage to store past chats and feeds them as system prompt for future chats. (I tried to insert past chats as message prompts but response wasn't that good.)
Also chatClient is being used instead of chatModel to provide a level of abstraction which makes it easier to config for other models.
The current project uses llama3.1 running locally on ollama.

The Spring AI while writing this code was still in milestone version 1.0.0-M2. No public release version was available at the time.

To build the project: `./mvnw clean install`

Pending:
1. Function calling external data sources
2. RAG
3. Dockerization of entire project including ollama