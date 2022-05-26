package io.github.h800572003.hcp.utils;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("deprecation")
@Slf4j
public class HcpHttpClientBuilder {

	public static CloseableHttpClient build() {
		try {

			SSLContext sslContext = SSLContextBuilder.create().useProtocol(SSLConnectionSocketFactory.SSL)
					.loadTrustMaterial((x, y) -> true).build();

			return HttpClientBuilder.create().setSslcontext(sslContext)//
					.setSSLHostnameVerifier(new NoopHostnameVerifier())//
					.setConnectionTimeToLive(70, TimeUnit.SECONDS)//
					.setMaxConnTotal(100).build();
		}

		catch (Exception e) {
			log.error("get CloseableHttpClient ", e);
			return null;
		}
	}

}
