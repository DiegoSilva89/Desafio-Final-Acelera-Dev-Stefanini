# Projeto de CRUD de Usuários e Produtos em Java

Este é um projeto Java que implementa operações CRUD para usuários e produtos, incluindo validações de unicidade para CPF dos usuários e Código dos produtos. O projeto utiliza o framework Spring Boot para criar serviços RESTful.

## Funcionalidades

-   CRUD de Usuários (Clientes) com as seguintes informações:
    -   Nome
    -   Data de Nascimento
    -   CPF (único)
    -   CEP
-   CRUD de Produtos com as seguintes informações:
    -   Código (único)
    -   Nome
    -   Preço
-   Registros de quantidades de produtos por mês
-   Leitura e armazenamento de dados de vendas a partir de uma planilha xlsx
-   Consulta de relatório de vendas por mês
-   Consulta de relatório de venda por usuário