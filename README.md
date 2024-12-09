

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

- **Java** 20
- **Maven** 3.8+
- **Spring Boot** 3.x
- **MongoDB** 6.x (para produção)
- IDE recomendada: IntelliJ IDEA ou Eclipse

---

## 🔧 **Configuração do Ambiente**

1. Clone o repositório:  
   ```bash
   git clone https://github.com/seu-usuario/api-gestao-barbearia.git



  2. Configure o arquivo `application.properties` ou `application.yml`:

  
 **Para Conexão com MongoDB**:
 ```
 spring.data.mongodb.uri=mongodb+srv://SEU_USUARIO:SUA_SENHA@cluster.mongodb.net/barbearia_prod
 spring.data.mongodb.database=barbearia
```
**Para iniciar o projeto**:
 ```
mvn spring-boot:run
 ```




