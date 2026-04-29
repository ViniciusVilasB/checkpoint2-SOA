# API Salas de Reunião - CP2
**Nome:** [Vinicius Vilas Boas]  
**RM:** [557843]

## Descrição
API desenvolvida em Spring Boot para gerenciamento de Salas de Reunião e Reservas, aplicando arquitetura SOA.

## Tecnologias
- Java 21
- Spring Boot
- H2 Database (Memória)
- Validações

## Como executar
1. Rode a classe `ReservaSalasApplication.java`
2. O banco H2 pode ser acessado em `http://localhost:8080/h2-console`
3. O Swagger está disponível em `http://localhost:8080/swagger-ui.html`

## Exemplos de JSON para teste (Insomnia/Postman)

**Criar Sala (POST /api/salas)**
{
"nome": "Sala A",
"capacidade": 10,
"localizacao": "Andar 1"
}

**Criar Reserva (POST /api/reservas)**
{
"salaId": 1,
"responsavel": "Vinicius",
"dataHoraInicio": "2026-05-10T14:00:00",
"dataHoraFim": "2026-05-10T16:00:00"
}