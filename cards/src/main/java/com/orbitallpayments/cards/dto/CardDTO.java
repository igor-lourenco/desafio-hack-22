package com.orbitallpayments.cards.dto;

import java.io.Serializable;

import com.orbitallpayments.cards.entities.Card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String cardNumber;
	private String embossName;
	private String customerName;
	private String documentNumber;
	private String motherName;
	private String address;
	private String city;
	
	public CardDTO(Card entity) {
		id = entity.getId();
		cardNumber = entity.getCardNumber();
		embossName = entity.getEmbossName();
		customerName = entity.getCustomerName();
		documentNumber = entity.getDocumentNumber();
		motherName = entity.getMotherName();
		address = entity.getAddress();
		city = entity.getCity();
	}
}
