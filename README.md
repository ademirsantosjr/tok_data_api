# API REST - Cadastro de Usuários
Sistema gerenciador de usuários, perfis e senhas.
## Ferramentas utilizadas
- Java 21
- Spring-Boot
  - Data Jpa
  - Web
  - Security
  - Validation
- JWT
- Migration
  - Flyway
- Banco de Dados
  - H2 in memory DB
## Executar a aplicação
Assegurar a versão 21 do Java instalada:
```bash
java --version
```
### Passo a Passo
1. Acessar a raiz do projeto (onde se encontra pasta `/src` e `/target`)
2. Construir a aplicação executando o comando a seguir:
```bash
./mvnw clean package
```
3. Acessar a pasta `./target`
4. Executar o comando a seguir:
```bash
java -jar cadastro-0.0.1-SNAPSHOT.jar
```