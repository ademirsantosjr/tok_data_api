ALTER TABLE usuarios
ADD CONSTRAINT fk_perfil
FOREIGN KEY (perfil_id) REFERENCES perfis(id)