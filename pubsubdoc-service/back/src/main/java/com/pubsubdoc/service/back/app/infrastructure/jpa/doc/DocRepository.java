package com.pubsubdoc.service.back.app.infrastructure.jpa.doc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DocRepository extends JpaRepository<DocDataModel, String> {
}
