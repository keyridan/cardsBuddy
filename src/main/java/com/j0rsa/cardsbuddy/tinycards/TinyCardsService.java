package com.j0rsa.cardsbuddy.tinycards;

import com.j0rsa.cardsbuddy.security.SecurityService;
import com.j0rsa.cardsbuddy.tinycards.exception.ParserException;
import com.j0rsa.cardsbuddy.tinycards.model.*;
import org.apache.http.StatusLine;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static com.j0rsa.cardsbuddy.SystemConstants.DECK_CARDS_TEMPLATE;
import static com.j0rsa.cardsbuddy.SystemConstants.FACT_TEMPLATE;
import static com.j0rsa.cardsbuddy.tinycards.TinyCardsParser.parseTo;
import static com.j0rsa.cardsbuddy.tinycards.TinyCardsRequest.*;

@Service
public class TinyCardsService {
    private final SecurityService securityService;

    @Autowired
    public TinyCardsService(SecurityService securityService) {
        this.securityService = securityService;
    }


    private Optional<TinyCardsHttpResponse> loginAndGetCheckedResponse(LoginRequest loginRequest) {
        return post("login")
                .jsonTransportHeader()
                .jsonBodyOf(loginRequest)
                .execute()
                .flatMap(TinyCardsResponse::returnResponse)
                .map(TinyCardsHttpResponse::checkStatus);
    }

    public Optional<TinyCardsLoginResponse> login(LoginRequest loginRequest) {
        return loginAndGetCheckedResponse(loginRequest)
                .map(response -> TinyCardsLoginResponse.builder()
                        .id(parseId(response))
                        .cookies(parseCookies(response))
                        .build()
                );
    }

    public List<DeckInfo> requestDeckInfo() {
        return get(decksInfoUri(securityService.userId()))
                .addCookiesHeaders(securityService.headers())
                .execute()
                .flatMap(TinyCardsResponse::returnResponse)
                .map(TinyCardsHttpResponse::checkStatus)
                .flatMap(TinyCardsHttpResponse::returnContent)
                .flatMap(parseTo(Decks.class))
                .map(Decks::getDecks)
                .orElse(Lists.newArrayList());
    }

    public Optional<Deck> requestDeckForEdit(UUID deckId) {
        return get(String.format(DECK_CARDS_TEMPLATE, deckId))
                .addCookiesHeaders(securityService.headers())
                .execute()
                .flatMap(TinyCardsResponse::returnResponse)
                .map(TinyCardsHttpResponse::checkStatus)
                .flatMap(TinyCardsHttpResponse::returnContent)
                .flatMap(parseTo(Deck.class));
    }

    public Optional<ImageFact> addImage(Details details) {
        return post(FACT_TEMPLATE)
                .addCookiesHeaders(securityService.headers())
                .bodyOf(details)
                .execute()
                .flatMap(TinyCardsResponse::returnResponse)
                .map(TinyCardsHttpResponse::checkStatus)
                .flatMap(TinyCardsHttpResponse::returnContent)
                .flatMap(parseTo(ImageFact.class));
    }

    public Optional<StatusLine> add(UUID deckId, Card card) {
        addImageFactsImagesAndUpdate(card);
        return requestDeckForEdit(deckId)
                .map(deck -> deck.addCard(card))
                .flatMap(deck -> patchDeck(deckId, deck));
    }

    private void addImageFactsImagesAndUpdate(Card card) {
        card.getSides().stream()
                .flatMap(side -> side.getConcepts().stream())
                .map(Concept::getFact)
                .filter(fact -> fact instanceof ImageFact)
                .map(fact -> (ImageFact) fact)
                .forEach(fact -> addImage(fact.getDetails()).ifPresent(newFact -> {
                    fact.setId(newFact.getId());
                    fact.setImageUrl(newFact.getImageUrl());
                }));
    }

    Optional<StatusLine> patchDeck(UUID deckId, Deck deck) {
        return patch(deckUri(deckId))
                .jsonTransportHeader()
                .jsonBodyOf(deck)
                .addCookiesHeaders(securityService.headers())
                .execute()
                .flatMap(TinyCardsResponse::returnResponse)
                .map(TinyCardsHttpResponse::checkStatus)
                .flatMap(TinyCardsHttpResponse::statusLine);
    }

    private String decksInfoUri(String tinyCardsId) {
        return String.format("decks?%s=%s", "userId", tinyCardsId);
    }

    private String deckUri(UUID deckId) {
        return String.format("decks/%s", deckId);
    }

    private Map<String, String> parseCookies(TinyCardsHttpResponse response) {
        return TinyCardsParser.parseAuthenticationHeaders(response.cookieHeaders());
    }

    private String parseId(TinyCardsHttpResponse response) {
        return response.returnContent()
                .flatMap(parseTo(TinyCardsLoginResponse.class))
                .map(TinyCardsLoginResponse::getId)
                .orElseThrow(ParserException::new);
    }
}
