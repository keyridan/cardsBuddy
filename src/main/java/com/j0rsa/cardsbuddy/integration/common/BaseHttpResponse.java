package com.j0rsa.cardsbuddy.integration.common;

import com.j0rsa.cardsbuddy.integration.common.exception.BadRequestException;
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
public abstract class BaseHttpResponse<T extends BaseHttpResponse> {
    private HttpResponse response;

    protected BaseHttpResponse(HttpResponse httpResponse) {
        this.response = httpResponse;
    }

    public Optional<StatusLine> statusLine() {
        return Optional.of(response.getStatusLine());
    }

    public T checkStatus() {
        return Match(this.response.getStatusLine()).of(
                Case($StatusLine($(401), $()), (statusCode, reason) -> {
                            logStatus(statusCode, reason);
                            throw new BadCredentialsException(reason);
                        }
                ),
                Case($StatusLine($(400), $()), (statusCode, reason) -> {
                            logStatus(statusCode, reason);
                            throw new BadRequestException(reason);
                        }
                ),
                Case($StatusLine($(), $()), (statusCode, reason) -> {
                            logStatus(statusCode, reason);
                            return (T) this;
                        }
                )
        );
    }

    public List<String> cookieHeaders() {
        return Lists.newArrayList(response.getHeaders(TINY_CARDS_COOKIE_HEADER))
                .stream()
                .map(NameValuePair::getValue)
                .peek(value -> log.debug("cookieHeaders: " + value))
                .collect(Collectors.toList());
    }

    public Optional<String> returnContent() {
        return Try.of(() -> EntityUtils.toString(response.getEntity()))
                .onFailure(e -> log.error("Error", e))
                .peek(content -> log.debug("returnContent: " + content))
                .toJavaOptional();
    }

    private void logStatus(Integer statusCode, String reason) {
        log.debug("code: " + statusCode + " reason: " + reason);
    }

}
