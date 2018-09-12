package com.blackcrowsys.dinewell.hotel.common.filters;

import com.blackcrowsys.dinewell.hotel.common.exception.TokenNotFoundException;
import com.blackcrowsys.dinewell.hotel.common.service.JwtSecurityCachingService;
import com.blackcrowsys.dinewell.hotel.common.service.JwtSecurityTokeniser;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenSecurityFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER = "Bearer";
    private static final int TOKEN_START_INDEX = 7;

    @Autowired
    private JwtSecurityTokeniser jwtSecurityTokeniser;

    @Autowired
    private JwtSecurityCachingService cachingService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Either<TokenNotFoundException, String> token = getJwtTokenFromRequest(request);
        if (token.isRight() && jwtSecurityTokeniser.validate(token.get())) {
            Option<UserDetails> userDetails = cachingService.get(token.get());
            if (userDetails.isDefined()) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails.get(), null, userDetails.get().getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private Either<TokenNotFoundException, String> getJwtTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTH_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)) {
            return Either.right(bearerToken.substring(TOKEN_START_INDEX, bearerToken.length()));
        }
        return Either.left(new TokenNotFoundException());
    }
}
