ALTER TABLE users
ADD CONSTRAINT fk_profie
FOREIGN KEY (profile_id) REFERENCES profiles(id)