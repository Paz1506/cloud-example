package com.zaytsevp.modelservice.repository;

import com.zaytsevp.modelservice.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Pavel Zaytsev
 */
public interface ModelRepository extends JpaRepository<Model, UUID> {
}
