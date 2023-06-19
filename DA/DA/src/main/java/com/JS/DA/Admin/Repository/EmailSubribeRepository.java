package com.JS.DA.Admin.Repository;

import com.JS.DA.Admin.Model.EmailSubribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailSubribeRepository extends JpaRepository<EmailSubribe, Long> {
}
