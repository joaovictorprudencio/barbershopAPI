

# 📋 **API de Gestão de Barbearia**

Bem-vindo à documentação da **API de Gestão de Barbearia**. Esta API foi desenvolvida com **Java** e **Spring Boot** para gerenciar horários, clientes e barbeiros.

---

## 🚀 **Funcionalidades**

- **Gerenciamento de Barbeiros**  
  - Cadastro, edição e listagem de barbeiros.
- **Gerenciamento de Clientes**  
  - Cadastro, edição e listagem de clientes.
- **Agendamento de Horários**  
  - Criação e cancelamento de horários.
  - Listagem de horários disponíveis e ocupados.
  - filtrar os horários do dia 
- **Autenticação e Autorização**  
  - Sistema baseado em **JWT** para autenticar clientes e barbeiros.

---

## 📦 **Requisitos**

- **Java** 21
- **Maven** 3.8+
- **Spring Boot** 3.x
- **MySQl**  (para produção)
- IDE recomendada: IntelliJ IDEA ou Eclipse

---

## 🔧 **Configuração do Ambiente**

1. Clone o repositório:  
   ```bash
   git clone https://github.com/seu-usuario/api-gestao-barbearia.git



  2. Configure o arquivo `application.properties` ou `application.yml`:

  
 **Para Conexão com MySQL**:
 ```
spring.datasource.url=jdbc:mysql://SEU_HOST:SEU_PORTA/NOME_DO_BANCO
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```
**Para iniciar o projeto**:
 ```
mvn spring-boot:run
 ```

**Para iniciar o projeto em produção**:
 ```
docker-compose up
 ```




