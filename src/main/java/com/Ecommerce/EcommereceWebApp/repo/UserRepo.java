package com.Ecommerce.EcommereceWebApp.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Ecommerce.EcommereceWebApp.entity.Users;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {

	Users findByEmailAndPassword(String email, String password);

	Optional<Users> findByEmail(String email);

	List<Users> findByRole(String role);

    boolean existsByEmail(String email);
}
