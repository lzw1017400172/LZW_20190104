package com.lzw.core.util;

@SuppressWarnings("serial")
class InvalidBase64CharacterException extends IllegalArgumentException {

	InvalidBase64CharacterException(String message) {
		super(message);
	}

}