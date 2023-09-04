CREATE DATABASE tech_proof;
CREATE DATABASE tech_proof_test;
CREATE USER tech_proof WITH ENCRYPTED PASSWORD 'tech_proof2023!';
ALTER DATABASE tech_proof OWNER TO tech_proof;
ALTER DATABASE tech_proof_test OWNER TO tech_proof;
GRANT ALL PRIVILEGES ON DATABASE tech_proof TO tech_proof;
GRANT ALL PRIVILEGES ON DATABASE tech_proof_test TO tech_proof;