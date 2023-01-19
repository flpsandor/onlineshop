# ITBC-Logger
Online store Springboot REST API with MongoDB database and JWT authorization.

## Business Requirements, MVP (Minimum Viable Product)
## Unregister User
- User can see all product
- User can categorise products, by id, name, and category
- User can access the register endpoint
- User can add product in shopping cart

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
  - Response body:
    ```json
    {
      "token": "string"
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
  - Response body:
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
  - Responses:
    - 200 - OK
    - 404 - No products

2. All categories
  - HTTP Method: 'GET'
  - Endpoint URL: '/api/product/category/all'
  - Response body:
      ```json
      {
          "id": "string",
          "name": "string"
       }
       ```
  - Responses:
      - 200 - OK
      - 404 - No categories

3. Find product by id
  - HTTP Method: 'GET'
  - Endpoint URL: '/api/product/find-by-id?id'
    Requestparam:
      - id - productid
  - Response body:
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
  - Responses:
    - 200 - OK
    - 404 - No product

4. Find product by name
  - HTTP Method: 'GET'
  - Endpoint URL: '/api/product/find-by-name?name'
  - Requestparam:
      - id - productName
  - Response body:
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
  - Responses:
    - 200 - OK
    - 404 - No product

5. Find product by categoryId
  - HTTP Method: 'GET'
  - Endpoint URL: '/api/product/product/all/category?id'
  - Requestparam:
      - id - categoryId
  - Response body:
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
  - Responses:
    - 200 - Ok
     - 404 - No product


6. Add product
  - HTTP Method: 'POST'
  - Endpoint URL: '/api/seller/product/add'
  - Request body:
    ```json
    {
       "name": "string",
       "description": "string",
       "price": "double",
       "category":"string",
       "stock": "integer"
       
    }
    ```
  - Request headers:
     - 'Authorization' - token (seller)
- Response body:
   ```json
    {
       "id" : "string",
       "name": "string",
       "description": "string",
       "price": "double",
       "category":"string",
       "stock": "integer"
    }
    ```
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
       "name": "string"
    }
    ```
  - Request headers:
     - 'Authorization' - token (seller)
    - Response body:
    ```json
    {
       "id" : "string",
       "name": "string"
    }
    ```
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
     - 'Authorization' - token (seller)
- Response body:
  ```json
   {
      "id" : "string",
      "name": "string",
      "description": "string",
      "price": "double",
      "category":"string",
      "stock": "integer"
   }
   ```
- Responses:
   - 201 - Created
   - 401 - Unauthorization
      -Incorrect token
  - 404
      - No product
9. Update product
  - HTTP Method: 'PATCH'
  - Endpoint URL: '/api/seller/product/update'
    - Request body:
    ```json
      {
         "name": "string",
         "description": "string",
         "price": "double",
         "category":"string",
         "stock": "integer"
       }
    ```
  - Request param:
    - id - productId
  - Request headers:
     - 'Authorization' - token (seller)
- Response body:
   ```json
    {
       "id" : "string",
       "name": "string",
       "description": "string",
       "price": "double",
       "category":"string",
       "stock": "integer"
    }
    ```
- Responses:
   - 201 - Created
   - 401 
     - Unauthorization
     - Incorrect token
   - 404 - No product 

10. Delete product
  - HTTP Method: 'DELETE'
  - Endpoint URL: '/api/seller/product/delete'
  - Request params:
    - id - productId
  - Request headers:
    - 'Authorization' - token (seller)
  - Responses:
    - 204 - No content
    - 401 - Unauthorized 
      - Incorrect token
    - 404
      - No product

11. Delete category
  - HTTP Method: 'DELETE'
  - Endpoint URL: '/api/seller/category/delete'
  - Request params:
    - id - categoryId
  - Request headers:
    - 'Authorization' - token (seller)
  - Responses:
    - 204 - No content
    - 401 - Unauthorized 
      - Incorrect token
    - 404
      - No product

