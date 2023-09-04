DELETE FROM tech_proof.product;
DELETE FROM tech_proof.stock;
DELETE FROM tech_proof.size;

SELECT reset_schema_sequences('tech_proof');
