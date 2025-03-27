DELETE FROM customers_image;

DELETE FROM products_image;

DELETE FROM customers;

DELETE FROM craftsmen;

DELETE FROM products;

DELETE FROM stocks;

DELETE FROM stocks_item;

INSERT INTO customers (id, email, password, role, name, phone, cpf, active, photo_id, address_street, address_number, address_neighborhood, address_city, address_state, address_zip_code, created_at, updated_at)
    VALUES
        ('c1a2b3d4e5f6g7h8i9j0k1l2m3n4o5p6', 'user1@example.com', 'hashedpassword1', 'CUSTOMER', 'Alice Silva', '11987654321', '12345678901', TRUE, NULL, 'Street 1', '123', 'Neighborhood A', 'City X', 'State Y', '12345678', NOW(), NOW()),
        ('d2b3c4e5f6g7h8i9j0k1l2m3n4o5p6q7', 'user2@example.com', 'hashedpassword2', 'CUSTOMER', 'Bruno Souza', '11976543210', '23456789012', TRUE, NULL, 'Street 2', '456', 'Neighborhood B', 'City Y', 'State Z', '23456789', NOW(), NOW()),
        ('e3c4d5f6g7h8i9j0k1l2m3n4o5p6q7r8', 'user3@example.com', 'hashedpassword3', 'CUSTOMER', 'Carla Mendes', '11965432109', '34567890123', TRUE, NULL, 'Street 3', '789', 'Neighborhood C', 'City Z', 'State W', '34567890', NOW(), NOW()),
        ('f4d5e6g7h8i9j0k1l2m3n4o5p6q7r8s9', 'user4@example.com', 'hashedpassword4', 'CUSTOMER', 'Daniel Rocha', '11954321098', '45678901234', TRUE, NULL, 'Street 4', '101', 'Neighborhood D', 'City A', 'State V', '45678901', NOW(), NOW()),
        ('g5e6f7h8i9j0k1l2m3n4o5p6q7r8s9t0', 'user5@example.com', 'hashedpassword5', 'CUSTOMER', 'Eduarda Lima', '11943210987', '56789012345', TRUE, NULL, 'Street 5', '202', 'Neighborhood E', 'City B', 'State U', '56789012', NOW(), NOW()),
        ('h6f7g8i9j0k1l2m3n4o5p6q7r8s9t0u1', 'user6@example.com', 'hashedpassword6', 'CUSTOMER', 'Felipe Martins', '11932109876', '67890123456', TRUE, NULL, 'Street 6', '303', 'Neighborhood F', 'City C', 'State T', '67890123', NOW(), NOW()),
        ('i7g8h9j0k1l2m3n4o5p6q7r8s9t0u1v2', 'user7@example.com', 'hashedpassword7', 'CUSTOMER', 'Gabriela Santos', '11921098765', '78901234567', TRUE, NULL, 'Street 7', '404', 'Neighborhood G', 'City D', 'State S', '78901234', NOW(), NOW()),
        ('j8h9i0k1l2m3n4o5p6q7r8s9t0u1v2w3', 'user8@example.com', 'hashedpassword8', 'CUSTOMER', 'Henrique Oliveira', '11910987654', '89012345678', TRUE, NULL, 'Street 8', '505', 'Neighborhood H', 'City E', 'State R', '89012345', NOW(), NOW()),
        ('k9i0j1l2m3n4o5p6q7r8s9t0u1v2w3x4', 'user9@example.com', 'hashedpassword9', 'CUSTOMER', 'Isabela Costa', '11898765432', '90123456789', TRUE, NULL, 'Street 9', '606', 'Neighborhood I', 'City F', 'State Q', '90123456', NOW(), NOW()),
        ('l0j1k2m3n4o5p6q7r8s9t0u1v2w3x4y5', 'user10@example.com', 'hashedpassword10', 'CUSTOMER', 'João Pereira', '11887654321', '01234567890', TRUE, NULL, 'Street 10', '707', 'Neighborhood J', 'City G', 'State P', '01234567', NOW(), NOW()),
        ('m1k2l3n4o5p6q7r8s9t0u1v2w3x4y5z6', 'user11@example.com', 'hashedpassword11', 'CUSTOMER', 'Karina Borges', '11876543210', '11223344556', TRUE, NULL, 'Street 11', '808', 'Neighborhood K', 'City H', 'State O', '12345679', NOW(), NOW()),
        ('n2l3m4o5p6q7r8s9t0u1v2w3x4y5z6a7', 'user12@example.com', 'hashedpassword12', 'CUSTOMER', 'Leonardo Freitas', '11865432109', '22334455667', TRUE, NULL, 'Street 12', '909', 'Neighborhood L', 'City I', 'State N', '23456780', NOW(), NOW()),
        ('o3m4n5p6q7r8s9t0u1v2w3x4y5z6a7b8', 'user13@example.com', 'hashedpassword13', 'CUSTOMER', 'Marcela Ribeiro', '11854321098', '33445566778', TRUE, NULL, 'Street 13', '111', 'Neighborhood M', 'City J', 'State M', '34567891', NOW(), NOW()),
        ('p4n5o6q7r8s9t0u1v2w3x4y5z6a7b8c9', 'user14@example.com', 'hashedpassword14', 'CUSTOMER', 'Nicolas Almeida', '11843210987', '44556677889', TRUE, NULL, 'Street 14', '222', 'Neighborhood N', 'City K', 'State L', '45678902', NOW(), NOW()),
        ('q5o6p7q8r9s0t1u2v3w4x5y6z7a8b915', 'user15@example.com', 'hashedpassword15', 'CUSTOMER', 'Olivia Fernandes', '11832109876', '55667788990', TRUE, NULL, 'Street 15', '333', 'Neighborhood O', 'City L', 'State M', '56789013', NOW(), NOW()),
        ('q5o6p7q8r9s0t1u2v3w4x5y6z7a8b916', 'user16@example.com', 'hashedpassword16', 'CUSTOMER', 'Pedro Gomes', '11821098765', '66778899001', TRUE, NULL, 'Street 16', '555', 'Neighborhood P', 'City M', 'State N', '56789014', NOW(), NOW()),
        ('q5o6p7q8r9s0t1u2v3w4x5y6z7a8b917', 'user17@example.com', 'hashedpassword17', 'CUSTOMER', 'Quésia Nunes', '11810987654', '77889900112', TRUE, NULL, 'Street 17', '345', 'Neighborhood Q', 'City N', 'State O', '56789015', NOW(), NOW()),
        ('q5o6p7q8r9s0t1u2v3w4x5y6z7a8b918', 'user18@example.com', 'hashedpassword18', 'CUSTOMER', 'Rafael Souza', '11798765432', '88990011223', TRUE, NULL, 'Street 18', '334', 'Neighborhood R', 'City O', 'State P', '56789016', NOW(), NOW()),
        ('q5o6p7q8r9s0t1u2v3w4x5y6z7a8b919', 'user19@example.com', 'hashedpassword19', 'CUSTOMER', 'Sophia Duarte', '11787654321', '99001122334', TRUE, NULL, 'Street 19', '312', 'Neighborhood S', 'City P', 'State Q', '56789017', NOW(), NOW()),
        ('q5o6p7q8r9s0t1u2v3w4x5y6z7a8b920', 'user20@example.com', 'hashedpassword20', 'CUSTOMER', 'Lucas Bomfim', '11787654322', '81989784567', TRUE, NULL, 'Street 20', '666', 'Neighborhood T', 'City Q', 'State R', '50890320', NOW(), NOW());

