package jbr.sboot.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;
import graphql.servlet.GraphQLProvider;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

	@Autowired
	private GraphQLProvider graphQLProvider;

	@PostMapping(value = "/graphql", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getLocations(@RequestBody String query) {
		log.info("Get Data Locations................");
		ExecutionResult execute = graphQLProvider.getGraphQL().execute(query);
		return new ResponseEntity<>(execute, HttpStatus.OK);
	}
}
