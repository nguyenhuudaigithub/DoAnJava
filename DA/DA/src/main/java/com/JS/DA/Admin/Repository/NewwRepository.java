package com.JS.DA.Admin.Repository;

import com.JS.DA.Admin.Model.Neww;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewwRepository extends JpaRepository<Neww, Long> {
}