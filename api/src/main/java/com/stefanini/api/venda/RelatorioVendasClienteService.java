package com.stefanini.api.venda;

import com.stefanini.api.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatorioVendasClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VendaService vendaService;

    public RelatorioVendasClienteDTO consultarRelatorioVendasPorCliente(String cpf) {

        return null;
    }
}
