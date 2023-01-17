# ITBC-Logger
Online store Springboot REST API with MongoDB database and JWT authorization.

## Business Requirements, MVP (Minimum Viable Product)
## Unregister User
- User can see all product
- User can categorise products, by id, name, and category
- User can access the register endpoint

## User
- User can create orders
- User can change password
- User can change his address information
- User can see his orders

## Seller
- Seller can add categories
- Seller can add products
- Seller can edit product
- Seller can edit current product stock
- Seller can delete categories
- Seller can delete products
- Seller can modify order status
- Seller can see all orders from users
- Seller can se user information

## Admin
- Admin can delete user
- Admin can set user role

## Endpoints

1. Register
  - HTTP Method: 'POST'
  - Enpoint URL: '/api/v1/auth/register'
  - Request body:
    ```json
    {
      "firstname":"string",
      "lastname":"string",
      "email":"string",
      "password":"string",
      "passwordCheck":"string"
    }
    ```
  - Responses:
    - 201 - Registered
    - 400 - Bad Request
      - email must be valid
      - firstName, lastName at least 3 characters
      - password at least 8 characters and one letter and one number and one special
    - 409 - Conflict
        - email already exist
        - password check failed

2. All products
  - HTTP Method: 'GET'
  - Endpoint URL: '/api/product/all'
    Responses:
      - 200 - OK
      ```json
      {
          "id": "string",
          "name": "string",
          "description": "string",
          "price": "double",
          "category": {
            "categoryId": "string",
            "categoryName": "string"
          },
          "stock": "integer"
       }
      ```
      - 404 - No products

2. All categories
  - HTTP Method: 'GET'
  - Endpoint URL: '/api/product/category/all'
    Responses:
      - 200 - OK
      ```json
      {
          "id": "string",
          "name": "string",
       }
      ```
      - 404 - No categories

3. Find product by id
  - HTTP Method: 'GET'
  - Endpoint URL: '/api/product/find-by-id?id'
    Requestparam:
      - id - productid
    Responses:
      - 200 - OK
      ```json
      {
          "id": "string",
          "name": "string",
          "description": "string",
          "price":"double",
          "category": {
            "categoryId": "string",
            "categoryName": "string"
          },
          "stock": "integer"
       }
      ```
      - 404 - No product

4. Find product by name
  - HTTP Method: 'GET'
  - Endpoint URL: '/api/product/find-by-name?name'
    Requestparam:
      - id - productName
    Responses:
      - 200 - OK
      ```json
      {
          "id": "string",
          "name": "string",
          "description": "string",
          "price": "double",
          "category": {
            "categoryId": "string",
            "categoryName": "string"
          },
          "stock": "integer"
       }
      ```
      - 404 - No product

5. Find product by categoryId
  - HTTP Method: 'GET'
  - Endpoint URL: '/api/product/product/all/category?id'
  - Requestparam:
      - id - categoryId
    Responses:
      - 200 - OK
      ```json
      {
          "id": "string",
          "name": "string",
          "description": "string",
          "price": "double",
          "category": {
            "categoryId": "string",
            "categoryName": "string"
          },
          "stock": "integer"
       }
      ```
      - 404 - No product


6. Add product
  - HTTP Method: 'POST'
  - Endpoint URL: '/api/seller/product/add'
  - Request body:
    ```json
    {
        {
          "name": "string",
          "description": "string",
          "price": "double",
          "category":"string"
          "stock": "integer"
       }
    }
    ```
  - Request headers:
     - 'Authorization' - token
  - Responses:
     - 201 - Created
     - 401 - Unauthorization
        -Incorrect token

7. Add category
  - HTTP Method: 'POST'
  - Endpoint URL: '/api/seller/category/add'
  - Request body:
    ```json
    {
        {
          "name": "string",
       }
    }
    ```
  - Request headers:
     - 'Authorization' - token
  - Responses:
     - 201 - Created
     - 401 - Unauthorization
        -Incorrect token

8. Add product stock
  - HTTP Method: 'PUT'
  - Endpoint URL: '/api/seller/category/add'
  - Request param:
    - id - productId
    - num - stock to add
  - Request headers:
     - 'Authorization' - token
  - Responses:
     - 201 - Created
     - 401 - Unauthorization
        -Incorrect token

9. Update product
  - HTTP Method: 'PATCH'
  - Endpoint URL: '/api/seller/product/update'
    - Request body:
    ```json
    {
        {
          "name": "string",
          "description": "string",
          "price": "double",
          "category":"string"
          "stock": "integer"
       }
    }
    ```
  - Request param:
    - id - productId
  - Request headers:
     - 'Authorization' - token
  - Responses:
     - 201 - Created
     - 401 - Unauthorization
        -Incorrect token

10. Delete product
  - HTTP Method: 'DELETE'
  - Endpoint URL: '/api/seller/product/delete'
  - Request params:
    - id - productId
  - Request headers:
    - 'Authorization' - token
  - Responses:
    - 204 - No content
    - 401 - Unauthorized 
      - Incorrect token

11. Delete category
  - HTTP Method: 'DELETE'
  - Endpoint URL: '/api/seller/category/delete'
  - Request params:
    - id - categoryId
  - Request headers:
    - 'Authorization' - token
  - Responses:
    - 204 - No content
    - 401 - Unauthorized 
      - Incorrect token
