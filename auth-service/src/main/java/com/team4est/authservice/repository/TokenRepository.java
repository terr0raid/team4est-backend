package com.team4est.authservice.repository;

import com.team4est.authservice.entity.Token;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Long> {
  @Query(
    value = """
    select t from Token t inner join Account a\s
    on t.account.id = a.id\s
    where a.id = :id and (t.expired = false or t.revoked = false)\s
    """
  )
  List<Token> findAllValidTokenByAccount(Long id);

  Optional<Token> findByAccessToken(String accessToken);
  Optional<Token> findByRefreshToken(String refreshToken);
}
