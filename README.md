# Conversor de Moedas em Java - Desafio ALURA

## Descrição

Este projeto é um conversor de moedas desenvolvido em Java que utiliza a API [ExchangeRate-API](https://www.exchangerate-api.com/) para obter taxas de câmbio em tempo real. O programa interage com o usuário via console, apresentando um menu com diferentes opções de conversão entre moedas populares (USD, EUR, GBP e BRL).  

A integração com a API é feita utilizando a moderna API HTTP do Java (`HttpClient`, `HttpRequest` e `HttpResponse`), garantindo uma comunicação eficiente e tratamento adequado das respostas.

---

## Funcionalidades

- Consulta dinâmica das taxas de câmbio em tempo real.
- Opções de conversão para 6 pares de moedas diferentes:
  - Dólar (USD) → Real (BRL)
  - Euro (EUR) → Real (BRL)
  - Libra (GBP) → Real (BRL)
  - Real (BRL) → Dólar (USD)
  - Real (BRL) → Euro (EUR)
  - Real (BRL) → Libra (GBP)
- Validação do código de resposta HTTP da API.
- Tratamento de erros e mensagens claras para o usuário.
- Interface de texto simples e intuitiva via console.

---

## Tecnologias Utilizadas

- Java 11 ou superior (para uso da API HttpClient)
- [Gson](https://github.com/google/gson) para parsing JSON
- ExchangeRate-API (https://www.exchangerate-api.com/) para taxas de câmbio dinâmicas

---

## Como usar

### Pré-requisitos

- Java 11+ instalado
- Biblioteca Gson (`gson-2.10.1.jar`) disponível no classpath
- Chave da API ExchangeRate-API (gratuita) — veja como obter abaixo

### Passos para compilar e executar

1. Baixe o [Gson](https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar) e coloque na pasta do projeto.

2. Edite o arquivo `ConversorMoedas.java` e insira sua própria chave da API no campo:

```java
private static final String API_KEY = "SUA_CHAVE_AQUI";
