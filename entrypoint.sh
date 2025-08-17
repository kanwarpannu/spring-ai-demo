#!/usr/bin/env bash
set -euo pipefail

# 1️⃣ Start Ollama in the background
echo "Starting Ollama..."
ollama serve &

# 2️⃣ Wait until Ollama is ready
echo "Waiting for Ollama to be ready..."
until curl -s http://localhost:11434/api/tags > /dev/null; do
  sleep 1
done
echo "Ollama is ready."

# 3️⃣ Start the Spring Boot application
echo "Starting Spring Boot app..."
exec java -jar /opt/app/app.jar