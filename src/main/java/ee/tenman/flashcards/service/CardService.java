package ee.tenman.flashcards.service;

import ee.tenman.flashcards.domain.Card;
import ee.tenman.flashcards.domain.User;
import ee.tenman.flashcards.repository.CardRepository;
import ee.tenman.flashcards.service.dto.CardDTO;
import ee.tenman.flashcards.service.mapper.CardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * Service Implementation for managing Card.
 */
@Service
@Transactional
public class CardService {

    private final Logger log = LoggerFactory.getLogger(CardService.class);

    private final CardRepository cardRepository;

    private final CardMapper cardMapper;

    @Inject
    private UserService userService;

    public CardService(CardRepository cardRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.cardMapper = cardMapper;
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
        CardDTO result = cardMapper.cardToCardDTO(card);
        return result;
    }

    /**
     * Get all the cards.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<CardDTO> findAll() {
        log.debug("Request to get all Cards");
        List<CardDTO> result = cardRepository.findByUserIsCurrentUser().stream()
            .map(cardMapper::cardToCardDTO)
            .collect(Collectors.toCollection(LinkedList::new));
        return result;
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
        CardDTO cardDTO = cardMapper.cardToCardDTO(card);
        return cardDTO;
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
