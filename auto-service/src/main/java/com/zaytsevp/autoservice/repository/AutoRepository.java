package com.zaytsevp.autoservice.repository;

import com.zaytsevp.autoservice.model.Auto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Pavel Zaytsev
 */
public interface AutoRepository extends JpaRepository<Auto, UUID> {
}
