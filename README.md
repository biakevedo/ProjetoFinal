SENAI Notes

SENAI Notes é uma API RESTful desenvolvida durante o curso de Back-End com Java (160h) no SENAI, com foco em boas práticas de arquitetura, segurança e escalabilidade.
O projeto tem como objetivo permitir que usuários criem, organizem e compartilhem anotações com tags e imagens, integrando recursos modernos de autenticação, upload e documentação de API.

Destaques do projeto

Desenvolvimento seguindo arquitetura em camadas (Controller, Service, Repository) e padrão DTO, promovendo código limpo e desacoplado

Implementação de autenticação JWT com Spring Security, garantindo segurança nas operações

Funcionalidade de upload e gerenciamento de imagens associadas às anotações

Recuperação de senha via SMTP Gmail, simulando ambiente real de produção

Tratamento centralizado de exceções com @ControllerAdvice, aprimorando a manutenção e o controle de erros

Documentação interativa com Swagger / OpenAPI, facilitando o uso e integração por outros desenvolvedores

Deploy em ambiente AWS, utilizando PostgreSQL e Hibernate (JPA) para persistência e escalabilidade
