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

<h2>Configuration</h2>
<p>Configure authentication with Asgardeo Identity Provider in <code>application.properties</code>.<br>

<p><strong>Navigate to application.properties:</strong><br>
Open the <code>application.properties</code> file located at <code>ServiceReservation\src\main\resource\</code>.</p>

<p><strong>Add Asgardeo Client ID and Client Secret:</strong><br>
In the <code>application.properties</code> file, find the following lines:<br>
<code>spring.security.oauth2.client.registration.asgardeo.client-id=</code><br>
<code>spring.security.oauth2.client.registration.asgardeo.client-secret=</code><br>
Obtain your Client ID and Client Secret from the Asgardeo admin panel and replace the empty values in the properties file with the obtained credentials.</p>

<p><strong>Set Issuer URL:</strong><br>
In the Asgardeo admin panel, go to the Info tab and copy the Issuer URL. Paste it at the end of the following line in <code>application.properties</code>:<br>
<code>spring.security.oauth2.client.provider.asgardeo.issuer-uri=</code></p>

<p><strong>Configure Authorized Redirect URLs:</strong><br>
In the Asgardeo admin panel, navigate to your application's Protocol tab. Locate the Authorized Redirect URLs textbox. Depending on your development environment:</p>

<ol>
  <li>If you're using IntelliJ or a similar IDE, add these URLs:<br>
    <code>http://localhost:8080/</code><br>
    <code>http://localhost:8080/login/oauth2/code/asgardeo</code></li>
  <li>If you're using Tomcat or another server application, add URLs in this format:<br>
    <code>http://localhost:8080/Service-Reservation/</code><br>
    <code>http://localhost:8080/Service-Reservation/login/oauth2/code/asgardeo</code></li>
</ol>

<p>Ensure that you have the correct URL pattern; the default opening URL (index address) and address+/login/oauth2/code/asgardeo need to be added. Failure to configure these correctly might result in an error stating "Redirect URL not allowed."</p>

<p><strong>Configure Allowed Origins:</strong><br>
Add the following allowed origins in the Asgardeo admin panel:<br>
<code>http://localhost:8080</code></p>

<p><strong>Configure User Attributes:</strong><br>
Navigate to the Asgardeo admin panel, and select the user attributes <em>phone</em> and <em>country(in address)</em> if they are not already selected. Ensure to update the scope in <code>application.properties</code> accordingly if you select additional fields.</p>

<p>Add peoples to asgardeo, if you want</p>

<p><strong>Set Up SQL Database:</strong><br>
To configure your SQL database, navigate to <code>application.properties</code> and fill in the database information as shown below:</p>

<code>spring.datasource.url = jdbc:mysql://{host}:{port}/{database_name}</code><br>
<code>spring.datasource.username = </code><br>
<code>spring.datasource.password = </code>

<p>If you don't have a local MySQL database, you can use services like <a href="https://www.freesqldatabase.com/">FreeSqlDatabase</a> to create an online MySQL database easily. After configuring the database, you can run your application using your preferred IDE or server.</p>


<h2>Usage</h2>
<ol>
    <li>Clone the repository: <code>git clone https://github.com/PiyumalT/ServiceReservation.git</code></li>
    <li>Navigate to the project directory: <code>cd ServiceReservation</code></li>
    <li>Run the application: <code>./mvnw spring-boot:run</code></li>
    <li>Access the application in your web browser: <code>http://localhost:8080</code>(You can change port if you want, modify port in <code>application.properties</code>  Or </li>
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



<h2>Additional Information</h2>
<p>For more details and advanced usage, please refer to my Medium blog: <a href="https://piyumalt.medium.com/">https://piyumalt.medium.com/</a></p>


<h3>(c) 2023 <a href="https://github.com/PiyumalT/">Tharindu Piyumal</a> </h3>

Enjoy! :smile:
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------