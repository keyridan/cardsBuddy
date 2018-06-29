package com.j0rsa.cardsbuddy.tinycards;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;
import org.assertj.core.util.Lists;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.j0rsa.cardsbuddy.PatternMatchingUtils.$StatusLine;
import static com.j0rsa.cardsbuddy.SystemConstants.TINY_CARDS_COOKIE_HEADER;
import static io.vavr.API.*;

@Slf4j
class TinyCardsHttpResponse {
    private HttpResponse response;

    private TinyCardsHttpResponse(HttpResponse httpResponse) {
        this.response = httpResponse;
    }

    static TinyCardsHttpResponse create(HttpResponse httpResponse) {
        return new TinyCardsHttpResponse(httpResponse);
    }

    Optional<StatusLine> statusLine() {
        return Optional.of(response.getStatusLine());
    }

    TinyCardsHttpResponse checkStatus() {
        return Match(this.response.getStatusLine()).of(
                Case($StatusLine($(401), $()), (statusCode, reason) -> {
                            logStatus(statusCode, reason);
                            throw new BadCredentialsException(reason);
                        }
                ),
                Case($StatusLine($(), $()), (statusCode, reason) -> {
                            logStatus(statusCode, reason);
                            return this;
                        }
                )
        );
    }

    List<String> cookieHeaders() {
        return Lists.newArrayList(response.getHeaders(TINY_CARDS_COOKIE_HEADER))
                .stream()
                .map(NameValuePair::getValue)
                .peek(value -> log.debug("cookieHeaders: " + value))
                .collect(Collectors.toList());
    }

    Optional<String> returnContent() {
        return Try.of(() -> EntityUtils.toString(response.getEntity()))
                .onFailure(e -> log.info(e.toString()))
                .peek(content -> log.info("returnContent: " + content))
                .toJavaOptional();
    }

    private void logStatus(Integer statusCode, String reason) {
        log.info("code: " + statusCode + " reason: " + reason);
    }
}
