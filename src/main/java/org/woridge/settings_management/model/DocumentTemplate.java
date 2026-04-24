package org.woridge.settings_management.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.woridge.settings_management.enumeration.DocumentCategory;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "document_templates")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DocumentTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DocumentCategory category;

    @Column(nullable = false)
    private String path;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private Instant updatedAt;
}
