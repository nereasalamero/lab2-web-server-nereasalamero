# Lab 2 Web Server -- Project Report

## Description of Changes
### Exercise 1. Customise the Whitelabel Error Page
When Spring Boot application encounters an error, it displays a customised error page in order to enhance user 
experience. In order to do it, these were the followed steps:
1. Create a custom error page.
2. Save the error page in the `src/main/resources/templates` directory.
3. Add a test that validates the use of the custom error page.

### Exercise 2. Add a new endpoint
Creation of a new REST endpoint that returns the current server time in a structured format. The followed steps were:
1. Create `TimeComponent.kt`
2. Define a Data Transfer Object (DTO)
3. Create a Time Provider Interface
4. Implement the Time Provider Service
5. Create an extension function
6. Create a REST Controller
7. Add a test that validates the use of the time endpoint


### Exercise 3. Enable HTTP/2 and SSL Support
Enablement of HTTP/2 and configuration of SSL using a self-signed certificate. Steps followed:
1. Generate a Self-Signed Certificate
2. Create a PKCS12 Keystore
3. Configure Spring Boot for SSL and HTTP/2

### Adjusting Tests to HTTP/2 and SSL Implementations
After configuring HTTP/2 and SSL, the tests I wrote for exercises one and two stopped working. It took me a while to 
realise why this was happening, but thanks to the help of AI (ChatGPT in this case) I was able to understand what the 
problem was. <br>
What happened was that my tests were failing with the status code `400 BAD_REQUEST` instead of the expected 
`404 NOT_FOUND` and `200 OK`. This was because the server rejected the connection because it didn't trust the self-signed
certificate after receiving the request. <br>
Some try-error tests later, I finally asked the teacher for some help, as well as some of my classmates who had the
same problem as me. In the end, I was able to fix it by using the `TestRestTemplateConfig.kt`, where I set the server to
trust all certificates, allowing the tests to run successfully.

## Technical Decisions
- **Code Organisation**: All definitions of exercise two were made on the same file in order to improve simplicity and readability. 
- **Testing configuration**: `TestRestTemplateConfig.kt` was used to handle SSL trust during testing instead of disabling SSL validation.

## Learning Outcomes
This section includes the things I have learned from this assignment.<br>
- Some features may sound easy, but in reality they are the most tricky ones.
- How to create REST endpoints, configure and use SSL certificate, and enable HTTP/2. 
- GitHub Actions are really handy when it comes to automatisation of builds and running tests.
- Having a clean code structure is really important, as well as coding proper testing and configuration management.

## AI Disclosure
### AI Tools Used
- ChatGPT: provided explanations, debugging help, and assistance with refactoring code and report writing
- Grammarly: grammar corrections and improving the clarity of the report.

### AI-Assisted Work
- Rewrite some of the explanations, in order to increase its clarity.
- Bug fixing.
- Helped interpret GitHub Actions errors and suggested some fixes.
- All AI-generated code was reviewed and modified.

### Original Work
- Base code was provided by the teacher.
- AI was used as an assistant to improve the quality of the code, clarify some doubts and explain some errors  (mentioned in the previous section).
- Additional coding tips were researched through Internet, although most of them weren't worth it.
- Created and tested the /time endpoint.
