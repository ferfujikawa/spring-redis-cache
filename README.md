# spring-redis-cache

Demo application using distributed cache with Redis and Spring Boot

## How to run the application

Just start the containers by running the following command:

```bash
docker compose up
```

## Application Endpoints

The application exposes only 2 endpoints: a **GET endpoint to retrieve a user by ID** and a **POST endpoint to change the user's name**.

> Curl commands to access these endpoints are showing bellow.

Example to get a user with id 1:
```bash
curl -X GET http://localhost:8080/users/1
```
Example to change the name of user with id 1:
```bash
curl -i -X PUT http://localhost:8080/users/1 -H 'Content-Type: application/json' -d $'{ "name": "New Name" }'
```

## Application's Behavior

You can watch the log of application's container to see it's behavior on each endpoint call (there's a message on each repository method call to represent an access to the database when the information is not cached yet).

> Example of message outputted when repository method is called: `Called getUser()...`

Run the following command to watch container's log:
```bash
docker logs spring-redis-cache -f
```

The application cache is configured to **live for 20 seconds**. After that, the application will call the repository again and a message will be outputted. If the PUT endpoint is called, that user information will be removed from cache.

