<h1>Vehicle Service Reservation</h1>

<h2>Introduction</h2>
<p>This project allows users to:</p>
<ul>
    <li>Perform OIDC login</li>
    <li>Manage reservations</li>
    <li>View all reservations</li>
    <li>Delete upcoming reservations</li>
    <li>View user profile information</li>
</ul>

<h2>Technologies</h2>
<p>Backend: Spring Boot, MySQL</p>
<p>Frontend: JSP, JavaScript, Bootstrap</p>
<p>Identity Provider (IDP): Asgardeo</p>

<h2>Main Dependencies / Libraries / Frameworks</h2>
<ul>
    <li><code>spring-boot - 2.7.16</code></li>
    <li><code>Java - 1.8</code></li>
    <li><code>Dependency Management - Maven</code>
    <li><code>packaging - war</code></li>
</ul>
<ul>
    <li><b>spring-boot-starter-web:</b> Provides the basic setup for a Spring Boot web application, including RESTful APIs and MVC.</li>
    <li><b>spring-boot-devtools:</b> Provides development-time tools to improve the development experience, such as automatic restarts and LiveReload.</li>
    <li><b>spring-boot-starter-data-jpa:</b> Offers a set of convenient features for working with JPA databases.</li>
    <li><b>tomcat-embed-jasper: </b>Enables JSP support for the embedded Tomcat server.</li>
    <li><b>jstl:</b> Provides JavaServer Pages Standard Tag Library support for JSP pages.</li>
    <li><b>mysql-connector-j:</b> Connects the application to a MySQL database.</li>
    <li><b>bootstrap (WebJar):</b> Integrates Bootstrap framework into the project for responsive and visually appealing UI elements.</li>
    <li><b>jquery (WebJar):</b> Integrates jQuery library into the project for client-side scripting.</li>
    <li><b>spring-boot-starter-oauth2-client: </b>Integrates OAuth2 client support into the Spring Boot application for authentication.</li>
    <li><b>spring-boot-starter-security: </b>Provides security features for the Spring Boot application, including authentication and authorization.</li>
    <li><b>jackson-databind: </b>Handles JSON data binding for JSON serialization and deserialization.</li>
    <li><b>spring-boot-starter-tomcat: </b>Embeds the Tomcat server for running the Spring Boot application.</li>
    <li><b>thymeleaf-extras-springsecurity5: </b>Integrates Spring Security with Thymeleaf templates, allowing security-related tags and expressions in templates.</li>
</ul>


<h2>Configuration</h2>
<p>Configure authentication with Asgardeo Identity Provider in <code>application.properties</code>.<br>
You must add your <code>client-id</code> and <code>client-secret</code> in <code>application.properties</code>. You can get that from Asgardeo admin panel. Then
Add Asgardeo registered Organization to <code>issuer-uri</code> in<code>application.properties</code>.<br>
Remember to add <code>redirect-uri</code> in Asgardeo admin panel. <br>
Remember to add <code>logout-uri</code> in Asgardeo admin panel. <br><br>
Also, Configure database connection details.<br><br>
You can modify Reservation related data, such as Location and reservation time in <code>application.properties</code>
<br><br>
Complete Guide: <a href="https://piyumalt.medium.com/vehicle-service-reservation-with-spring-boot-and-asgardeo-identity-provider-oidc-1e0b0b0b0b0b">https://piyumalt.medium.com/vehicle-service-reservation-with-spring-boot-and-asgardeo-identity-provider-oidc-1e0b0b0b0b0b</a>
</p>


<h2>Usage</h2>
<ol>
    <li>Clone the repository: <code>git clone https://github.com/PiyumalT/ServiceReservation.git</code></li>
    <li>Navigate to the project directory: <code>cd ServiceReservation</code></li>
    <li>Run the application: <code>./mvnw spring-boot:run</code></li>
    <li>Access the application in your web browser: <code>http://localhost:8080</code>(You can change port if you want, modify port in <code>application.properties</code> </li>
</ol>

<p>You can change the port number or context path as per your Tomcat configuration.</p>


<h2>Installation Guide</h2>
<ol>
    <li>Download the WAR file from the repository.</li>
    <li>Copy the WAR file to your Tomcat's webapps directory. path/to/your/tomcat/webapps/</li>
</ol>

<ol start="3">
    <li>Start your Tomcat server. You can use the following command:</li>
</ol> <code>/tomcat/bin/catalina.bat run </code>

<ol start="4">
    <li>Access the application in your web browser using the following URL:</li>
</ol>
<code>http://localhost:8080/service-reservation</code>

<p>You may need to config application.properties file. After the first run of application, goto webapp directory>service-reservation>WEB-INF>classes
and follow the above config instructions to modify application.properties file. Then restart the application. </p>

<h2>Additional Information</h2>
<p>For more details and advanced usage, please refer to my Medium blog: <a href="https://piyumalt.medium.com/">https://piyumalt.medium.com/</a></p>


<h3>(c) 2023 <a href="https://github.com/PiyumalT/">Tharindu Piyumal</a> </h3>

Enjoy! :smile:
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------