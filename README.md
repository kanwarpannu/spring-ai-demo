This project is intended to be introduction to AI using spring ai libraries.

This project uses in-memory storage to store past chats and feeds them as system prompt for future chats. (I tried to insert past chats as message prompts but response wasn't that good.)
Also chatClient is being used instead of chatModel to provide a level of abstraction which makes it easier to config for all chat models.
The current project was tested using llama3.1 running locally on ollama.

The Spring AI, while writing this code, was still in milestone version 1.0.0-M2. No public release version was available at the time.

To build the project: `./mvnw clean install`

Pending:  
1. RAG - code written but output is garbage even with postgres and chromadb using ollama llama3.1 embedding
2. Function calling external data sources  ---- get from here https://en.wikipedia.org/w/api.php?action=help&modules=opensearch eg. https://en.wikipedia.org/w/api.php?action=opensearch&profile=classic&search=pokemon   
3. Write evaluation testing `https://docs.spring.io/spring-ai/reference/api/testing.html`
4. Dockerization of entire project including ollama