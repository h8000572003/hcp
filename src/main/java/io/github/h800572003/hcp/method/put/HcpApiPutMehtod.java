package io.github.h800572003.hcp.method.put;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import io.github.h800572003.hcp.HcpOption;
import io.github.h800572003.hcp.IHcpContext;
import io.github.h800572003.hcp.exception.HcpBusinessException;
import io.github.h800572003.hcp.exception.HcpCodeExcepton;
import io.github.h800572003.hcp.method.BaseHcpInfo;
import io.github.h800572003.hcp.method.get.HcpApiMethod;

public class HcpApiPutMehtod implements HcpApiMethod<IHcpPut, BaseHcpInfo> {

	@Override
	public BaseHcpInfo execute(IHcpPut hcpMethod, IHcpContext hcpService) throws HcpCodeExcepton {
		HcpOption option = hcpService.getOption();
		CloseableHttpClient client = hcpService.getClient();
		HttpPut request = new HttpPut(option.getRest() + hcpMethod.toHcpPath());
		request.addHeader("Authorization", hcpService.getAuthorization());
		ByteArrayEntity requestEntity = new ByteArrayEntity(hcpMethod.getPutByte());
		request.setEntity(requestEntity);

		try (CloseableHttpResponse response = client.execute(request)) {
			int statusCode = response.getStatusLine().getStatusCode();
			if (hcpService.getStatusCodeChecker().isOk(hcpMethod, statusCode)) {
				return new BaseHcpInfo(response);
			}
			throw new HcpCodeExcepton(statusCode, response.getStatusLine().getReasonPhrase());
		} catch (ClientProtocolException e) {
			throw new HcpBusinessException("HcpApiDeleteMehtod error ClientProtocolException", e);
		} catch (IOException e) {
			throw new HcpBusinessException("HcpApiDeleteMehtod error IOException", e);
		}
	}

}
