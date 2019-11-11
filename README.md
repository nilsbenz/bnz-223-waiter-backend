# M223 Waiter Application Backend

This is the Backend Server for an application called Waiter. It was realized with Spring Boot and uses the internal H2 Database.

The backend implements a rest api for authenticationg users and managing the different entities, further explanation in the client's readme. For requests other than registring and logging in a jwt token is required. 

These users are initially loaded into the database:

```
[
    {
        id: 1,
        username: "admin",
        password: "12",
        admin: true,
        waiter: false
    },
    {
        id: 2,
        username: "waiter",
        password: "12",
        admin: false,
        waiter: true
    }
]
```

The password is only valid when encoded with md5 before sending it to the server (the client does that automatically).

There is also some other example data loaded.

## Getting Started

First, you have to import the Gradle Projects.

Next, the server can be started when typing `./gradlew bootRun` into the console.

The Server then is started on [localhost:8080](http://localhost:8080).
