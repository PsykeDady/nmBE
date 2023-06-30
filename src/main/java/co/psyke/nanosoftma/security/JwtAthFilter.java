package co.psyke.nanosoftma.security;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAthFilter extends OncePerRequestFilter {+

	public final String BEARER = "Bearer ";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);

		if(authHeader==null || ! authHeader.startsWith("Bearer")){
			filterChain.doFilter(request, response);
		}

		final String jwtToken=authHeader.substring(BEARER.length()).trim();

		final String userEmail="psdady@msn.com";

		if(userEmail != null && SecurityContextHolder.getContext().getAuthentication()== null){
			UserDetails userDetails = CustomUserDetail.loadUserByUsername(userEmail);
			final boolean isTokenValid = true;

			if(isTokenValid){
				UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
