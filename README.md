<h2>About</h2>
<p>Build a web-application using Spring Security, Spring Web, and Spring Data JPA where authorization tokens are generated upon successful verification of One-Time-Password (OTP).</p>

<h2>Development Environment</h2>

- <b>Intellij Idea</b>
- <b>Java 21</b>
- <b>Spring Boot 3.2.5 + Maven</b>
- <b>PostgreSQL</b>

<h2>Testing via HTTP Client</h2>

- <h3>Sign-up</h3>
```http
POST http://localhost:8080/signUp
username: John Doe
password: 1234
email: johnDoe@gmail.com
```
![image](https://github.com/Ali-Hassan33/Generating_User_Authorization-Token_via_OTP/assets/101888551/13ba085e-a09a-4533-bb96-e6afa277cce5)
<hr>

- <h3>Sign-in</h3>
```http
POST http://localhost:8080/signIn
username: John Doe
password: 1234
```
<img src = "https://github.com/Ali-Hassan33/Generating_User_Authorization-Token_via_OTP/assets/101888551/c71ba5f6-ee13-47f4-b796-c59ed7f57267"> <image src = "https://github.com/Ali-Hassan33/Generating_User_Authorization-Token_via_OTP/assets/101888551/1f499e8e-fcec-4324-b92c-0ec49202159f" width = "270" height = "140">
<hr>

- <h3>Sending OTP</h3>
```http
POST http://localhost:8080/signIn
username: John Doe
otp: 9446
```
<img src= "https://github.com/Ali-Hassan33/Generating_User_Authorization-Token_via_OTP/assets/101888551/92b3b8c5-6739-451b-8cc5-38648e235521" width = "510" height = "140">  <img src= "https://github.com/Ali-Hassan33/Generating_User_Authorization-Token_via_OTP/assets/101888551/ee512920-166c-4381-9f84-b9221cc90112" width = "400" height = "140">
<hr>

- <h3>Accessing end-points via Authorization-token</h3>
```http
GET http://localhost:8080/greet
Authorization: 4c55f9d4-86be-4f86-8014-d0533298b173
```
![image](https://github.com/Ali-Hassan33/Generating_User_Authorization-Token_via_OTP/assets/101888551/1968de62-86fc-4f6e-8a2c-0d2ec306f34e)

<hr>

- <h3>Accessing end-points via invalid Authorization-token</h3>

```http
GET http://localhost:8080/greet
- Authorization: 7c55f9d4-86be-4f86-8014-d0533298b142

```

![image](https://github.com/Ali-Hassan33/Generating_User_Authorization-Token_via_OTP/assets/101888551/f330d293-0ceb-4b24-b055-2efe82536b57)










