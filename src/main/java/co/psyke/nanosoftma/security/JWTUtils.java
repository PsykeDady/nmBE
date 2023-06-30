package co.psyke.nanosoftma.security;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtils {
	private String jwtSigningKey = "secret"; 
	public final Long EXPIRATION_TIME=1000*60*5L;

	public String extractUsername (String token){
		return extractClaim(token, Claims::getSubject);
	}

	public LocalDateTime extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration); 
	}

	public boolean hasClaim(String token, String claim){

		return true; 
	}

	public String generateToken(UserDetails userdetails){
		return "";
	}

	public String generateToken(UserDetails userdetails, Map<String,Object> claims){
		return "";
	}

	
	public String createToken(Map<String,Object> claims, UserDetails userDetails){
		return Jwts
					.builder()
					.setClaims(claims)
					.setSubject(userDetails.getUsername())
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.HS256,jwtSigningKey).compact();
		;
	}

	public Boolean isTokenValid(String token, UserDetails userDetails) {
		final String username= extractUsername(token);
		final Boolean valid = username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
}
