package ecommerce.entity.jwt;

import org.springframework.stereotype.Component;

@Component
public class JwtHeader{

	String contentType;
	String algorithm;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
}