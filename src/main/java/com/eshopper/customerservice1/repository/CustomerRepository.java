package com.eshopper.customerservice1.repository;

import com.eshopper.customerservice1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<User, Integer> {
}
