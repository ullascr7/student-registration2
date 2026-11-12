
FROM nginx:alpine

COPY src/main/resources/index.html /usr/share/nginx/html/index.html

EXPOSE 80
