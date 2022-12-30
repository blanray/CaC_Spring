package com.example.CrudPB.service;

import com.example.CrudPB.dto.request.UserRequestDTO;
import com.example.CrudPB.dto.response.UserResponseDTO;
import com.example.CrudPB.entities.User;
import com.example.CrudPB.repository.IUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import com.example.CrudPB.exceptions.UserNotFoundException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.CrudPB.utils.CONSTANTS.SECRET_KEY_TOKEN;

@Service
public class SessionServiceImpl implements ISessionService {

    private IUserRepository userRepository;

    public SessionServiceImpl ( IUserRepository userRepository) {
        this.userRepository= userRepository;
    }

    @Override
    public UserResponseDTO login (UserRequestDTO user ) {

        String username = user.getUserName();
        User usuario = userRepository.findByUsernameAndPassword(username, user.getPassword())
                .orElseThrow(UserNotFoundException::new);

        List<String> roles = usuario.getRoles()
                .stream()
                .map(e -> e.getRol().getText())
                .collect(Collectors.toList());


        String token = getJWTToken(username, roles);

        return new UserResponseDTO(username, token);
    }

    private String getJWTToken ( String username, List<String> roles ) {

        List<GrantedAuthority> grantedAuthorities = roles
                .stream()
                .map(AuthorityUtils::commaSeparatedStringToAuthorityList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        LocalDateTime expired = LocalDateTime.now()
                .plusMinutes(15);
        Date expiredTime = Date.from(expired.atZone(ZoneId.systemDefault())
                .toInstant());

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList())
                )
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expiredTime)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY_TOKEN.getBytes())
                .compact();

        return "Bearer " + token;
    }

    private static Claims decodeJWT (String token ) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY_TOKEN.getBytes())
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public static String getUsername ( String token ) {
        Claims claims = decodeJWT(token);
        return claims.get("sub", String.class);
    }


}
