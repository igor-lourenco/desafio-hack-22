package com.orbitallpayments.cards.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.orbitallpayments.cards.dto.CardDTO;
import com.orbitallpayments.cards.entities.Card;
import com.orbitallpayments.cards.repositories.CardRepository;
import com.orbitallpayments.cards.services.exceptions.DatabaseException;
import com.orbitallpayments.cards.services.exceptions.EntityNotFoundException;
import com.orbitallpayments.cards.services.exceptions.ResourceNotFoundException;

@Service
public class CardService {

	@Autowired
	private CardRepository repository;
	
	@Transactional(readOnly = true)
	public List<CardDTO> findAll() {
		List<Card> entity = repository.findAll();
		return entity.stream().map(c -> new CardDTO(c)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public CardDTO findById(Long id) {
		Optional<Card> obj = repository.findById(id); 
		Card entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id não existe"));
		return new CardDTO(entity); 
	}
	
	@Transactional
	public CardDTO insert(CardDTO dto) {
		Card entity = new Card(); 
		copyDtoEntity(dto, entity);
		return new CardDTO(entity); 
	}

	@Transactional
	public CardDTO update(Long id, CardDTO dto) {
		try {
			Card entity = repository.getById(id);
			copyDtoEntity(dto, entity);
			return new CardDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id não encontrado: " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("id não existe: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Violação de integridade no banco");
		}
	}
	
	private void copyDtoEntity(CardDTO dto, Card entity) {
		entity.setCardNumber(dto.getCardNumber());
		entity.setEmbossName(dto.getEmbossName());
		entity.setCustomerName(dto.getCustomerName());
		entity.setDocumentNumber(dto.getDocumentNumber());
		entity.setMotherName(dto.getMotherName());
		entity.setAddress(dto.getAddress());
		entity.setCity(dto.getCity());
		entity = repository.save(entity); 
	}
}
