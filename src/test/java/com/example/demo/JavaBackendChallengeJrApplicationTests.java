package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dto.ProdutoRequestDto;
import com.example.demo.dto.ProdutoResponseDto;
import com.example.demo.enums.TipoProduto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JavaBackendChallengeJrApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	private static UUID id;

	@Test
	@Order(1)
	void cadastrarProdutoTest() throws Exception {

		var faker = new Faker(Locale.forLanguageTag("pt-BR"));

		var request = new ProdutoRequestDto();
		request.setNome(faker.commerce().productName());
		request.setTipo(faker.options().option(TipoProduto.class));
		request.setPrecoUnitario(faker.number().randomDouble(2, 1, 1000));

		var result = mockMvc.perform(
				post("/api/produtos").contentType("application/json").content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk()).andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		var response = objectMapper.readValue(content, ProdutoResponseDto.class);

		assertNotNull(response.getId()); // Verificando se um ID foi gerado para o produto
		assertEquals(response.getNome(), request.getNome()); // Comparando o nome da resposta com o da requisição
		assertEquals(response.getPrecoUnitario(), request.getPrecoUnitario()); // Comparando o preço da resposta com o
																				// da requisição
		assertEquals(response.getTipo(), request.getTipo()); // Comparando o tipo da resposta com o da requisição

		id = response.getId();
	}

	@Test
	@Order(2)
	void atualizarProdutoTest() throws Exception {

		var faker = new Faker(Locale.forLanguageTag("pt-BR"));

		var request = new ProdutoRequestDto();
		request.setNome(faker.commerce().productName());
		request.setTipo(faker.options().option(TipoProduto.class));
		request.setPrecoUnitario(faker.number().randomDouble(2, 1, 1000));

		var result = mockMvc.perform(put("/api/produtos/" + id).contentType("application/json")
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk()).andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		var response = objectMapper.readValue(content, ProdutoResponseDto.class);

		assertEquals(response.getId(), id);
		assertEquals(response.getNome(), request.getNome());
		assertEquals(response.getTipo(), request.getTipo());
		assertEquals(response.getPrecoUnitario(), request.getPrecoUnitario());
	}

	@Test
	@Order(3)
	void obterProdutoPorIdTest() throws Exception {

		var result = mockMvc.perform(get("/api/produtos/" + id)).andExpect(status().isOk()).andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		var response = objectMapper.readValue(content, ProdutoResponseDto.class);

		assertEquals(response.getId(), id);
		assertNotNull(response.getNome());
		assertNotNull(response.getTipo());
		assertNotNull(response.getPrecoUnitario());
	}

	@Test
	@Order(4)
	void consultarProdutosTest() throws Exception {

		var result = mockMvc.perform(get("/api/produtos")).andExpect(status().isOk()).andReturn();

		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		var response = objectMapper.readValue(content, new TypeReference<List<ProdutoResponseDto>>() {
		});

		response.stream().filter(produto -> produto.getId().equals(id)).findFirst()
				.orElseThrow(() -> new AssertionError("Produto com ID " + id + " não encontrado na lista."));
	}

	@Test
	@Order(5)
	void excluirProdutoTest() throws Exception {
		
		var result = mockMvc.perform(delete("/api/produtos/" + id)).andExpect(status().isOk()).andReturn();
		
		var content = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		assertTrue(content.contains("Produto excluído com sucesso!"));
	}
}
