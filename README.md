# 🌦️ API Tempo Livre - Sistema de Monitoramento Climático

Uma API REST robusta desenvolvida com Spring Boot para gerenciar alertas climáticos, sensores IoT, abrigos seguros e usuários em um sistema integrado de monitoramento de eventos climáticos extremos.

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Vídeos de Demonstração](#-vídeos-de-demonstração)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação](#-instalação)
- [Como Usar](#-como-usar)
- [Endpoints da API](#-endpoints-da-api)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Deploy](#-deploy)
- [Documentação](#-documentação)
- [Contribuição](#-contribuição)
- [Licença](#-licença)
- [Desenvolvedores](#-desenvolvedores)
- [Links Úteis](#-links-úteis)

## 🎯 Sobre o Projeto

O **Tempo Livre** é uma solução tecnológica inovadora desenvolvida para enfrentar os eventos climáticos extremos que afetam milhões de brasileiros. A API combina autenticação JWT, gerenciamento de sensores IoT, sistema de alertas inteligentes e mapeamento de abrigos seguros em uma plataforma integrada.

O sistema oferece:

- Autenticação segura com JWT
- Gerenciamento completo de usuários
- Sistema de alertas com diferentes níveis de severidade
- Cadastro e localização de abrigos seguros
- API RESTful com documentação Swagger

## 📺 Vídeos de Demonstração

#### Pitch: [YouTube]()
#### Demonstração: [YouTube](https://youtu.be/0TpukToeOvU)

## ⚡ Funcionalidades

- ✅ **Autenticação JWT**: Sistema seguro de login e registro
- ✅ **Gerenciamento de Usuários**: CRUD completo com paginação
- ✅ **Monitoramento de Sensores**: Integração com dispositivos IoT
- ✅ **Sistema de Alertas**: Níveis configuráveis de alertas climáticos
- ✅ **Abrigos Seguros**: Cadastro e busca de locais seguros
- ✅ **API RESTful**: Endpoints padronizados e documentados
- ✅ **Documentação Swagger**: Interface interativa para testes
- ✅ **Filtros Avançados**: Busca por múltiplos critérios
- ✅ **Paginação**: Otimização para grandes volumes de dados

## 🛠 Tecnologias Utilizadas

### Backend & Framework
- **Java 17** - Linguagem de programação
- **[Spring Boot 3.5.0](https://spring.io/projects/spring-boot)** - Framework principal
- **[Spring Security](https://spring.io/projects/spring-security)** - Autenticação e autorização
- **[Spring Data JPA](https://spring.io/projects/spring-data-jpa)** - Persistência de dados
- **[Hibernate](https://hibernate.org/)** - ORM

### Banco de Dados
- **[Oracle Database](https://www.oracle.com/database/)** - Banco de dados principal
- **[JDBC Oracle Driver](https://www.oracle.com/database/technologies/appdev/jdbc.html)** - Conectividade

### Segurança & Validação
- **[JWT (java-jwt)](https://github.com/auth0/java-jwt)** - Tokens de autenticação
- **[Spring Validation](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#validation)** - Validação de dados
- **[BCrypt](https://spring.io/blog/2017/11/01/spring-security-5-0-x-password-storage-format)** - Criptografia de senhas

### Documentação & Desenvolvimento
- **[Swagger/OpenAPI](https://springdoc.org/)** - Documentação da API
- **[Lombok](https://projectlombok.org/)** - Redução de boilerplate
- **[Maven](https://maven.apache.org/)** - Gerenciamento de dependências
- **[Spring DevTools](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools)** - Hot reload

### Configuração
- **[DotEnv](https://github.com/cdimascio/dotenv-java)** - Gerenciamento de variáveis de ambiente

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- **Java 17** ou superior
- **Maven 3.6+**
- **Oracle Database** (local ou em nuvem)
- **Git**

## 🚀 Instalação

### 1. Clone o Repositório
```bash
git clone https://github.com/TEMPO-LIVRE-APP/api.git
cd api
```

### 2. Configure o Banco de Dados

Crie um arquivo `.env` na raiz do projeto:
```env
# Database Configuration
DB_URL=jdbc:oracle:thin:@localhost:1521:XE
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha

# JWT Configuration
JWT_SECRET=sua_chave_secreta_aqui
JWT_EXPIRATION=86400000
```

### 3. Instale as Dependências
```bash
mvn clean install
```

### 4. Execute a Aplicação
```bash
mvn spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

## 📱 Como Usar

### 1. Acesse a Documentação
Após iniciar a aplicação, acesse:
- **Swagger UI**: `http://localhost:8080/swagger-ui/index.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

### 2. Registre um Usuário
```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "email": "joao@email.com", 
    "username": "joaosilva",
    "password": "123456",
    "role": "USER"
  }'
```

### 3. Faça Login
```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "joao@email.com",
    "password": "123456"
  }'
```

### 4. Use o Token JWT
Inclua o token retornado no header `Authorization: Bearer {token}` para acessar endpoints protegidos.

## 🌐 Endpoints da API

### 🔐 Autenticação (`/auth`)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/auth/login` | Realizar login |
| POST | `/auth/register` | Cadastrar novo usuário |

### 👥 Usuários (`/users`)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/users` | Listar usuários (paginado) |
| GET | `/users/{id}` | Buscar por ID |
| GET | `/users/email/{email}` | Buscar por email |
| GET | `/users/username/{username}` | Buscar por username |
| POST | `/users` | Criar usuário |
| PUT | `/users/{id}` | Atualizar usuário |
| DELETE | `/users/{id}` | Deletar usuário |

### 📡 Sensores (`/sensors`)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/sensors` | Listar sensores (paginado) |
| GET | `/sensors/{id}` | Buscar sensor por ID |
| POST | `/sensors` | Cadastrar sensor |
| PUT | `/sensors/{id}` | Atualizar sensor |
| DELETE | `/sensors/{id}` | Deletar sensor |

### 🏠 Abrigos Seguros (`/safe-places`)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/safe-places` | Listar abrigos (com filtros) |
| GET | `/safe-places?name={nome}` | Filtrar por nome |
| GET | `/safe-places?endereco={endereco}` | Filtrar por endereço |
| GET | `/safe-places/{id}` | Buscar abrigo por ID |
| POST | `/safe-places` | Cadastrar abrigo |
| PUT | `/safe-places/{id}` | Atualizar abrigo |
| DELETE | `/safe-places/{id}` | Deletar abrigo |

### 🚨 Alertas (`/alerts`)
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/alerts` | Listar alertas (com filtros) |
| GET | `/alerts?nivel={nivel}` | Filtrar por nível |
| GET | `/alerts?status={status}` | Filtrar por status |
| GET | `/alerts/{id}` | Buscar alerta por ID |
| POST | `/alerts` | Criar alerta |
| PUT | `/alerts/{id}` | Atualizar alerta |
| DELETE | `/alerts/{id}` | Deletar alerta |

## 📁 Estrutura do Projeto

```
api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/tempolivre/api/
│   │   │       ├── controller/          # Controllers REST
│   │   │       │   ├── AuthController.java
│   │   │       │   ├── UserController.java
│   │   │       │   ├── SensorController.java
│   │   │       │   ├── AbrigoController.java
│   │   │       │   └── AlertaController.java
│   │   │       ├── service/             # Lógica de negócio
│   │   │       ├── repository/          # Acesso a dados
│   │   │       ├── model/               # Entidades JPA
│   │   │       ├── dto/                 # Data Transfer Objects
│   │   │       ├── security/            # Configurações de segurança
│   │   │       └── config/              # Configurações gerais
│   │   └── resources/
│   │       ├── application.properties   # Configurações da aplicação
│   │       └── data.sql                # Scripts de inicialização
│   └── test/                           # Testes unitários e integração
├── .env                                # Variáveis de ambiente
├── pom.xml                            # Dependências Maven
└── README.md
```

## 🚀 Deploy

### Deploy no Azure
O projeto está configurado para deploy automático no Azure. 

**URL do Deploy**: [Azure App Service]()

### Configuração de Produção
1. Configure as variáveis de ambiente no Azure App Service
2. Ajuste a connection string do banco de dados
3. Configure o perfil de produção no `application-prod.properties`

### Docker (Opcional)
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 📖 Documentação

### Swagger UI
Acesse a documentação interativa em:
`http://localhost:8080/swagger-ui/index.html`

### Postman Collection
Importe a coleção do Postman para testar todos os endpoints:
[Download da Collection]()

### Exemplos de Uso
```java
// Exemplo de criação de alerta
{
  "nivel": "ALTO",
  "status": "ATIVO",
  "mensagem": "Alerta de tempestade severa",
  "localizacao": "São Paulo, SP",
  "dataHora": "2025-06-08T10:30:00"
}
```

## 🤝 Contribuição

Contribuições são sempre bem-vindas! Para contribuir:

1. Faça um Fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

### Áreas que Precisam de Ajuda
- [ ] Implementação de notificações em tempo real
- [ ] Melhorias na documentação da API
- [ ] Testes de integração automatizados
- [ ] Implementação de cache Redis
- [ ] Métricas e monitoramento
- [ ] Otimização de queries do banco

## 📄 Licença

Este projeto está sob a licença MIT. 
Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
Ou acesse o site [https://opensource.org/license/mit](https://opensource.org/license/mit)

## 👨‍💻 Desenvolvedores

**Daniel Barros** - [LinkedIn](https://www.linkedin.com/in/danielbarros63/) - [GitHub](https://github.com/Barros263inf)

**Luccas Alencar** - [LinkedIn](https://www.linkedin.com/in/luccasalencar/) - [GitHub](https://github.com/LuccasAlencar)

**Link do Projeto**: [https://github.com/TEMPO-LIVRE-APP/api](https://github.com/TEMPO-LIVRE-APP/api)

---

## 🔗 Links Úteis

### Documentação Técnica
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/)
- [Spring Data JPA Reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Oracle JDBC Documentation](https://docs.oracle.com/en/database/oracle/oracle-database/21/jjdbc/)
- [JWT.io - JWT Debugger](https://jwt.io/)

### Tutoriais e Referências
- [Building REST APIs with Spring Boot](https://spring.io/guides/tutorials/rest/)
- [Securing Spring Boot with JWT](https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/)
- [Spring Boot Testing Guide](https://spring.io/guides/gs/testing-web/)
- [Oracle Database Setup Guide](https://docs.oracle.com/en/database/oracle/oracle-database/21/xeinw/)

### Ferramentas de Desenvolvimento
- [Postman](https://www.postman.com/)
- [DBeaver - Database Client](https://dbeaver.io/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)

---

⭐ **Se este projeto foi útil para você, não esqueça de dar uma estrela!** ⭐
