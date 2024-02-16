CREATE TABLE desafio_final.quantidades (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    quantidade INT NOT NULL,
    mes INT NOT NULL,
    ano INT NOT NULL,
    produto_id BIGINT NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES desafio_final.produtos(id)
);
