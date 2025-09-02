package com.sleepy.bankmanagement.service;

import com.sleepy.bankmanagement.entity.Card;
import com.sleepy.bankmanagement.repository.CardRepository;
import java.util.List;

public class CardService {

    public Card save(Card card) throws Exception {
        try (CardRepository cardRepository = new CardRepository()) {
            return cardRepository.save(card);
        }
    }

    public Card edit(Card card) throws Exception {
        try (CardRepository cardRepository = new CardRepository()) {
            // Logic must be done within the try block
            if (cardRepository.findById(card.getCardNumber()) != null) {
                return cardRepository.edit(card);
            } else {
                throw new Exception("Card not found");
            }
        }
    }

    public Card deleteById(String id) throws Exception {
        try (CardRepository cardRepository = new CardRepository()) {
            Card cardToDelete = cardRepository.findById(id);
            if (cardToDelete != null) {
                return cardRepository.deleteById(id);
            } else {
                throw new Exception("Card not found");
            }
        }
    }

    public Card findById(String id) throws Exception {
        try (CardRepository cardRepository = new CardRepository()) {
            return cardRepository.findById(id);
        }
    }

    public List<Card> findAll() throws Exception {
        try (CardRepository cardRepository = new CardRepository()) {
            return cardRepository.findAll();
        }
    }
}