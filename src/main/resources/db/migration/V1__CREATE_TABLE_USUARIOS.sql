CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50),
    perfil_id INT,
    email VARCHAR(250),
    senha VARCHAR(250)
)