# Currency Converter

This is a simple currency converter application built using Spring Boot and JDK-17.

## Project Organisation
This is a simple implementation composed of: 1) a conversion service containing the main application logic,  and 2) domain classes used to model the data entities such as countries, exchange rates and conversions. It exposes a CLI interface and a REST API.
![component diagram](src/main/resources/currencyconverter.png "Class Diagram")

The conversion service orchestrates the conversion process, utilising the domain classes to represent and manipulate currency-related information.

The domain classes adhere to strong object-oriented principles, encapsulating data and behavior related to currencies.

This approach ensures a structured and organised way to handle currency conversions, promoting modularity, reusability, and maintainability.

## Command Line Interface
The command line interface is implemented using Spring Shell. It supports the following interactions:

### 1. Currency conversion in one line:

`convert <source> <target> <amount>`

where source and target are ISO 4217 currency codes, and amount is in the format 0.00.

### 2. Step-by-step currency conversion: 

This allows the amount, source and target currencies to be set independently before the conversion is executed.

#### Set the source currency:
`set source <currency>`

where currency is an ISO 4217 currency code.

#### Set the target currency:
`set target <currency`

where currency is an ISO 4217 currency code.

#### Set the amount to convert:
`set amount <amount>`

where amount is in the format 0.00.

#### Execute the conversion:
`run`

## REST API
The application exposes a simple RESTful API for integration with other services. 

![component diagram](src/main/resources/api-demo.png "REST API Example")

## Run using Docker
### Build the image

Run the `bootBuildImage` Gradle task

### Run the application

`docker run -p 80:8080 -it -t tvergilio/currency-converter`

(the port publishing is for the REST API)

## Exchange Rate Data

The exchange rates were obtained from: https://www.gov.uk/government/publications/hmrc-exchange-rates-for-2023-monthly

They are read from a static file stored in `src/main/resources/exchange-rates.csv`