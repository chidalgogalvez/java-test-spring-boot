package cl.demotest.client.util;


import cl.demotest.client.dto.ResponseWrapper;
import cl.demotest.client.exception.ConflictException;
import cl.demotest.client.exception.NotFoundException;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class Utils {

  public static void verifyForConflict(boolean condition, String message)
      throws ConflictException {
    if (condition) {
      throw new ConflictException(message);
    }
  }

  public static void verifyForNotFound(boolean condition, String message)
      throws NotFoundException {
    if (condition) {
      throw new NotFoundException(message);
    }
  }

  public static ResponseWrapper formatResponse(int statusCode, String message, Object payload) {

    return ResponseWrapper.builder().statusCode(statusCode).message(message).payload(payload).build();
  }

  public static String getJWTToken(String username) {
    String secretKey = "mySecretKey";
    List<GrantedAuthority> grantedAuthorities = AuthorityUtils
            .commaSeparatedStringToAuthorityList("ROLE_USER");

    String token = Jwts
            .builder()
            .setId("softtekJWT")
            .setSubject(username)
            .claim("authorities",
                    grantedAuthorities.stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList()))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 600000))
            .signWith(SignatureAlgorithm.HS512,
                    secretKey.getBytes()).compact();

    return "Bearer " + token;
  }

  public static String convertToJson(Object object) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.setSerializationInclusion(Include.NON_NULL);
      ObjectWriter objectWriter = mapper.writer();
      return objectWriter.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      log.error(e.getMessage());
      return e.getMessage();
    }
  }
}
