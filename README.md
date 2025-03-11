
---

# Projeto IGNIS - Gerenciador de Tarefas

## Descrição
 Nosso primeiro projeto é um TO-DO list. O projeto terá versões em diferentes stacks, sendo a primeira versão em **Java + Spring Boot + Angular+PostegreSQL**. 

## Tecnologias
<img src="https://skillicons.dev/icons?i=java,spring,angular,postgresql,docker" /><br/>
- **Back-end:** Java, Spring Boot, PostgreSQL
- **Front-end:** Angular
- **Banco de dados:** PostgreSQL
- **Autenticação:** OAuth e JWT
- **Deploy:** Docker/AWS (em fase de deploy)

## O que você vai precisar
Antes de rodar o projeto localmente, você precisará de algumas ferramentas instaladas no seu computador. Aqui está o que você vai precisar e como instalá-las:

### 1. **Java Development Kit (JDK)**
Para rodar o back-end, você precisará do **JDK**. Recomendamos a versão **11 ou superior**.

- [Baixar JDK 11 ou superior](https://adoptopenjdk.net/)
  
Após instalar o JDK, verifique se ele está corretamente instalado executando o seguinte comando no terminal:

```bash
java -version
```

### 2. **Apache Maven**
Tenho quase certeza que o Maven já é adicionado ao você abrir o projeto. Mas vai que "NA SUA MÁQUINA NÃO FUNCIONA" 🤨

O **Maven** é utilizado para gerenciar dependências e rodar o back-end. Para instalar o Maven, siga os passos abaixo:

- [Baixar Maven](https://maven.apache.org/download.cgi)

Após a instalação, verifique se o Maven está corretamente instalado com o comando:

```bash
mvn -version
```

### 3. **Node.js e npm**
O **Node.js** é necessário para rodar o front-end em **Angular**. O **npm** é o gerenciador de pacotes que vem junto com o Node.js.

- [Baixar Node.js](https://nodejs.org/)

Verifique se o Node.js e o npm estão instalados com os comandos:

```bash
node -v
npm -v
```

### 4. **Angular CLI**
Instale globalmente, executando o seguinte comando:

```bash
npm install -g @angular/cli
```

### 5. **PostgreSQL**
O **PostgreSQL** será usado como banco de dados para armazenar as informações do sistema. Você pode baixar o PostgreSQL no link abaixo:

- [Baixar PostgreSQL](https://www.postgresql.org/download/)

Após a instalação, crie um banco de dados chamado `todo_db` e configure as credenciais no arquivo `.env`. 

obs: o nome do banco é opcional, mas o padrão é **todo_db**

### 6. **Docker** (futuro)
FUTURO //TODO

## Arquitetura
O projeto seguirá a arquitetura **MVC** (Model-View-Controller) para o desenvolvimento do back-end, e a comunicação entre o front-end e o back-end será feita através de **API REST**.

## Estrutura do Projeto

### Backend
O back-end será construído utilizando o framework **Spring Boot** com **PostgreSQL** como banco de dados. A estrutura será organizada conforme as boas práticas de desenvolvimento em Java.

```
📂 projeto-ignis-todo
├── 📂 backend
│   ├── src/main/java/com/ignis/todo
│   │   ├── controller/        # Controladores para gerenciar as requisições
│   │   ├── service/           # Lógica de negócios
│   │   ├── repository/        # Acesso ao banco de dados
│   │   ├── dto/               # Objetos de transferência de dados
│   │   ├── model/             # Modelos de dados
│   │   ├── exception/         # Exceções personalizadas
│   │   ├── config/            # Configurações do projeto
│   │   └── security/          # (Futuro) Segurança e autenticação
│   ├── src/test/java/com/ignis/todo  # Testes automatizados
│   ├── pom.xml                # Dependências do projeto
│   ├── .env                   # Variáveis de ambiente
│   └── README.md              # Documentação do back-end
```

## Como Rodar o Projeto Localmente

### Backend
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/projeto-ignis-todo.git
   ```
2. Navegue até o diretório do back-end:
   ```bash
   cd projeto-ignis-todo/backend
   ```
3. Execute o projeto utilizando o Maven:
   ```bash
   mvn spring-boot:run
   ```

4. Crie um arquivo `.env` na raiz do diretório `backend/` e adicione as configurações necessárias. Este arquivo irá conter variáveis de ambiente para as credenciais do banco de dados e outros parâmetros de configuração.

   **Exemplo de conteúdo do `.env`:**
   ```env
   # Credenciais do banco de dados
   DB_URL=jdbc:postgresql://localhost:5432/todo_db
   DB_USERNAME=seu_usuario
   DB_PASSWORD=sua_senha

   # Outras Configurações
    ...
   ```
5. O back-end estará rodando em `http://localhost:8080`.
6. Com a aplicação rodando, veja a documentação da API em `http://localhost:8080/swagger-ui/index.html`.

### Frontend
1. Navegue até o diretório do front-end:
   ```bash
   cd projeto-ignis-todo/frontend
   ```
2. Instale as dependências:
   ```bash
   npm install
   ```
3. Execute o servidor de desenvolvimento:
   ```bash
   ng serve
   ```
4. O front-end estará disponível em `http://localhost:4200`.

## Testes //TODO
Ainda será implementado

Para rodar os testes:
- **Back-end:**
  ```bash
  ./mvnw test
  ```
- **Front-end:**
  ```bash
  ng test
  ```

## Planejamento Futuro
- **Versões em outras stacks**: Após a conclusão da versão em Java + Angular, as versões em Node.js + React, C# + Vue.js e Python + Vue.js poderão ser desenvolvidas.
- **Funcionalidades adicionais**: O sistema de segurança será aprimorado com implementações futuras de autenticação e autorização.
- **Deploy completo**: O sistema será implementado em ambientes de produção utilizando (decisão futura).

## Deu ruim ou tem dúvida
Abra uma issue ou me mande mensagem no discord (vinidias1)👍.

## Contribuições
Quer ajudar? Me mande mensagem ou abra uma issue dizendo quer colaborar. Vou pensar no seu caso 🤔😂

## Dito isso...
Bufo! 

---
