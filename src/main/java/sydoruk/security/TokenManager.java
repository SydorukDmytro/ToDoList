package sydoruk.security;

import java.util.Date;

public class TokenManager {

    private String secretKey;

    public TokenManager(String secretKey) {
        this.secretKey = secretKey;
    }

    public String createToken(TokenPayload tokenPayload){
        String mixedPayload = createMixedTokenPayload((tokenPayload));
        String signature = createSignature(mixedPayload);
        String token = String.format("%s#%s", mixedPayload, signature);
        return token;
    }

    private String createMixedTokenPayload(TokenPayload tokenPayload){
        String timeOfCreation = String.valueOf(tokenPayload.getTimeOfCreation().getTime());
        String id = String.valueOf(tokenPayload.getUserId());
        String email = tokenPayload.getEmail();
        return String.format("%s#%s#%s", id, email, timeOfCreation);
    }

    private String createSignature(String mixedPayload){
        return "" + mixedPayload.charAt(2)
                + mixedPayload.charAt(0)
                + mixedPayload.charAt(2)
                + mixedPayload.charAt(5)
                + mixedPayload.charAt(3)
                + mixedPayload.charAt(mixedPayload.length() - 1);
    }

    public boolean verifyToken(String token){
        TokenPayload payload = extractPayload(token);
        String trustedToken = createToken(payload);
        return token.equals(trustedToken);
    }

    public TokenPayload extractPayload(String token){
        String[] tokenParts = token.split("#");
        Long id = Long.valueOf(tokenParts[0]);
        String email = String.valueOf(tokenParts[1]);
        Date time = new Date(Long.valueOf(tokenParts[2]));
        TokenPayload payload = new TokenPayload(id, email, time);
        return payload;
    }
}
