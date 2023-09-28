# API Spring Boot - Teste Técnico Attornatus

Esta é uma API Spring Boot desenvolvida como parte do teste técnico da Attornatus.
A API fornece funcionalidades essenciais para a aplicação e pode ser
facilmente executada usando o Docker Compose.

## Contato
- Email: a.h.vassoler@gmail.com

## Pré-requisitos

Antes de começar, certifique-se de que você tenha as seguintes ferramentas instaladas em sua máquina:

- [Java](https://www.java.com/): Versão 17 ou superior.
- [Gradle](https://gradle.org/): Para gerenciamento de dependências e construção do projeto.
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Executando a API

Siga os passos abaixo para executar a API em seu ambiente:

1. Clone este repositório para sua máquina local:

   ```bash
   git clone https://github.com/Arthur-Vassoler/teste-tecnico-attornatus

- Importe os requests do Postman necessários para testar a API.
 Você pode encontrar esses requests no arquivo
  - requests_teste_attornatus.postman_collection.json,

  que deve ser colocado na pasta raiz do projeto.

Você pode Executar o Docker Compose para iniciar a API:

```bash
docker-compose up -d
```

### Ou

- #### Usando o Gradle: <br>
Se o projeto estiver configurado com o Gradle, você pode usar o seguinte comando para iniciar o aplicativo Spring Boot:

```bash
./gradlew bootRun
```

Ou, se você tiver o Gradle instalado globalmente:

```bash
gradle bootRun
```

Isso iniciará o contêiner da API e tornará a API disponível na porta 8080.

Agora, você pode acessar a API através do seguinte endpoint:

```bash
http://localhost:8080
```

### Documentação da API
Para obter informações detalhadas sobre os endpoints e as operações suportadas pela API, consulte a documentação da API, que pode ser acessada em:

```bash
http://localhost:8080/swagger-ui/index.html
```

### Testes Unitários
-   Este projeto inclui um conjunto abrangente de testes unitários que cobrem as principais funcionalidades da API. Você pode executar os testes usando o seguinte comando Maven:

```bash
mvn test
```

Os testes unitários são cruciais para garantir que as funcionalidades da API funcionem conforme o esperado e que o código permaneça robusto, mesmo durante as mudanças.

### Clean Code
- O código-fonte deste projeto segue os princípios de Clean Code, o que significa que ele foi escrito com um foco na legibilidade e na manutenção. Os nomes de variáveis e métodos são descritivos, e o código é organizado de forma lógica.