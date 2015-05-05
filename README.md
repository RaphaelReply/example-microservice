# example-microservice
A little simple example of a microservice involving several technologies

## Running the Example

Checkout the project
Run maven with
> mvn clean package jetty:run

This deploys the example on your localhost on port 8080 using Jetty. 
To perform a call against the API you can use the a REST client to access the following URIs:

* <a href="http://localhost:8080/v0.1.0/customers">http://localhost:8080/v0.1.0/customers</a> -- **customers **
* <a href="http://localhost:8080/v0.1.0/hystrix">http://localhost:8080/v0.1.0/hystrix</a> -- ** mashup **



Or you can use CURL with the following commands:

GET
(all customers)
curl -i -X GET http://localhost:8080/v0.1.0/customers

(by Id)
curl -i -X GET http://localhost:8080/v0.1.0/customers/{id}

POST
(Enter new customer)
curl -i -H 'Content-Type: application/json' -X POST -d '{"salutation":"Herr", "firstname":"Max", "lastname":"Mustermann"}' http://localhost:8080/v0.1.0/customers/

(Partial update of the salutation)
curl -i -H 'Content-Type: application/json' -X POST -d '{"salutation":"Herr"}' http://localhost:8080/v0.1.0/customers/{id}
