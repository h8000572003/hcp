package io.github.h800572003.hcp.method.delete;

import org.apache.commons.lang3.StringUtils;

import io.github.h800572003.hcp.exception.HcpBusinessException;
import io.github.h800572003.hcp.method.PathBuilder;

public class HcpDelete implements IHcpDelete {
	private String path;

	public HcpDelete(PathBuilder pathBuilder) {
		super();
		this.path = pathBuilder.to();
	}

	public HcpDelete(String path) {
		super();
		this.path = path;
	}

	@Override
	public String toHcpPath() {
		return path;
	}

	@Override
	public void check() {
		if (StringUtils.isBlank(path)) {
			throw new HcpBusinessException("path is require");
		}

	}
}
