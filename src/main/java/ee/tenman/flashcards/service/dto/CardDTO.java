package ee.tenman.flashcards.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Card entity.
 */
public class CardDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 500)
    private String front;

    @NotNull
    @Lob
    private String back;

    private Boolean known;

    private Long userId;

    private String userLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }
    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }
    public Boolean getKnown() {
        return known;
    }

    public void setKnown(Boolean known) {
        this.known = known;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CardDTO cardDTO = (CardDTO) o;

        if ( ! Objects.equals(id, cardDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CardDTO{" +
            "id=" + id +
            ", front='" + front + "'" +
            ", back='" + back + "'" +
            ", known='" + known + "'" +
            '}';
    }
}
