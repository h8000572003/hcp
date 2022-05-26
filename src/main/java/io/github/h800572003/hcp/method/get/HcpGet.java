package io.github.h800572003.hcp.method.get;

import org.apache.commons.lang3.StringUtils;

import io.github.h800572003.hcp.exception.HcpBusinessException;

public class HcpGet implements IHcpGet {
	private String path;

	@Override
	public String toHcpPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void check() {
		if (StringUtils.isBlank(path)) {
			throw new HcpBusinessException("path is require");
		}

	}

}
