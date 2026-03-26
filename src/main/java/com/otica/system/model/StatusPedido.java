package com.otica.system.model;

public enum StatusPedido {
    ABERTO,        // criando pedido
    AGUARDANDO_PAGAMENTO,
    PAGO,
    EM_PRODUCAO,   // lente sendo feita
    PRONTO,
    ENTREGUE,
    CANCELADO
}
