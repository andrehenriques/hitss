package br.com.globalhitss.claro.helper;

public final class RedisKeysHelper {
	private static final String AUTHTOKEN_KEY_PARTNER = "authtoken:%s";
	private static final String ACCOUNT_KEY_PARTNER = "account:%s";
	private static final String AUTHTOKENS_KEY_PARTNER = "account:%s:authtokens";
	
	public static String generateAuthtokenKey(String authtoken) {
		return String.format(AUTHTOKEN_KEY_PARTNER, authtoken);
	}
	
	public static String generateAuthtokensKey(String account) {
		return String.format(AUTHTOKENS_KEY_PARTNER, account);
	}
	
	public static String generateAccountKey(String account) {
		return String.format(ACCOUNT_KEY_PARTNER, account);
	}
}
