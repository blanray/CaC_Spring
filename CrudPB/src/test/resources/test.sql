INSERT INTO `tipos_producto`
(`tip_id`, `tip_descripcion`)
VALUES
    (1, 'Tipo1'),
    (2, 'Tipo2'),
    (3, 'Tipo3'),
    (4, 'Tipo4'),
    (5, 'Tipo5'),
    (6, 'Tipo6'),
    (7, 'Tipo7'),
    (8, 'Tipo8'),
    (9, 'Tipo9'),
    (10, 'Tipo10');

INSERT INTO `productos`
(`prd_id`, `prd_descripcion`, prd_tip_id, prd_stock, prd_precio)
VALUES
    (1, 'ProdTipo1', 4, 11, 11111),
    (2, 'ProdTipo2', 3, 22, 22222),
    (3, 'ProdTipo3', 8, 33, 33333),
    (4, 'ProdTipo4', 8, 44, 44444),
    (5, 'ProdTipo5', 5, 55, 55555),
    (6, 'ProdTipo6', 9, 66, 66666),
    (7, 'ProdTipo7', 8, 77, 77777),
    (8, 'ProdTipo8', 6, 88, 88888),
    (9, 'ProdTipo9', 5, 99, 99999),
    (10, 'ProdTipo10', 4, 101, 10110),
    (11, 'ProdTipo11', 10, 11, 11111);

