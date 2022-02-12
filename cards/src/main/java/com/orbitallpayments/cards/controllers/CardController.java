package com.orbitallpayments.cards.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.orbitallpayments.cards.dto.CardDTO;
import com.orbitallpayments.cards.services.CardService;

@RestController
@RequestMapping(value = "/cards")
public class CardController {

	@Autowired
	private CardService service;
	
	@GetMapping(value = "/paginationAndSorting")
	public ResponseEntity<Page<CardDTO>> findAll(Pageable pageable){ 
		Page<CardDTO> dto = service.findAllPaged(pageable);  
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	private ResponseEntity<List<CardDTO>> findAll(){
		var entity = service.findAll();
		return ResponseEntity.ok(entity);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CardDTO> findById(@PathVariable Long id){ 
		var dto = service.findById(id);  
		return ResponseEntity.ok().body(dto); 
	}
	
	@PostMapping()
	public ResponseEntity<CardDTO> insert(@RequestBody CardDTO dto) {
		var obj = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<CardDTO> update(@PathVariable Long id, @RequestBody CardDTO dto) {
		var obj = service.update(id, dto);
		return ResponseEntity.ok(obj);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<CardDTO> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
