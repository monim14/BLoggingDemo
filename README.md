# BLoggingDemo
e6data assignment


# Blogging Platform API

This project is a simple blogging platform API built with Spring Boot.

## Description

The Blogging Platform API allows users to create, read, update, and delete posts. It provides endpoints for managing posts, such as creating a new post, retrieving a post by ID, updating a post, deleting a post, and listing posts with pagination.

## Installation

To run this project locally, make sure you have Java and Maven installed. Then, follow these steps:

1. Clone the repository:
2. Navigate to the project directory:
3. Build the project using Maven:
4. Run the project:
5. Project will be running at http://localhost:9090/
6. ## Endpoints

- `POST /post`: Create a new post
  Sample Req
{
    "title": "ABC",
    "content": "Today is a fine day",
    "author": "Sam"
}

- `GET /post/{postId}`: Retrieve a post by ID
- `DELETE /post/delete/{postId}`: Delete a post by ID
- `PATCH /post/update/{postId}`: Update a post by ID
- `POST /post/list`: List posts with pagination
  Sample Request
{
    "pageDto": {
        "number": 0,
        "pageSize": 2
    },
    "filter": {"author": "Keats","creationDate": "2024-06-01"}

}
7. It has 4 folders:- ui,domain,application,configuration
8. ui has all the controller logic
9. domain has all the models & repository
10. configuration provides securityConfig
11. application has the business logic,its the service layer
12. Tests are available at src/test/java/com/assignment/application/TestBloggingServiceImpl.java
13. Currently only testing the service layer ,this can be extended further



