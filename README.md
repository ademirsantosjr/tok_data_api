# API REST - Cadastro de Usuários
Sistema gerenciador de usuários, perfis e senhas, com foco na autenticação e autorização de usuários.
> Para consumir os recursos desta aplicação utilizando uma interface de usuário acesse [Users UI](https://github.com/ademirsantosjr/users_ui) e siga as instruções.

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
A aplicação estará em execução no endereço `http://localhost:8080`.<br>
<br>
Para consumir os recursos desta aplicação utilizando uma interface de usuário acesse [Users UI](https://github.com/ademirsantosjr/users_ui) e siga as instruções.

## Configurações
Para alterar as configurações de servidor, porta, cors etc, acesse o arquivo de configurações `application.yaml`.<br>
</br>
Para alterar a validade do token, modifique o valor da propriedade `security.jwt.token.expired-length` em `application.yaml`. O valor atual é de 3 minutos (180000ms)