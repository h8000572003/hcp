package io.github.h800572003.hcp.method.get;

import org.apache.commons.lang3.StringUtils;

import io.github.h800572003.hcp.exception.HcpBusinessException;
import io.github.h800572003.hcp.method.PathBuilder;

public class HcpGet implements IHcpGet {
	private String path;

	public HcpGet(PathBuilder pathBuilder) {
		this.path = pathBuilder.to();
	}

	public HcpGet(String path) {
		this.path = path;
	}

	@Override
	public String toHcpPath() {
		return path;
	}


}
