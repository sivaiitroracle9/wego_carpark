package com.wego.dao;

import com.wego.db.model.CarparkModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarparkRepository extends JpaRepository<CarparkModel, Long> {
}
