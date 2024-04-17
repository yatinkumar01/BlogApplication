# Blog Application

This is a simple blog application implemented using Spring Boot for the backend.

## Features

- **Categories**: CRUD operations for managing blog categories.
- **Posts**: CRUD operations for managing blog posts.
- **Comments**: CRUD operations for managing comments on blog posts.
- **Users**: User registration, login, and retrieval operations with JWT token-based security.

## Endpoints

### Categories

- `POST /api/category/{userID}`: Create a new category.
- `GET /api/category/{categoryID}`: Get a category by ID.
- `PUT /api/category/{categoryID}`: Update a category.
- `DELETE /api/category/{categoryID}`: Delete a category.
- `GET /api/category`: Get all categories.

### Posts

- `POST /api/posts/create/{userId}/{categoryId}`: Create a new post.
- `DELETE /api/posts/delete/{postID}`: Delete a post.
- `GET /api/posts/get/{postID}`: Get a post by ID.
- `PUT /api/posts/update/{postID}`: Update a post.
- `GET /api/posts/getAll`: Get all posts.

### Comments

- `POST /api/comments/{userID}/{postID}`: Create a new comment.
- `DELETE /api/comments/{commentID}`: Delete a comment.
- `PATCH /api/comments/{commentID}`: Update a comment.

### Users

- `POST /api/users/register`: Register a new user.
- `POST /api/users/login`: Log in a user and obtain a JWT token.
- `GET /api/users/getUsers/{email}`: Get a user by email.
- `GET /api/users/AllUsers`: Get all users.

## Setup

1. Clone the repository.
2. Configure the database settings in `application.properties`.
3. Build and run the application.

## Dependencies

- Spring Boot
- Spring Data JPA
- Spring Security with JWT
- H2 Database (for development)
- MySQL (for production)

## Usage

1. Use Postman or any REST client to make requests to the endpoints.
2. Register a user using the `/api/users/register` endpoint.
3. Log in using the `/api/users/login` endpoint to obtain a JWT token.
4. Include the obtained token in the `Authorization` header for accessing protected endpoints (`Bearer <token>`).

## Contributors

- Yatin Kumar
