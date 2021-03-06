package ee.tenman.flashcards.service;

import ee.tenman.flashcards.domain.Card;
import ee.tenman.flashcards.domain.User;
import ee.tenman.flashcards.repository.CardRepository;
import ee.tenman.flashcards.service.dto.CardDTO;
import ee.tenman.flashcards.service.mapper.CardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Card.
 */
@Service
@Transactional
public class CardService {

    private final Logger log = LoggerFactory.getLogger(CardService.class);

    private final CardRepository cardRepository;

    private final CardMapper cardMapper;

    private final UserService userService;

    public CardService(CardRepository cardRepository, CardMapper cardMapper, UserService userService) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
        this.userService = userService;
    }

    /**
     * Save a card.
     *
     * @param cardDTO the entity to save
     * @return the persisted entity
     */
    public CardDTO save(CardDTO cardDTO) {
        log.debug("Request to save Card : {}", cardDTO);
        User user = userService.getUserWithAuthorities();
        if (user != null) {
            cardDTO.setUserId(user.getId());
        }
        Card card = cardMapper.cardDTOToCard(cardDTO);
        card = cardRepository.save(card);
        return cardMapper.cardToCardDTO(card);
    }

    /**
     * Get all the cards.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<CardDTO> findAll() {
        log.debug("Request to get all Cards");
        return cardRepository.findByUserIsCurrentUser().stream()
            .map(cardMapper::cardToCardDTO)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one card by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public CardDTO findOne(Long id) {
        log.debug("Request to get Card : {}", id);
        Card card = cardRepository.findOne(id);
        return cardMapper.cardToCardDTO(card);
    }

    /**
     * Delete the  card by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Card : {}", id);
        cardRepository.delete(id);
    }
}
