# Validation Service

Service that simplifies the use of Bean Validation (JSR-303) in Java projects, this service allows call some methods for different types of validations (by object, property o r value).

The service are integrated with Javax Injection (JSR-330), the implementation class are annotated with `@Named` and `@Singleton`. Singleton because the validator factory are loaded only at first time (by constructor parameter or load default using `Validation.buildDefaultValidatorFactory()` method).