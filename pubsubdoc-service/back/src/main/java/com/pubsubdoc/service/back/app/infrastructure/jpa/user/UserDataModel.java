package com.pubsubdoc.service.back.app.infrastructure.jpa.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserDataModel {
        @Id
        String userId;

        @Column
        String name;

        @Column
        String teamId;
}
