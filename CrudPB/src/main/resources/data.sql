INSERT INTO `tipos_producto`
(`tip_id`, `tip_descripcion`)
VALUES
    (1, 'ProduccionTipo1'),
    (2, 'ProduccionTipo2'),
    (3, 'ProduccionTipo3'),
    (4, 'ProduccionTipo4'),
    (5, 'ProduccionTipo5'),
    (6, 'ProduccionTipo6'),
    (7, 'ProduccionTipo7'),
    (8, 'ProduccionTipo8'),
    (9, 'ProduccionTipo9'),
    (10, 'ProduccionTipo10');

INSERT INTO `productos`
(`prd_id`, `prd_descripcion`, prd_tip_id, prd_stock, prd_precio)
VALUES
    (1, 'ProduccionProdTipo1', 4, 11, 11111),
    (2, 'ProduccionProdTipo2', 3, 22, 22222),
    (3, 'ProduccionProdTipo3', 8, 33, 33333),
    (4, 'ProduccionProdTipo4', 8, 44, 44444),
    (5, 'ProduccionProdTipo5', 5, 55, 55555),
    (6, 'ProduccionProdTipo6', 9, 66, 66666),
    (7, 'ProduccionProdTipo7', 8, 77, 77777),
    (8, 'ProduccionProdTipo8', 6, 88, 88888),
    (9, 'ProduccionProdTipo9', 5, 99, 99999),
    (10, 'ProduccionProdTipo10', 4, 101, 10110),
    (11, 'ProduccionProdTipo11', 10, 11, 11111),
    (12, 'ProduccionProdTipo12', 8, 121, 12112),
    (13, 'ProduccionProdTipo13', 9, 131, 13113),
    (14, 'ProduccionProdTipo14', 10, 141, 14114),
    (15, 'ProduccionProdTipo15', 1, 151, 15115),
    (16, 'ProduccionProdTipo16', 3, 161, 16116);
