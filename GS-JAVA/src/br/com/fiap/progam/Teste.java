package br.com.fiap.progam;

import java.io.IOException;
import br.com.fiap.model.Endereco;
import br.com.fiap.services.ViaCepServices;

public class Teste {

	public static void main(String[] args) throws ClassNotFoundException, IOException {

		ViaCepServices viaCepServices = new ViaCepServices();

		try {
			Endereco endereco = viaCepServices.getEndereco("01310914");

			String ddd = endereco.getDdd();
			String uf = endereco.getUf();

			System.out.println(endereco + "\n");
			System.out.println(ddd + "\n");
			System.out.println(uf + "\n");

		} finally {

		}

	}

}
