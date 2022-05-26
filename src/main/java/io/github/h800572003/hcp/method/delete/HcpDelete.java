package io.github.h800572003.hcp.method.delete;

import org.apache.commons.lang3.StringUtils;

import io.github.h800572003.hcp.exception.HcpBusinessException;

public class HcpDelete implements IHcpDelete {
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