12. Delete user
- HTTP Method: 'DELETE'
- Endpoint URL: '/api/admin/user/delete'
- Request params:
    - id - userId
- Request headers:
    - 'Authorization' - token (admin token)
- Responses:
    - 204 - No content
    - 401 - Unauthorized
        - Incorrect token

12. Set user type
- HTTP Method: 'POST'
- Endpoint URL: '/api/admin/user/type'
- Request params:
    - id - userId
    - type - UserType (enum)
- Request headers:
    - 'Authorization' - token (admin token)
- Response body:
   ```json
    {
       "id" : "string",
       "firstName": "string",
       "lastName": "string",
       "email": "string",
       "type":"string"
    }
    ```
- Responses:
    - 201 - Created
    - 401 
      - Unauthorized
      - Incorrect token
    - 404 - No user

13. Add address information to user
- HTTP Method: 'POST'
- Endpoint URL: '/api/user/add-address'
    - Request body:
  ```json
    {
       "street": "string",
       "city": "string",
       "cityCode": "double",
       "state":"string"
     }
  ```
- Request param:
    - id - userId
- Request headers:
    - 'Authorization' - token (user)
  - Response body:
  ```json
    {
       "id": "string",
       "firstName": "string",
       "lastName": "string",
       "email": "double",
       "type":"string",
       "street" : "string",
       "city": "string",
       "cityCode": "string",
       "state" : "string"
     }
  ```
- Responses:
    - 201 - Created
    - 401 
      - Unauthorization 
      - -Incorrect token
    - 404 - User not exist

14. User information
- HTTP Method: 'GET'
- Endpoint URL: '/api/user/info'
    - Response body:
  ```json
    {
       "id": "string",
       "firstName": "string",
       "lastName": "string",
       "email": "double",
       "type":"string",
       "street" : "string",
       "city": "string",
       "cityCode": "string",
       "state" : "string"
     }
  ```
- Request param:
    - id - userId
- Request headers:
    - 'Authorization' - token (user)
- Responses:
    - 200 - OK
    - 401 
      - Unauthorization
      - Incorrect token
    - 404 - User not exist

15. Delete user
- HTTP Method: 'DELETE'
- Endpoint URL: '/api/user/delete'
- Request params:
    - id - userId
- Request headers:
    - 'Authorization' - token (user token)
- Responses:
    - 204 - No content
    - 401 
       - Unauthorized
        - Incorrect token
    - 404 - No user

16. Update user information
- HTTP Method: 'POST'
- Endpoint URL: '/api/user/update'
    - Request body:
  ```json
    {
       "firstName": "string",
       "lastName": "string",
       "email": "double",
       "type":"string",
       "street" : "string",
       "city": "string",
       "cityCode": "string",
       "state" : "string"
     }
  ```
- Request headers:
  - 'Authorization' - token (user token)
- Response body:
  ```json
    {
       "id": "string",
       "firstName": "string",
       "lastName": "string",
       "email": "double",
       "type":"string",
       "street" : "string",
       "city": "string",
       "cityCode": "string",
       "state" : "string"
     }
  ```
- Responses:
    - 201 - Created
    - 401 
      - Unauthorized
      - Incorrect token
    - 404 - No user

17. Change password information
- HTTP Method: 'POST'
- Endpoint URL: '/api/user/update'
    - Request body:
  ```json
    {
       "password": "string",
       "passwordCheck": "string"
     }
  ```
- Request headers:
    - 'Authorization' - token (user token)
  - Response body:
  ```json
    {
       "id": "string",
       "firstName": "string",
       "lastName": "string",
       "email": "double",
       "type":"string"
     }
  ```
- Responses:
    - 202 - Accepted
    - 401
        - Unauthorized
        - Incorrect token
    - 404 - No user

