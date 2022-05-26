package io.github.h800572003.hcp;

import org.apache.commons.lang3.StringUtils;

import io.github.h800572003.hcp.exception.HcpBusinessException;
import lombok.Data;

@Data
public class HcpOption {
	private String user;
	private String pwd;
	private String rest;

	public void check() {
		if (StringUtils.isBlank(user)) {
			throw new HcpBusinessException("user is require");
		}
		if (StringUtils.isBlank(pwd)) {
			throw new HcpBusinessException("pwd is require");
		}
		if (StringUtils.isBlank(rest)) {
			throw new HcpBusinessException("rest is require");
		}
	}
}
