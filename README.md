# Currency Converter

This is a simple currency converter application built using Spring Boot and JDK-17.

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

## Docker
### Build the image

Run the `bootBuildImage` Gradle task

### Run the application

`docker run -it -t tvergilio/currency-converter  `

## Exchange Rates

The exchange rates were obtained from: https://www.gov.uk/government/publications/hmrc-exchange-rates-for-2023-monthly

They are read from a static file stored in `src/main/resources/exchange-rates.csv`