18. Add product in cart
- HTTP Method: 'POST'
- Endpoint URL: '/api/cart/add'
    - Request body:
  ```json
    {
       "user": "string",
       "product": "Product",
       "count" : "Integer"
     }
  ```
- Request param:
    - id - userId (id is generated on frontend with device information, so no loggin is needed for this)
- Response body:
  ```json
    {
       "id": "string",
       "user": "string",
       "products": "Map<product, count>",
       "date": "date"
     }
  ```
- Responses:
    - 201 - Created

19. Clear cart
- HTTP Method: 'DELETE'
- Endpoint URL: '/api/cart/clear'
- Request param:
    - id - userId (id is generated on frontend with device information, so no loggin is needed for this)
- Responses:
    - 204 - No content
    - 404 - No cart

20. Create order (shopping cart service find cart by id of user, and send this data to orderservice to create order)
- HTTP Method: 'POST'
- Endpoint URL: '/api/cart/add-order'
- Request headers:
    - 'Authorization' - token (user token)
- Request param:
    - id - userId (id is generated on frontend with device information, so no loggin is needed for this)
- Request headers:
    - 'Authorization' - token (user token)
- Responses:
    - 204 - Created
    - 401
      - Unauthorized
      - Incorrect token
    - 404 
        - No User
        - No shopping cart

OrderService recive shopping cart object and token values

- Request arguments:
  ```json
    {
       "shoppingCartId": "string",
       "shoppingCartUserInformation": "string",
       "shoppingCartProducts": "double",
       "shoppingCartDate":"string"
     }
  ```
    - 'Authorization' - token (user token)
- Responsebody:
  ```json
    {
       "id": "string",
       "user": "User",
       "address": "Address",
       "status":"OrderStatus (enum)",
       "price": "double",
       "dateTime" : "LocaldateTime",
       "products": "Map<product, count>"
     }
  ```

21. Order status change
- HTTP Method: 'POST'
- Endpoint URL: '/api/seller/order/status'
- Request param:
 - id - orderId
 - status - orderstatus (enum)
- Request headers:
    - 'Authorization' - token (seller token)
- Responsebody:
```json
  {
     "id": "string",
     "user": "User",
     "address": "Address",
     "status":"OrderStatus (enum)",
     "price": "double",
     "dateTime" : "LocaldateTime",
     "products": "Map<product, count>"
   }
```
- Responses:
    - 201 - Created
    - 401
        - Unauthorized
        - Incorrect token
    - 404 - No order

22. Delete order
- HTTP Method: 'DELETE'
- Endpoint URL: '/api/seller/order/delete'
- Request params:
    - id - orderId
- Request headers:
    - 'Authorization' - token (seller token)
- Responses:
    - 204 - No content
    - 401 
      - Unauthorized
      - Incorrect token
    - 404 
     - No user

23. Get all orders
- Http Method: 'GET'
- Endpoint URL: '/api/seller/order/all'
- Request header:
  - 'Authorization' - token (seller token)
- Responsebody:
```json
[
  {
     "id": "string",
     "user": "User",
     "address": "Address",
     "status":"OrderStatus (enum)",
     "price": "double",
     "dateTime" : "LocaldateTime",
     "products": "Map<product, count>"
   }
]
```
- Responses:
  - 200 - OK 
- 401 - Unauthorized
  - Incorrect token
  - 404 

24. Get order info
- Http Method: 'GET'
- Endpoint URL: '/api/seller/order/info'
- Request params:
    - id - orderId
- Request headers:
    - 'Authorization' - token (seller token)
- Responsebody:
```json
  {
     "id": "string",
     "user": "User",
     "address": "Address",
     "status":"OrderStatus (enum)",
     "price": "double",
     "dateTime" : "LocaldateTime",
     "products": "Map<product, count>"
   }
```
- Responses:
    - 200 - Ok
    - 401
        - Unauthorized
        - Incorrect token
    - 404
      - No order