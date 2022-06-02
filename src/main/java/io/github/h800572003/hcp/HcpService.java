package io.github.h800572003.hcp;

import java.io.IOException;
import java.io.Serializable;
import java.util.function.Supplier;

import org.apache.http.impl.client.CloseableHttpClient;

import io.github.h800572003.hcp.exception.HcpCodeExcepton;
import io.github.h800572003.hcp.method.BaseHcpInfo;
import io.github.h800572003.hcp.method.IHcpMethod;
import io.github.h800572003.hcp.method.delete.HcpApiDeleteMehtod;
import io.github.h800572003.hcp.method.delete.IHcpDelete;
import io.github.h800572003.hcp.method.get.GetHcpInfo;
import io.github.h800572003.hcp.method.get.HcpApiGetMethod;
import io.github.h800572003.hcp.method.get.HcpApiMethod;
import io.github.h800572003.hcp.method.get.IHcpGet;
import io.github.h800572003.hcp.method.put.HcpApiPutMehtod;
import io.github.h800572003.hcp.method.put.IHcpPut;
import io.github.h800572003.hcp.utils.HcpHttpClientBuilder;
import io.github.h800572003.hcp.utils.HcpUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HcpService implements IHcpService, IHcpContext {
	private final CloseableHttpClient client;
	private final HcpOption option;
	private final HcpApiGetMethod hcpApiGetMethod = new HcpApiGetMethod();
	private final HcpApiDeleteMehtod hcpApiDeleteMehtod = new HcpApiDeleteMehtod();
	private final HcpApiPutMehtod hcpApiPutMehtod = new HcpApiPutMehtod();
	private final Supplier<String> getToken;// 取得token策略
	private final IHcpMethodCheck hcpMethodCheck;

	public HcpService(CloseableHttpClient client, HcpOption option, IHcpMethodCheck hcpMethodCheck) {
		this.client = client;
		this.option = option;
		this.option.check();
		this.getToken = option.getTokens();
		this.hcpMethodCheck = hcpMethodCheck;
	}

	public HcpService(HcpOption option) {
		this(HcpHttpClientBuilder.build(), option, new HcpMethodCheck());
	}

	@Override
	public <O extends Serializable, I extends IHcpMethod> O execute(HcpApiMethod<I, O> api, I cmd)
			throws HcpCodeExcepton {
		return api.execute(cmd, this);
	}

	@Override
	public GetHcpInfo execute(IHcpGet hcpGet) throws HcpCodeExcepton {
		this.hcpMethodCheck.check(hcpGet);
		return this.execute(this.hcpApiGetMethod, hcpGet);
	}

	@Override
	public BaseHcpInfo execute(IHcpDelete hpDelete) throws HcpCodeExcepton {
		this.hcpMethodCheck.check(hpDelete);
		return this.execute(this.hcpApiDeleteMehtod, hpDelete);
	}

	@Override
	public BaseHcpInfo execute(IHcpPut hcpPut) throws HcpCodeExcepton {
		this.hcpMethodCheck.check(hcpPut);
		return this.execute(this.hcpApiPutMehtod, hcpPut);
	}

	@Override
	public HcpOption getOption() {
		return this.option;
	}

	@Override
	public CloseableHttpClient getClient() {
		return this.client;
	}

	@Override
	public void close() {
		try {
			this.client.close();
		} catch (final IOException e) {
			log.info("close error", e);
		}
	}

	@Override
	public String getAuthorization() {
		return this.getToken.get();
	}
}
