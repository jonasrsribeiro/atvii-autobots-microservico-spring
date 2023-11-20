package com.autobots.automanager.modelos;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.ClienteControle;
import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.servicos.AdicionadorLink;
import com.autobots.automanager.servicos.AdicionadorLinkFactory;

@Component
public class AdicionadorLinkCliente extends AdicionadorLinkFactory<Cliente> implements AdicionadorLink<Cliente> {

	@Override
    protected AdicionadorLink<Cliente> criarAdicionadorLink(Class<?> classe) {
        if (classe.equals(Cliente.class)) {
            return new AdicionadorLinkCliente();
        }

		return null;

	}

	@Override
    public void adicionarLink(Cliente objeto) {
        adicionarLinkCliente(objeto);
    }

	@Override
    public void adicionarLink(List<Cliente> lista) {
        for (Cliente cliente : lista) {
            adicionarLinkCliente(cliente);
        }
    }

	private void adicionarLinkCliente(Cliente cliente) {
        long id = cliente.getId();

        Link linkProprio = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteControle.class).obterCliente(id))
                .withSelfRel().withType("GET").withRel("self").withTitle("Obter detalhes do cliente");

        Link linkListaClientes = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClienteControle.class).obterClientes())
                .withRel("todosClientes").withType("GET").withTitle("Obter lista de clientes");

        Link linkNovoCliente = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ClienteControle.class).cadastrarCliente(new Cliente()))
                .withRel("novoCliente").withType("POST").withTitle("Criar um novo cliente");

        Link linkAtualizar = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ClienteControle.class).atualizarCliente(cliente))
                .withRel("atualizar").withType("PUT").withTitle("Atualizar detalhes do cliente");

        Link linkExcluir = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ClienteControle.class).excluirCliente(cliente)).withRel("excluir")
                .withType("DELETE").withTitle("Excluir cliente");

        cliente.add(linkProprio, linkListaClientes, linkNovoCliente, linkAtualizar, linkExcluir);
    }

	public void adicionarLinkListaClientes(List<Cliente> clientes) {
        Link linkListaClientes = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ClienteControle.class).obterClientes()).withRel("clientes")
                .withType("GET").withTitle("Obter lista de clientes");

        Link linkNovoCliente = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ClienteControle.class).cadastrarCliente(new Cliente()))
                .withRel("novoCliente").withType("POST").withTitle("Criar um novo cliente");

        clientes.forEach(cliente -> cliente.add(linkListaClientes, linkNovoCliente));
    }
}
