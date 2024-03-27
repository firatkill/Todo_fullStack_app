package com.todoApp.todoApp.Security.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.impl.ClaimsHolder;
import com.auth0.jwt.impl.ClaimsSerializer;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.todoApp.todoApp.Model.Entity.User;
import com.todoApp.todoApp.Security.SecurityExceptions.InvalidTokenException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JWTService {


    private final Algorithm algorithm=Algorithm.HMAC256("!sE3N?5'v&/'i<]`<c4a3SN#oKelyNcQ");

    public JWTService() throws NoSuchAlgorithmException {
    }


    public String generateToken(UserDetails user){



        String token= JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+10+60*1000))
                .withClaim("authorities",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);


        return "Bearer ".concat(token);

    }
    public Boolean validateToken(String token, UserDetails userDetails) throws InvalidTokenException{
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            String email = extractUser(token);
            Date expirationDate = extractExpiration(token);
            return userDetails.getUsername().equals(email) && !expirationDate.before(new Date());
        } catch (JWTVerificationException exception){
            throw new InvalidTokenException("token is invalid.");
        }
    }
    private Date extractExpiration(String token) {
        return JWT.decode(token).getExpiresAt();

    }
    public String extractUser(String token) {
        return JWT.decode(token).getSubject();
    }
    public static SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        return keyGenerator.generateKey();
    };
    public static String convertSecretKeyToString(SecretKey secretKey) {
        byte[] rawData = secretKey.getEncoded();
        String encodedKey = Base64.getEncoder().encodeToString(rawData);
        return encodedKey;
    }


}
