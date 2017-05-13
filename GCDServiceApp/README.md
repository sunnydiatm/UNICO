Following Technologies have been used to build this solution:

Wildfly(JBoss) Application Server - Version 10.1.0.Final
MySQL Server : 5.6.34-log MySQL Community Server (GPL)
JAX-WS(Soap) : Apache CXF (Default runtime for wildfly)
JAX-RS(Rest) : RestEasy ( Default runtime for wildfly)
CDI : DI Framework used for main app
Spring : DI Framework used for Unit Testing
Persistence : JPA provided by Hibernate
JMS : ActiveMQ (Part of wildfly AS)
Maven : for build integration.
Postman : For testing Rest Services
SoapUI : For testing Soap Services.
JUnit : Unit Test Framework
Mockito : Mocking framework

Project structure was generated using mvn archtype:generate

Pre-requisites for building and deploying of this application:
1) MySQL database should be installed and started
2) Create a datasource with jndi name java:/jdbc/mysqlds in wildfly (modify standalone-full.xml to add a data source)
3) Run the db.sql srcipt present in the sql directory in ebj module
4) Create JMS queue "queue/unicoGCDQueue" either using Wildfly Console or from CLI interface. 

Security Considerations:

1. Both SOAP and REST services are secured using application level security.
2. Security services are provided by non-invasive http interceptors.
3. Clients are required to send username and encrypted password as http headers(http headers should be "Username" = unicouser and "Password" = enrypted value of (P@ssw0rd!)).
4. Passwords are encrypted/decrypted using public/private key mechanism.
5. Public key has been uploaded to the github.
6. Application can be further secured by adding Container managed security and enabling SSL on the server.

MultiUser Environment:

Applications scales well in multi user environment. 
Each rest call is handled by a new object, while methods to handle Soap calls has been synchronised.

Username/Password (to be set in http headers)
unicouser/P@ssw0rd!
password must be encrypted and Base64 encoded before it is sent to the server.

Testing:
Application was tested using custom build java rest client/Postman and Soap UI.

URLS:
GCD SOAP Service WSDL
http://localhost:8080/GCDServiceApp-web/GCDService?wsdl

GCD SOAP Service Endpoint:
http://localhost:8080/GCDServiceApp-web/GCDService

GCD Rest Service Endpoint:
http://localhost:8080/GCDServiceApp-web/rest/gcd/push/4/12
