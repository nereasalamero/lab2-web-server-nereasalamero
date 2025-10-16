# Lab 2 Web Server -- Project Report

## Description of Changes
[Detailed description of all changes made]
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

### Extra features
#### Add Replace error.html with @ControllerAdvice
- **Description:** Replace the static page with a global exception handler returning RFC 7807 `ProblemDetail` (or equivalent), mapping at least one custom domain exception with meaningful fields and localization via `MessageSource`. Handle `NoHandlerFoundException` and validation errors with distinct problem types; include `type` as a resolvable URI and `instance` as the request path.
- **JUnit Test:** Verify status codes and JSON body shape for different exceptions; include a test that ensures a correlation/trace id is included (header and response) and localized `detail` based on `Accept-Language`. Assert `404` for unknown paths uses your problem format and that content negotiation returns XML when requested.
- **Goal:** Achieve more flexible and centralized error handling.
- **Benefit:** Enhances maintainability and provides a consistent error response structure.


## Technical Decisions
[Explanation of technical choices made]

## Learning Outcomes
[What you learned from this assignment]

## AI Disclosure
### AI Tools Used
- ChatGPT
- Grammarly

### AI-Assisted Work
- [Describe what was generated with AI assistance]
- [Percentage of AI-assisted vs. original work]
- [Any modifications made to AI-generated code]

- Order the features in order of difficulty
- Explain GitHub Actions errors.
- Correction of Redaction

### Original Work
- [Describe work done without AI assistance]
- [Your understanding and learning process]