package ee.tenman.flashcards.service.mapper;

import ee.tenman.flashcards.domain.*;
import ee.tenman.flashcards.service.dto.CardDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Card and its DTO CardDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, })
public interface CardMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    CardDTO cardToCardDTO(Card card);

    List<CardDTO> cardsToCardDTOs(List<Card> cards);

    @Mapping(source = "userId", target = "user")
    Card cardDTOToCard(CardDTO cardDTO);

    List<Card> cardDTOsToCards(List<CardDTO> cardDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Card cardFromId(Long id) {
        if (id == null) {
            return null;
        }
        Card card = new Card();
        card.setId(id);
        return card;
    }
    

}
