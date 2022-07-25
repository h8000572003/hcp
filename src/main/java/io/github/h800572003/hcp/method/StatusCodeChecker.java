package io.github.h800572003.hcp.method;

public class StatusCodeChecker implements IStatusCodeChecker {

	@Override
	public boolean isOk(IHcpMethod hcpMehtod, int code) {
		return code >= 200 && code <= 299;
	}

}
