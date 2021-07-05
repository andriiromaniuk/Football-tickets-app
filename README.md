# Football tickets app
It is RESTful project with which you can manage football ticketing system.

## How to use:
1. Configure Web Server (I personally used Tomcat 9.0.46)
2. Setup MySQL Server and enter your schemas URL, USERNAME and PASSWORD in db.properties file. User must have access to schema that was given in URL.
3. Run a project

In DataInitializer class you can make your own profile (method inject()) with ADMIN/USER role with which you will be able to login on /login page.

You may use Postman to check how things work, see controller package for available requests.

## Tecnhologies used in project
- Spring - Core, MVC, Web, Security
- Hibernate Framework
- MySQL Server 8.0.25
- Maven
- Apache Tomcat 9.0.46
- Json
