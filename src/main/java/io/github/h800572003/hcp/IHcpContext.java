package io.github.h800572003.hcp;

import org.apache.http.impl.client.CloseableHttpClient;

public interface IHcpContext {

	HcpOption getOption();

	CloseableHttpClient getClient();

	String getAuthorization();

}