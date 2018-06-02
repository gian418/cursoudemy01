package com.giancarlohaack.cursoudemy01.services;

import com.giancarlohaack.cursoudemy01.domain.Cliente;
import com.giancarlohaack.cursoudemy01.domain.ItemPedido;
import com.giancarlohaack.cursoudemy01.domain.PagamentoComBoleto;
import com.giancarlohaack.cursoudemy01.domain.Pedido;
import com.giancarlohaack.cursoudemy01.domain.enums.EstadoPagamento;
import com.giancarlohaack.cursoudemy01.repositories.ClienteRepository;
import com.giancarlohaack.cursoudemy01.repositories.ItemPedidoRepository;
import com.giancarlohaack.cursoudemy01.repositories.PagamentoRepository;
import com.giancarlohaack.cursoudemy01.repositories.PedidoRepository;
import com.giancarlohaack.cursoudemy01.repositories.ProdutoRepository;
import com.giancarlohaack.cursoudemy01.security.UserSS;
import com.giancarlohaack.cursoudemy01.services.exceptions.AuthorizationException;
import com.giancarlohaack.cursoudemy01.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private BoletoService boletoService;
    @Autowired
    private PagamentoRepository  pagamentoRepository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private EmailService emailService;

    public Pedido find(Integer id){
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()
                )
        );
    }

    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);

        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }

        obj = pedidoRepository.save(obj);
        pagamentoRepository.save(obj.getPagamento());

        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0D);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(obj);
        }

        itemPedidoRepository.saveAll(obj.getItens());
        emailService.sendOrderConfirmationEmail(obj);
        return obj;
    }

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Cliente cliente = clienteService.find(user.getId());
        return pedidoRepository.findByCliente(cliente, pageRequest);
    }

}
