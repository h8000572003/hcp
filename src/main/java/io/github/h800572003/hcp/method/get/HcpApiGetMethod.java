package io.github.h800572003.hcp.method.get;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import io.github.h800572003.hcp.HcpOption;
import io.github.h800572003.hcp.IHcpContext;
import io.github.h800572003.hcp.exception.HcpBusinessException;
import io.github.h800572003.hcp.exception.HcpCodeExcepton;

public class HcpApiGetMethod implements HcpApiMethod<IHcpGet, GetHcpInfo> {

	@Override
	public GetHcpInfo execute(IHcpGet get, IHcpContext hcpService) throws HcpCodeExcepton {
		HcpOption option = hcpService.getOption();
		CloseableHttpClient client = hcpService.getClient();
		HttpGet request = new HttpGet(option.getRest() + get.toHcpPath());
		request.addHeader("Authorization", hcpService.getAuthorization());
		try (CloseableHttpResponse response = client.execute(request)) {
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				return new GetHcpInfo(response);
			}
			throw new HcpCodeExcepton(statusCode, response.getStatusLine().getReasonPhrase());
		} catch (ClientProtocolException e) {
			throw new HcpBusinessException("HcpDownloadInfo error ClientProtocolException", e);
		} catch (IOException e) {
			throw new HcpBusinessException("HcpDownloadInfo error IOException", e);
		}
	}

}
