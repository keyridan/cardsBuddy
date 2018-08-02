package com.j0rsa.cardsbuddy.tinycards;

import com.j0rsa.cardsbuddy.DefaultData;
import com.j0rsa.cardsbuddy.TestSecurityService;
import com.j0rsa.cardsbuddy.tinycards.model.*;
import org.apache.http.StatusLine;
import org.junit.Test;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.List;
import java.util.Optional;

import static com.j0rsa.cardsbuddy.DefaultData.defaultLoginRequest;
import static com.j0rsa.cardsbuddy.DefaultData.defaultTinyCardsId;
import static com.j0rsa.cardsbuddy.tinycards.model.CardBuilder.aCard;
import static org.assertj.core.api.Assertions.assertThat;

public class TinyCardsServiceTest {
    private TinyCardsService tinyCardsService;

    public TinyCardsServiceTest() {
        tinyCardsService = new TinyCardsService(new TestSecurityService());
    }

    @Test
    public void testLogin() throws Exception {
        LoginRequest testLoginRequest = defaultLoginRequest().build();

        Optional<TinyCardsLoginResponse> response = tinyCardsService.login(testLoginRequest);

        assertThat(response).isPresent();
        assertThat(response.get().getId()).isEqualTo(defaultTinyCardsId());
        assertThat(response.get().getCookies()).hasSize(1);
    }

    @Test(expected = BadCredentialsException.class)
    public void testLoginWithAnotherPassword() throws Exception {
        LoginRequest testLoginRequest = defaultLoginRequest()
                .password("testNotValidPassword").build();

        tinyCardsService.login(testLoginRequest);
    }

    @Test
    public void testRequestDeckInfo() throws Exception {
        List<DeckInfo> deckInfos = tinyCardsService.requestDeckInfo();

        assertThat(deckInfos).isNotEmpty();
    }

    @Test
    public void testRequestDeckForEdit() throws Exception {
        Optional<Deck> deck = tinyCardsService.requestDeckForEdit(DefaultData.defaultDecksId());

        assertThat(deck).isPresent();
    }

    @Test
    public void testAddCard() throws Exception {
        Card card = aCard()
                .frontSideFact("die Karte")
                .backSideFact("card")
                .build();

        Optional<StatusLine> statusLine = tinyCardsService.add(DefaultData.defaultDecksId(), card);

        assertThat(statusLine).isPresent();
        assertThat(statusLine.get().getStatusCode()).isEqualTo(200);
    }

    @Test
    public void testPatchDeck() throws Exception {
        Deck deck = defaultDeck();

        Optional<StatusLine> statusLine = tinyCardsService.patchDeck(DefaultData.defaultDecksId(), deck);

        assertThat(statusLine).isPresent();
        assertThat(statusLine.get().getStatusCode()).isEqualTo(200);
    }

    private Deck defaultDeck() {
        Card card1 = aCard()
                .frontSideFact("die Prüfung")
                .backSideFact("test")
                .build();
        Card card2 = aCard()
                .frontSideFact("prüfen")
                .backSideFact("check")
                .build();
        Deck deck = new Deck();
        deck.setName("TestDeck");
        deck.setIsPrivate(true);
        deck.setShareable(true);
        deck.addCards(card1, card2);
        return deck;
    }
}