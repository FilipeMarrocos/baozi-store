package br.com.baozi.controller;

import br.com.baozi.model.Cliente;
import br.com.baozi.model.Pedido;
import br.com.baozi.model.PedidoRequest;
import br.com.baozi.model.Produto;
import br.com.baozi.repository.ClienteRepository;
import br.com.baozi.repository.PedidoRepository;
import br.com.baozi.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoController(PedidoRepository pedidoRepository,
                            ClienteRepository clienteRepository,
                            ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody PedidoRequest req) {
        if (req.getClienteId() == null || req.getProdutoId() == null
                || req.getQuantidade() == null || req.getQuantidade() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Informe clienteId, produtoId e quantidade maior que zero");
        }

        Cliente cliente = clienteRepository.findById(req.getClienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        Produto produto = produtoRepository.findById(req.getProdutoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setProduto(produto);
        pedido.setQuantidade(req.getQuantidade());

        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoRepository.save(pedido));
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        buscarPorId(id);
        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
