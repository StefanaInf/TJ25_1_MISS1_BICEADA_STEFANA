### Laboratory 6

In folder named PROJECT

- Refactor your domain such as students and instructors are also users of the application. Other users may exist, such as an administrator.
-> **added UserEntity domain entity** 

- Configure Spring Boot to read the users and their roles (ADMIN, INSTRUCTOR, STUDENT) from the database.
-> **created Role enum, which is a field in UserEntity** 

- Implement JWT-based authentication and a login endpoint issuing tokens.
-> **added JwtAuthenticationFilter (enforces JWT auth as part of the securityFilterChain) and JwtService (generates, validates tokens)**

- Protect POST/PUT/DELETE endpoints with role-based access and keep GET endpoints public where reasonable.
- Add method-level security with @PreAuthorize and test it.
-> **enforeces RBAC on endpoints via SecurityConfig, as well as implemented PreAithorize on mappings within StudentController** 

- Implement password storage with BCrypt and a user registration flow.
-> **created a Bean passwordEncoder in SecurityConfig** 

- Secure Actuator endpoints: expose only health and info publicly; require auth for metrics.
-> **exposed "/actuator/health",  "/actuator/info" with permitAll and ("/actuator/") required admin role**


### Compulsory 7
Mistakenly named Compulsory 6, im sorry.

Cannot get Kafka to connect on my machine, tested connection both ways 
- host -> docker (succeceds)
- docker -> host (succeceds)
- service to docker (fails), connections times out and the request has a size of >1Gb?? The topic has been created through which is weird, seems like a configuration problem

uploaded my attempt :(
