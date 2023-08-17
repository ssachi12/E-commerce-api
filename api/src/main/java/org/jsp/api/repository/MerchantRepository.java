package org.jsp.api.repository;

import java.util.Optional;

import org.jsp.api.dto.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
@Query("select m from Merchant m where m.phone=?1 and m.password=?2")
public Optional<Merchant> verifyByPhone(long phone,String password);
@Query("select m from Merchant m where m.email=?1 and m.password=?2")
public Optional<Merchant> verifyByEmail(String email,String password);
@Query("select m from Merchant m where m.token=?1")
public Merchant findmerchantByToken(String token);
@Query("select m from Merchant m where m.email=?1")
public Merchant findmerchantByEmail(String email);
}