INSERT INTO craftsmen (id, email, password, role, name, phone, cpf, active, photo_id, address_street, address_number, address_neighborhood, address_city, address_state, address_zip_code, created_at, updated_at)
    VALUES
        ('b2f11e1ac68d4c6b8f00c1f2e5da27db', 'joao.silva@email.com', 'senha123', 'CRAFTSMAN', 'João Silva', '31987654321', '12345678901', TRUE, NULL, 'Rua A', '123', 'Centro', 'Belo Horizonte', 'MG', '30123-456', NOW(), NOW()),
        ('c1a234eb1cf240679f982cfabbebf4d8', 'maria.souza@email.com', 'senha456', 'CRAFTSMAN', 'Maria Souza', '31986543210', '98765432100', TRUE, NULL, 'Rua B', '456', 'Santa Efigênia', 'Belo Horizonte', 'MG', '30124-567', NOW(), NOW()),
        ('d6723cd8a21946b0a2b09a9c8acba8e3', 'pedro.martins@email.com', 'senha789', 'CRAFTSMAN', 'Pedro Martins', '31985432109', '11223344556', TRUE, NULL, 'Rua C', '789', 'Barro Preto', 'Belo Horizonte', 'MG', '30125-678', NOW(), NOW()),
        ('9a21cb99c9b84a7a944acb8d56bb9b23', 'ana.oliveira@email.com', 'senha1234', 'CRAFTSMAN', 'Ana Oliveira', '31984321098', '22334455667', TRUE, NULL, 'Rua D', '321', 'Lourdes', 'Belo Horizonte', 'MG', '30126-789', NOW(), NOW()),
        ('f263ad51a1c9476db1c5ea9b413e3b4b', 'luana.pereira@email.com', 'senha5678', 'CRAFTSMAN', 'Luana Pereira', '31983210987', '33445566778', TRUE, NULL, 'Rua E', '654', 'Funcionários', 'Belo Horizonte', 'MG', '30127-890', NOW(), NOW()),
        ('a93d24b7f59a4e3d9b27c09d3c6c5d5a', 'jose.santos@email.com', 'senha9012', 'CRAFTSMAN', 'José Santos', '31982109876', '44556677889', TRUE, NULL, 'Rua F', '987', 'Floresta', 'Belo Horizonte', 'MG', '30128-901', NOW(), NOW()),
        ('7b5ab77b8e1a4f14a8d9a1d9fcb75958', 'carla.silva@email.com', 'senha3456', 'CRAFTSMAN', 'Carla Silva', '31981098765', '55667788990', TRUE, NULL, 'Rua G', '123', 'Pampulha', 'Belo Horizonte', 'MG', '30129-012', NOW(), NOW()),
        ('43cd5d4f82f341bb8f2c6a3e69933c95', 'marcos.mendes@email.com', 'senha2345', 'CRAFTSMAN', 'Marcos Mendes', '31979987654', '66778899001', TRUE, NULL, 'Rua H', '234', 'São Bento', 'Belo Horizonte', 'MG', '30130-123', NOW(), NOW()),
        ('90f9b01a053b47e39a1f3f9e56d1d616', 'gabriela.rodrigues@email.com', 'senha6789', 'CRAFTSMAN', 'Gabriela Rodrigues', '31978876543', '77889900112', TRUE, NULL, 'Rua I', '345', 'Pindorama', 'Belo Horizonte', 'MG', '30131-234', NOW(), NOW()),
        ('5f84b045ec07412b818ab6e2f89a4d0a', 'fernando.almeida@email.com', 'senha0123', 'CRAFTSMAN', 'Fernando Almeida', '31977765432', '88990011223', TRUE, NULL, 'Rua J', '456', 'Santo Antônio', 'Belo Horizonte', 'MG', '30132-345', NOW(), NOW()),
        ('e6ff1f6e4b3b46348b8754b481b1f9fd', 'beatriz.martins@email.com', 'senha3457', 'CRAFTSMAN', 'Beatriz Martins', '31976654321', '99001122334', TRUE, NULL, 'Rua K', '567', 'Serra', 'Belo Horizonte', 'MG', '30133-456', NOW(), NOW()),
        ('a07b8b850f7c43e68831b0716d8f7298', 'ricardo.souza@email.com', 'senha8901', 'CRAFTSMAN', 'Ricardo Souza', '31975543210', '10012223345', TRUE, NULL, 'Rua L', '678', 'Ouro Preto', 'Belo Horizonte', 'MG', '30134-567', NOW(), NOW()),
        ('b9c2a0c083f84351971a21f33dfe648f', 'juliana.nunes@email.com', 'senha2346', 'CRAFTSMAN', 'Juliana Nunes', '31974432109', '22334455678', TRUE, NULL, 'Rua M', '789', 'Vila Nova', 'Belo Horizonte', 'MG', '30135-678', NOW(), NOW()),
        ('4acb12cd53cf45a3a3d8ad6db2f801ef', 'lucas.oliveira@email.com', 'senha5679', 'CRAFTSMAN', 'Lucas Oliveira', '31973321098', '33445566789', TRUE, NULL, 'Rua N', '890', 'Ipiranga', 'Belo Horizonte', 'MG', '30136-789', NOW(), NOW()),
        ('dd00d33da4b74ee184c3dcb9b0c63ed2', 'patricia.rosa@email.com', 'senha9013', 'CRAFTSMAN', 'Patrícia Rosa', '31972210987', '44556677890', TRUE, NULL, 'Rua O', '123', 'Vila Estoril', 'Belo Horizonte', 'MG', '30137-890', NOW(), NOW()),
        ('0c62a9f6b5754cb18e5d2edc84b75f99', 'gabriel.souza@email.com', 'senha2347', 'CRAFTSMAN', 'Gabriel Souza', '31971109876', '55667788991', TRUE, NULL, 'Rua P', '234', 'Coração Eucarístico', 'Belo Horizonte', 'MG', '30138-901', NOW(), NOW()),
        ('32d0706a6cdb4694a9e3d7c94482f3c3', 'carolina.pereira@email.com', 'senha6780', 'CRAFTSMAN', 'Carolina Pereira', '31970098765', '66778899002', TRUE, NULL, 'Rua Q', '345', 'São Lucas', 'Belo Horizonte', 'MG', '30139-012', NOW(), NOW()),
        ('2bcb98309e0a4e72a9371f245d72a300', 'felipe.alves@email.com', 'senha0124', 'CRAFTSMAN', 'Felipe Alves', '31968987654', '77889900113', TRUE, NULL, 'Rua R', '456', 'Caiçara', 'Belo Horizonte', 'MG', '30140-123', NOW(), NOW()),
        ('34652d32cd634e7b9c7bb32192a62f8b', 'raquel.lima@email.com', 'senha3458', 'CRAFTSMAN', 'Raquel Lima', '31967876543', '88990011224', TRUE, NULL, 'Rua S', '567', 'Caratateua', 'Belo Horizonte', 'MG', '30141-234', NOW(), NOW());

