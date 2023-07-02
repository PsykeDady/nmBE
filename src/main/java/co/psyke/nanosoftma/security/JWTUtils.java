package co.psyke.nanosoftma.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtils {

	@Value("${jjwt.secret.keys}")
	private String jwtSigningKey;

	public final Long EXPIRATION_TIME=TimeUnit.MINUTES.toMillis(1); 

	public <T> T extractClaim(String token, Function<Claims, T> claimResolver){
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSigningKey));
		Claims claim = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
		return claimResolver.apply(claim);
	}

	public String extractUsername (String token){
		return extractClaim(token, Claims::getSubject);
	}

	public LocalDateTime extractExpiration(String token) {
		Date t = extractClaim(token,Claims::getExpiration); 
		return Instant.ofEpochMilli(t.getTime()).atZone((ZoneId.systemDefault())).toLocalDateTime();
	}

	public boolean hasClaim(String token, String claim){

		return true; 
	}

	public String generateToken(UserDetails userdetails){
		Map<String,Object> claims=new HashMap<>();
		return createToken(claims, userdetails);
	}

	public String generateToken(UserDetails userdetails, Map<String,Object> claims){
		return createToken(claims, userdetails);
	}

	
	public String createToken(Map<String,Object> claims, UserDetails userDetails){
		SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSigningKey));
		return Jwts
					.builder()
					.setClaims(claims)
					.setSubject(userDetails.getUsername())
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
					.signWith(key,SignatureAlgorithm.HS256).compact()
		;
	}

	public boolean isTokenExpired(String token) {
		return extractExpiration(token).compareTo(LocalDateTime.now())>0;
	}

	public Boolean isTokenValid(String token, UserDetails userDetails) {
		final String username= extractUsername(token);
		final Boolean valid = username.equals(userDetails.getUsername()) && !isTokenExpired(token);

		return userDetails.getUsername().equals(username) && valid;
	}
}
