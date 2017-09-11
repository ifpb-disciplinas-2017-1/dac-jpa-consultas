CREATE TABLE dependente (
    id integer NOT NULL,
    nome character varying(255),
    chave_emp integer
);

CREATE TABLE emp_proj (
    empregados_id integer NOT NULL,
    projetos_id integer NOT NULL
);

CREATE TABLE empregado (
    id integer NOT NULL,
    nome character varying(255),
    chave_faculdade bigint
);

CREATE TABLE endereco (
    id integer NOT NULL,
    bairro character varying(255),
    rua character varying(255),
    empregado_id integer
);

CREATE TABLE faculdade (
    id bigint NOT NULL,
    descricao character varying(255)
);


CREATE TABLE projeto (
    id integer NOT NULL,
    descricao character varying(255)
);


CREATE TABLE sequence (
    seq_name character varying(50) NOT NULL,
    seq_count numeric(38,0)
);

ALTER TABLE ONLY dependente
    ADD CONSTRAINT dependente_pkey PRIMARY KEY (id);

ALTER TABLE ONLY emp_proj
    ADD CONSTRAINT emp_proj_pkey PRIMARY KEY (empregados_id, projetos_id);

ALTER TABLE ONLY empregado
    ADD CONSTRAINT empregado_pkey PRIMARY KEY (id);

ALTER TABLE ONLY endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);

ALTER TABLE ONLY faculdade
    ADD CONSTRAINT faculdade_pkey PRIMARY KEY (id);

ALTER TABLE ONLY projeto
    ADD CONSTRAINT projeto_pkey PRIMARY KEY (id);


ALTER TABLE ONLY dependente
    ADD CONSTRAINT fk_dependente_chave_emp FOREIGN KEY (chave_emp) REFERENCES empregado(id);

ALTER TABLE ONLY emp_proj
    ADD CONSTRAINT fk_emp_proj_empregados_id FOREIGN KEY (empregados_id) REFERENCES empregado(id);

ALTER TABLE ONLY emp_proj
    ADD CONSTRAINT fk_emp_proj_projetos_id FOREIGN KEY (projetos_id) REFERENCES projeto(id);

ALTER TABLE ONLY empregado
    ADD CONSTRAINT fk_empregado_chave_faculdade FOREIGN KEY (chave_faculdade) REFERENCES faculdade(id);

ALTER TABLE ONLY endereco
    ADD CONSTRAINT fk_endereco_empregado_id FOREIGN KEY (empregado_id) REFERENCES empregado(id);


-- inserindo --
INSERT INTO dependente (id, nome, chave_emp) VALUES (5, 'Ana', 1);
INSERT INTO dependente (id, nome, chave_emp) VALUES (6, 'Maria', 1);
INSERT INTO dependente (id, nome, chave_emp) VALUES (56, 'Kiko', 1);
INSERT INTO dependente (id, nome, chave_emp) VALUES (57, 'Chaves', 1);
INSERT INTO dependente (id, nome, chave_emp) VALUES (58, 'Florinda', 1);

INSERT INTO emp_proj (empregados_id, projetos_id) VALUES (1, 4);

INSERT INTO empregado (id, nome, chave_faculdade) VALUES (1, 'Job', 2);
INSERT INTO empregado (id, nome, chave_faculdade) VALUES (23, 'Kiko', 2);

INSERT INTO endereco (id, bairro, rua, empregado_id) VALUES (3, 'Centro', 'Rua Antonio Vilela', 1);

INSERT INTO faculdade (id, descricao) VALUES (2, 'IFPB');

INSERT INTO projeto (id, descricao) VALUES (4, 'DAC 2017.1');


