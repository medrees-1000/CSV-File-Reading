# CSV File Reading and Functional Data Processing in Java

A lightweight, functional Java utility for reading, streaming, and transforming tabular CSV datasets into strongly typed Java records and domain models.

Repository: https://github.com/medrees-1000/CSV-File-Reading

---

## Overview

This project provides a clean approach to delimited file parsing in Java by combining `java.util.Scanner` file streaming with Java functional interfaces (`Function<String, T>`, `Predicate<T>`). It ingests financial and sector-specific data streams, cleans numeric strings, strips missing value indicators, and converts rows into immutable Java records.

---

## Architecture & Components

### 1. File Parsing Engine (`edu.cst.csv.function`)
* **`ReadFile<T>`**: Generic, configurable CSV processor. Uses try-with-resources over standard Java I/O to guarantee stream closure. Supports configurable header row skipping and accepts a generic transformation function `Function<String, T>` to dynamically map lines into target objects while streaming.

### 2. Domain Models (`edu.cst.csv.function.records`)
* **`Stock`**: Immutable Java record representing equity market data:
  * `rank` (int)
  * `symbol` (String)
  * `rating` (float)
  * `marketCapInBillions` (float)
  * `dividend` (float)

### 3. Verification & Testing (`src/test/java`)
* **`T1_ReadFile`**: JUnit 5 test harness validating file ingestion and functional transformations:
  * Ingests and parses `Energy-CST3650.csv` into `Stock` record objects.
  * Sanitizes missing numeric values (e.g., `-` converted to `0.0f`).
  * Normalizes market capitalization values to billions.
  * Verifies row processing counts (232 records expected).
  * Demonstrates filter predicates for dividend yield evaluation and academic score grading.

---

## Project Structure

```text
├── src/
│   ├── main/
│   │   └── java/
│   │       └── edu/cst/csv/function/
│   │           └── ReadFile.java
│   └── test/
│       ├── java/
│       │   └── edu/cst/csv/function/
│       │       ├── records/
│       │       │   └── Stock.java
│       │       └── T1_ReadFile.java
│       └── resources/
│           └── Energy-CST3650.csv
├── pom.xml
├── .gitignore
└── README.md
