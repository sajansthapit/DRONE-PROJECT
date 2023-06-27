package com.sajansthapit.shipmentservice.util.http;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sajansthapit.shipmentservice.constants.Messages;
import com.sajansthapit.shipmentservice.exceptionhandlers.exceptions.HttpFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class HttpClientWrapper {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public HttpClientWrapper(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public <T> T get(String url, Map<String, String> queryParams, Class<T> clazz, String exceptionMessage, String serviceName) {
        try {
            UriComponentsBuilder componentsBuilder = UriComponentsBuilder.newInstance();
            UriBuilder uriBuilder;
            if (queryParams != null) {
                MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
                for (Map.Entry<String, String> entry : queryParams.entrySet())
                    multiValueMap.put(entry.getKey(), Collections.singletonList(entry.getValue()));

                uriBuilder = componentsBuilder.uri(new URI(url))
                        .queryParams(multiValueMap);
            } else
                uriBuilder = componentsBuilder.uri(new URI(url));

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));


            log.info(url);
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    uriBuilder.build(),
                    HttpMethod.GET,
                    entity,
                    String.class);

            return objectMapper.readValue(
                    response.getBody(),
                    clazz
            );
        } catch (HttpClientErrorException exception) {
            if (exception.getStatusCode().value() == HttpStatus.NOT_FOUND.value())
                throw new EntityNotFoundException(exceptionMessage);
            throw new HttpFailedException(exception.getMessage());
        } catch (Exception e) {
            throw new HttpFailedException(MessageFormat.format(Messages.FAILED_TO_CALL_SERVICE, serviceName));
        }
    }

    public <T> T put(String url, String body, Map<String, String> queryParams, Class<T> clazz, String exceptionMessage) {
        try {

            MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
            if (queryParams != null) {
                for (Map.Entry<String, String> entry : queryParams.entrySet())
                    multiValueMap.put(entry.getKey(), Collections.singletonList(entry.getValue()));
            }

            UriComponentsBuilder componentsBuilder = UriComponentsBuilder.newInstance();
            UriBuilder uriBuilder = componentsBuilder.uri(new URI(url))
                    .queryParams(multiValueMap);

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_JSON);
            log.info(url);
            log.info(body);
            HttpEntity<String> entity = new HttpEntity<String>(body, headers);

            ResponseEntity<String> response = restTemplate.exchange(uriBuilder.build(),
                    HttpMethod.PUT,
                    entity,
                    String.class);

            return objectMapper.readValue(
                    response.getBody(),
                    clazz
            );
        } catch (Exception e) {
            throw new HttpFailedException(exceptionMessage);
        }
    }
}
