package com.autobots.automanager.modelos;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.EnderecoControle;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.servicos.AdicionadorLink;
import com.autobots.automanager.servicos.AdicionadorLinkFactory;

@Component
public class AdicionadorLinkEndereco extends AdicionadorLinkFactory<Endereco> implements AdicionadorLink<Endereco> {

	@Override
    protected AdicionadorLink<Endereco> criarAdicionadorLink(Class<?> classe) {
        if (classe.equals(Endereco.class)) {
            return new AdicionadorLinkEndereco();
        }

		return null;

	}

	@Override
    public void adicionarLink(Endereco objeto) {
        adicionarLinkEndereco(objeto);
    }

	@Override
    public void adicionarLink(List<Endereco> lista) {
        for (Endereco endereco : lista) {
            adicionarLinkEndereco(endereco);
        }
    }

	private void adicionarLinkEndereco(Endereco endereco) {
        long id = endereco.getId();

        Link linkProprio = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnderecoControle.class).obterEndereco(id))
                .withSelfRel().withType("GET").withRel("self").withTitle("Obter detalhes do endereco");

        Link linkListaEnderecos = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EnderecoControle.class).obterEnderecos())
                .withRel("todosEnderecos").withType("GET").withTitle("Obter lista de clientes");

        Link linkNovoEndereco = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EnderecoControle.class).cadastrarEndereco(new Endereco()))
                .withRel("novoEndereco").withType("POST").withTitle("Criar um novo endereco");

        Link linkAtualizar = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EnderecoControle.class).atualizarEndereco(endereco))
                .withRel("atualizar").withType("PUT").withTitle("Atualizar detalhes do endereco");

        Link linkExcluir = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EnderecoControle.class).excluirEndereco(endereco)).withRel("excluir")
                .withType("DELETE").withTitle("Excluir endereco");

        endereco.add(linkProprio, linkListaEnderecos, linkNovoEndereco, linkAtualizar, linkExcluir);
    }

	public void adicionarLinkListaEnderecos(List<Endereco> clientes) {
        Link linkListaEnderecos = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EnderecoControle.class).obterEnderecos()).withRel("enderecos")
                .withType("GET").withTitle("Obter lista de enderecos");

        Link linkNovoEndereco = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EnderecoControle.class).cadastrarEndereco(new Endereco()))
                .withRel("novoEndereco").withType("POST").withTitle("Criar um novo endereco");

        clientes.forEach(endereco -> endereco.add(linkListaEnderecos, linkNovoEndereco));
    }
}
