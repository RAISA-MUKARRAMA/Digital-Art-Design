package com.example.Digital.Art.Design.Arupa.Repository;

import com.example.Digital.Art.Design.Arupa.EntityClasses.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRequestRepository extends JpaRepository<UserRequest, LocalDateTime> {
    public List<UserRequest> findByuserId(Long uId);
}
