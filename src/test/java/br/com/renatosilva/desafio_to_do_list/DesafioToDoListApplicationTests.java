package br.com.renatosilva.desafio_to_do_list;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.renatosilva.desafio_to_do_list.entity.Todo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DesafioToDoListApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testeCreateTodoSucess() {
		var todo = new Todo("todo 1", "desc todo 1", false, 1);

		webTestClient
			.post()
			.uri("/todos")
			.bodyValue(todo)
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.jsonPath("$").isArray()
			.jsonPath("$.length()").isEqualTo(1)
			.jsonPath("$[0].nome").isEqualTo(todo.getNome())
			.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
			.jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
			.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
	}

	@Test
	void testeCreateTodoFailure() {
	  webTestClient
			.post()
			.uri("/todos")
			.bodyValue(
			  new Todo("", "", false, 0))
			.exchange()
			.expectStatus().isBadRequest();
	}

}
