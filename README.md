# Currency Converter App

## Overview

The **Currency Converter App** is a Android application that allows users to convert between different currencies using real-time exchange rates with user-friendly interface.
Designed with a focus on simplicity and usability, it provides accurate conversions quickly and efficiently across 30+ global currencies.

## Main Layout
![main layout](https://github.com/user-attachments/assets/406783de-78b9-4050-8952-f1160f497a4a)




## Features

- **Real-Time Exchange Rates**: Fetches updated rates from https://freecurrencyapi.com/.
- **User-Friendly Interface**: Simple and intuitive for all users.
- **Support for Multiple Currencies**: Convert between various global currencies.
- **History**: Check the history of previous conversions and option to clear all.

## History Layout
![history layout](https://github.com/user-attachments/assets/0c79dc76-5c63-4aba-bcd3-cab420b6d801)



## Technologies Used

- Java
- Volley (HTTP)
- SQLite Database

## Installation

Follow these steps to set up and run the Currency Converter App on your local machine:

### Prerequisites

- Install [Android Studio](https://developer.android.com/studio).
- Obtain an API key from [FreeCurrencyAPI](https://freecurrencyapi.com/).

### Steps

1. **Clone the Repository**:
   Clone the project to your local machine using the following command:
   ```bash
   git clone https://github.com/glk-0/currency-converter-app.git
   cd currency-converter-app

2.Open the Project in Android Studio:
-Open Android Studio.
-Click on File > Open and navigate to the project directory.
-Select the folder and open it.
-Set Up the API Key:

Obtain an API key from FreeCurrencyAPI.
- Add the key to res/values/strings.xml:
```bash
   <string name="api_key">your_api_key_here</string>
