package client;

import java.net.MalformedURLException;
  
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory; 
import org.springframework.web.client.RestTemplate;

import lombok.Getter;

@Configuration
public class RestTemplateConfig {
	
	@Bean
    public PoolingHttpClientConnectionManager connectionManager() throws MalformedURLException { 
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(100);
        connectionManager.setValidateAfterInactivity(3000); 
        return connectionManager;
    } 
	
	@Bean
    public RequestConfig requestConfig() throws MalformedURLException { 
		return RequestConfig.custom()
                .setConnectionRequestTimeout(3000)
                .setConnectTimeout(3000)
                .setSocketTimeout(1000)
                .build();
    } 
	
	@Bean
    public SocketConfig socketConfig() throws MalformedURLException { 
		return SocketConfig.custom()
                .setSoKeepAlive(true)
                .setSoReuseAddress(true)
                .setTcpNoDelay(true)
                .build(); 
    } 
	
	@Bean
	public CloseableHttpClient httpClient(PoolingHttpClientConnectionManager connectionManager, 
											RequestConfig requestConfig, SocketConfig socketConfig) {  
	        return HttpClientBuilder.create()
	                .setConnectionManager(connectionManager)
	                .setDefaultSocketConfig(socketConfig)
	                .setDefaultRequestConfig(requestConfig)
	                .build();
    } 
 
    @Bean
    public RestTemplate restTemplate(CloseableHttpClient httpClient) {
    	 HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
    	 requestFactory.setReadTimeout(5000);       
    	 requestFactory.setConnectTimeout(3000);   
    	 requestFactory.setHttpClient(httpClient);  
         return new RestTemplate(requestFactory);
    } 
}
