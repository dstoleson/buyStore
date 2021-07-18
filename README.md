# buyStore
RESTful service for buyStore products.

###Description

The buyStore RESTful service provides a way to get product info and update product price information.

###Hosted:

The buyStore RESTful service is hosted on Heroku with the endpoint URL: https://buystore-dhs.herokuapp.com/product

###API Documentation:

Swagger/API documentation and a UI for running buyStore API examples is available at this URL:  https://buystore-dhs.herokuapp.com/swagger-ui

You can also use any REST client as well.

###Examples:

####getProductInfo

`GET /products/{id}`

Request:

`GET /products/1
Host: buystore-dhs.herokuapp.com
`
Response:

`{
"id": 1,
"name": "Product 1",
"current_price": {
"value": 2.5,
"currency_code": "USD"
}
}`

####getProductInfo error

Passing in a productId that doesn't exist will return an HTTP 404 Not Found.

Request:

`GET /products/1111
Host: buystore-dhs.herokuapp.com
`

Response:

`{
"timestamp": "2021-07-18T19:54:26.700+00:00",
"status": 404,
"error": "Not Found",
"path": "/products/1111"
}`

####updateProductInfo

`PUT /products/{id}`

Request:

`PUT /products/1 HTTP/1.1
Content-Type: application/json
Host: buystore-dhs.herokuapp.com
{
"current_price": {
"value": 6.0,
"currency_code": "EUR"
}
}`

Response:

`{
"id": 1,
"name": "Product 1",
"current_price": {
"value": 6.0,
"currency_code": "EUR"
}
}`

####updateProductInfo error

Passing in a productId that doesn't exist will return an HTTP 404 Not Found.

Request:

`PUT /products/1111 HTTP/1.1
Content-Type: application/json
Host: buystore-dhs.herokuapp.com
{
"current_price": {
"value": 6.0,
"currency_code": "EUR"
}
}`

Response:

`{
"timestamp": "2021-07-18T19:58:02.425+00:00",
"status": 404,
"error": "Not Found",
"path": "/products/1111"
}`

## Running locally

You can also run locally by running it as a Spring Boot application.  Set `spring.profile.active=local` and update `application.properties` with REDIS connection properties:

`# redis
spring.redis.database=0
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.password=password
spring.redis.timeout=60000
`

You will need to have a local REDIS instance running.  One way is to use an existing Docker image.

Directions for running REDIS using Docker:

$ docker run -p 6379:6379 -d redis:6.0 redis-server --requirepass "password"