INSERT INTO products (id, name, description, price, type, photo_id, craftsman_id, created_at, updated_at)
    VALUES
        ('8c9f7d1e2a3b4c5d8e9f0a1b2c3d4e5f', 'Colar Artesanal de Prata', 'Colar feito à mão com prata pura.', 150.00, 'HANDMADE_JEWELRY', NULL, '4acb12cd53cf45a3a3d8ad6db2f801ef', NOW(), NOW()),
        ('b1c2d3e4f5a6b7c8d9e0f1a2b3c4d5e6', 'Escultura de Madeira', 'Peça única esculpida em madeira nobre.', 300.00, 'SCULPTURE', NULL, '0c62a9f6b5754cb18e5d2edc84b75f99', NOW(), NOW()),
        ('d5e6f7a8b9c0d1e2f3a4b5c6d7e8f9a0', 'Vaso de Cerâmica', 'Vaso artesanal pintado à mão.', 90.00, 'CERAMICS', NULL, 'f263ad51a1c9476db1c5ea9b413e3b4b', NOW(), NOW()),
        ('a9b8c7d6e5f4a3b2c1d0e9f8a7b6c5d4', 'Quadro Abstrato', 'Pintura vibrante em tela.', 450.00, 'PAINTING', NULL, '4acb12cd53cf45a3a3d8ad6db2f801ef', NOW(), NOW()),
        ('c5d4e3f2a1b0c9d8e7f6a5b4c3d2e1f0', 'Carteira de Couro', 'Feita à mão com couro legítimo.', 120.00, 'LEATHER_CRAFT', NULL, '0c62a9f6b5754cb18e5d2edc84b75f99', NOW(), NOW()),
        ('e1f0a9b8c7d6e5f4a3b2c1d0e9f8a7b6', 'Brinquedo de Madeira', 'Carrinho artesanal ecológico.', 80.00, 'HANDMADE_TOYS', NULL, 'f263ad51a1c9476db1c5ea9b413e3b4b', NOW(), NOW()),
        ('d3e2f1a0b9c8d7e6f5a4b3c2d1e0f9a8', 'Pote de Vidro Decorado', 'Vidro soprado com detalhes pintados.', 110.00, 'GLASS_ART', NULL, '4acb12cd53cf45a3a3d8ad6db2f801ef', NOW(), NOW()),
        ('b7c6d5e4f3a2b1c0d9e8f7a6b5c4d3e2', 'Caderno Artesanal', 'Feito com papel reciclado.', 50.00, 'PAPER_CRAFT', NULL, '0c62a9f6b5754cb18e5d2edc84b75f99', NOW(), NOW()),
        ('e9f8a7b6c5d4e3f2a1b0c9d8e7f6a5b4', 'Tapete de Crochê', 'Feito à mão com fios de algodão.', 200.00, 'CROCHET_KNITTING', NULL, 'f263ad51a1c9476db1c5ea9b413e3b4b', NOW(), NOW()),
        ('c1d0e9f8a7b6c5d4e3f2a1b0c9d8e7f6', 'Escultura de Metal', 'Peça moderna feita com aço.', 500.00, 'METAL_ART', NULL, '4acb12cd53cf45a3a3d8ad6db2f801ef', NOW(), NOW()),
        ('a3b2c1d0e9f8a7b6c5d4e3f2a1b0c9d8', 'Caixa de Madeira', 'Feita à mão com entalhes detalhados.', 140.00, 'WOODWORK', NULL, '0c62a9f6b5754cb18e5d2edc84b75f99', NOW(), NOW()),
        ('e5f4a3b2c1d0e9f8a7b6c5d4e3f2a1b0', 'Bijuteria de Resina', 'Colar feito com resina colorida.', 95.00, 'RESIN_ART', NULL, 'f263ad51a1c9476db1c5ea9b413e3b4b', NOW(), NOW()),
        ('f4d3b2a1c0e9f8a7b6d5e4c3b2a1d0e9', 'Porta-copos Sustentável', 'Feito com materiais reciclados.', 35.00, 'ECO_FRIENDLY_PRODUCTS', NULL, '4acb12cd53cf45a3a3d8ad6db2f801ef', NOW(), NOW()),
        ('b2c1d0e9f8a7b6c5d4e3f2a1b0c9d8e7', 'Bolsa de Tecido Artesanal', 'Bolsa feita com tecidos reciclados.', 180.00, 'TEXTILE_ART', NULL, '0c62a9f6b5754cb18e5d2edc84b75f99', NOW(), NOW()),
        ('a1b0c9d8e7f6a5b4c3d2e1f0a9b8c7d6', 'Relógio de Madeira', 'Relógio feito à mão.', 250.00, 'WOODWORK', NULL, 'f263ad51a1c9476db1c5ea9b413e3b4b', NOW(), NOW()),
        ('f9e8d7c6b5a4d3e2f1a0b9c8d7e6f5a4', 'Cesta de Palha', 'Trançada à mão.', 75.00, 'ECO_FRIENDLY_PRODUCTS', NULL, '4acb12cd53cf45a3a3d8ad6db2f801ef', NOW(), NOW()),
        ('e7d6c5b4a3d2f1e0b9c8d7e6f5a4b3c2', 'Espelho com Moldura Artesanal', 'Feito com madeira sustentável.', 320.00, 'WOODWORK', NULL, '0c62a9f6b5754cb18e5d2edc84b75f99', NOW(), NOW()),
        ('c3b2a1d0e9f8a7b6d5e4c3b2a1d0e9f8', 'Escultura de Resina', 'Obra moderna feita à mão.', 400.00, 'RESIN_ART', NULL, 'f263ad51a1c9476db1c5ea9b413e3b4b', NOW(), NOW()),
        ('d2e1f0a9b8c7d6e5f4a3b2c1d0e9f8a7', 'Pintura em Aquarela', 'Paisagem natural pintada à mão.', 280.00, 'PAINTING', NULL, '4acb12cd53cf45a3a3d8ad6db2f801ef', NOW(), NOW());
