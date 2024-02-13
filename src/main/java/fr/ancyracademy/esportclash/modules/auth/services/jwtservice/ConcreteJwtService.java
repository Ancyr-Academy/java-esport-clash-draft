package fr.ancyracademy.esportclash.modules.auth.services.jwtservice;

import fr.ancyracademy.esportclash.modules.auth.model.JwtUser;
import fr.ancyracademy.esportclash.modules.auth.model.User;
import fr.ancyracademy.esportclash.shared.date.DateProvider;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.time.ZoneId;
import java.util.Date;

public class ConcreteJwtService implements JwtService {
  private final SecretKey secretKey;
  private final JwtParser jwtParser;

  private final long expiration;
  private final DateProvider dateProvider;

  public ConcreteJwtService(String secret, long expiration, DateProvider dateProvider) {
    this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    this.jwtParser = Jwts.parser().verifyWith(secretKey).build();
    this.expiration = expiration;
    this.dateProvider = dateProvider;
  }

  @Override
  public String tokenize(User user) {
    var claims = Jwts.claims()
        .subject(user.getId())
        .add("emailAddress", user.getEmailAddress())
        .build();

    var createdAt = dateProvider.now();
    var expiresAt = createdAt.plusSeconds(this.expiration);

    return Jwts.builder()
        .claims(claims)
        .issuedAt(Date.from(createdAt.atZone(ZoneId.systemDefault()).toInstant()))
        .expiration(Date.from(expiresAt.atZone(ZoneId.systemDefault()).toInstant()))
        .signWith(secretKey)
        .compact();
  }

  @Override
  public JwtUser parse(String token) {
    var claims = jwtParser.parseSignedClaims(token).getPayload();

    var id = claims.getSubject();
    var emailAddress = claims.get("emailAddress", String.class);

    return new JwtUser(id, emailAddress);
  }
}
