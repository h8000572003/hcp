package io.github.h800572003.hcp;

import org.apache.http.impl.client.CloseableHttpClient;

import io.github.h800572003.hcp.method.IStatusCodeChecker;

public interface IHcpContext {

	HcpOption getOption();

	CloseableHttpClient getClient();

	String getAuthorization();

	IStatusCodeChecker getStatusCodeChecker();

}