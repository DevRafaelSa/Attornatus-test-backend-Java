# Teste Attornatus - Backend Java
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/DevRafaelSa/Attornatus-test-backend-Java/blob/main/LICENCE)

# Sobre o projeto

A aplicação solicitada por ocasião do Teste Técnico para uma vaga de Backend Java na Attornatus Procuradoria Digital consiste em uma API que gerencie e maneje pessoas e seus endereços, podendo ainda elencar, dentre este último, um endereço principal.

## Features/Serviços Solicitados
- Criar uma pessoa
- Editar uma pessoa
- Consultar uma pessoa
- Listar pessoas
- Criar endereço para pessoa
- Listar endereços da pessoa
- Poder informar qual endereço é o principal da pessoa



## Soluções(Endpoints)
- Criar uma pessoa:              localhost:8080/api/cadastrarPessoa                             - METODO.POST
- Editar uma pessoa:             localhost:8080/api/pessoas/{idPessoa}                          - METODO.PUT
- Consultar uma pessoa:          localhost:8080/api/pessoas/{idPessoa}                          - METODO.GET
- Listar pessoas:                localhost:8080/api/pessoas/todos                               - METODO.GET
- Criar endereço para pessoa:    localhost:8080/api/addEnderecoAPessoa/pessoaId/1/endereco/1    - METODO.POST
- Listar endereços da pessoa:    localhost:8080/api/enderecos/{idPessoa}                        - METODO.GET
- Edita Endereço como Principal: localhost:8080/api/escolhendoEnderecoPrincipal/{idEndereco}    - METODO.PUT
- Retornar endereço principal:   localhost:8080/api/enderecoPrincipal/{idPessoa}                - METODO.GET
- Cria um endereço               localhost:8080/api/cadastrarEndereco                           - METODO.POST
- Listar endereços:              localhost:8080/api/endereco/todos                              - METODO.GET


# Tecnologias utilizadas
- Java
- Spring Boot
- Spring Validation
- JPA / Hibernate
- Lombok
- Maven
- Banco de dados: H2
- Testes: Postman


```bash
# clonar repositório
https://github.com/DevRafaelSa/Attornatus-test-backend-Java.git

# entrar na pasta do projeto back end
cd people-management

# executar o projeto
./mvnw spring-boot:run
```

## Questões do Teste
- Questão 1: Durante a implementação de uma nova funcionalidade de software solicitada, quais critérios você avalia e implementa para garantia de qualidade de software?

RESPOSTA:
    Fidelidade aos requisitos e documentação (diagrama de casos de uso, classes, entidade-relacionamento, atividades, etc). Do ponto de vista do valor é preciso analisar custo e preço para que a nova funcionalidade, e assim consequentemente o sistema, ofereça desempenho equitativo ao seu preço ou custo de forma aceitável pelo mercado e pela empresa. E pelo ponto de vista do usuário é interessante estudar se a nova funcionalidade oferece com eficácia e eficiência a satisfação da necessidade a ser suprida pela feature. Em suma: estudo de vialbilidade, elicitação e análise dos requisitos, identificação, e validação com os stakeholders. Com isso a qualidade de uma nova feature deverá se manter proporcional aos esforços do time nesse sentido. Um mecanismo muito interessante é a prototipação. Ela permite antecipar algo de validação e melhorias, possibilitando a diminuição de modificações e alterações no decorrer da implementação.


- Questão 2: Em qual etapa da implementação você considera a qualidade de software?

RESPOSTA:
    A qualidade de software permea todas as fases de desenvolvimento de um sistema. Com efeito, já na elicitação de requisitos a qualidade é bem vinda. Requisitos bem especificados e esmiuçados, assim como a documentação produzida a partir desses requisitos (funcionais, não funcionais ou regras de negócio). Outros pontos de vista da qualidade de software são importantes, por exemplo, do ponto de vista do usuário um produto/software de qualidade é aquele que melhor atender suas necessidades. E este é m excelente "termômetro" de qualidade do software. Contudo, ainda que haja outras possibilidades de análise de qualidade do software, a questão se refere especificamente a etapa de implementação. Aqui aparecem, por exemplo, testes unitários, de integração e também os testes e2e (end to end - ainda que no caso deste último ele seja mais plausível na fase de IMPLANTAÇÃO). A depender ainda de se tratar de uma aplicação web, por exemplo, testes de tráfego e hospedagem também são importantes para evitar circuit brakers e outras falhas de sistema. Por fim, acredito que a resposta da questão anterior também possa ser levada em consideração aqui



# Autor

Rafael Nunes de Sá Santos

https://www.linkedin.com/in/rafaelsasantos/