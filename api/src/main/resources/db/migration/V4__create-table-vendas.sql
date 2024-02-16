CREATE TABLE desafio_final.vendas (
    id SERIAL PRIMARY KEY,
    codigo_produto VARCHAR(255) NOT NULL,
    cpf_usuario VARCHAR(14) NOT NULL,
    quantidade_produtos INT NOT NULL,
    data_compra DATE NOT NULL
);
