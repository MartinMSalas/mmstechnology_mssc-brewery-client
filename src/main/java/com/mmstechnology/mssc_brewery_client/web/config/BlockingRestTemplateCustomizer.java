package com.mmstechnology.mssc_brewery_client.web.config;


import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties(prefix = "mmstechnology")
public class BlockingRestTemplateCustomizer implements RestTemplateCustomizer {


    private final Integer maxtotalconnections;

    private final Integer defaultmaxtotalconnections;

    private final Integer connectionrequesttimeout;

    private final Integer sockettimeout;


    public BlockingRestTemplateCustomizer(@Value("${mmstechnology.maxtotalconnections}") Integer maxtotalconnections,
                                          @Value("${mmstechnology.defaultmaxtotalconnections}") Integer defaultmaxtotalconnections,
                                          @Value("${mmstechnology.connectionrequesttimeout}") Integer connectionrequesttimeout,
                                          @Value("${mmstechnology.sockettimeout}") Integer sockettimeout) {
        this.maxtotalconnections = maxtotalconnections;
        this.defaultmaxtotalconnections = defaultmaxtotalconnections;
        this.connectionrequesttimeout = connectionrequesttimeout;
        this.sockettimeout = sockettimeout;
    }


    private ClientHttpRequestFactory clientHttpRequestFactory() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(100);
        cm.setDefaultMaxPerRoute(20);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(Timeout.ofMilliseconds(3_000))
                .setResponseTimeout(Timeout.ofMilliseconds(3_000))
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        restTemplate.setRequestFactory(clientHttpRequestFactory());
    }
}
