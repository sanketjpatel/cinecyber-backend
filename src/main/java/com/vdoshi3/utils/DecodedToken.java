package com.vdoshi3.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DecodedToken {
	private String id, subject, issuer;
	private long expiration;
}
