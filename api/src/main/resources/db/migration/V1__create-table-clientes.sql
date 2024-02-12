CREATE TABLE desafio_final.clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    nascimento DATE NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    logradouro VARCHAR(255),
    complemento VARCHAR(255),
    bairro VARCHAR(255),
    localidade VARCHAR(255),
    uf VARCHAR(2),
    ibge VARCHAR(255),
    gia VARCHAR(255),
    ddd VARCHAR(255),
    siafi VARCHAR(255)
);

