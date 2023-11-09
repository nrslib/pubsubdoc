package com.pubsubdoc.service.back.app.infrastructure.jpa.doc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "docs")
public class DocDataModel{
        @Id
        String docId;

        @Column
        String body;

        @Column
        int effectiveCount;
}
