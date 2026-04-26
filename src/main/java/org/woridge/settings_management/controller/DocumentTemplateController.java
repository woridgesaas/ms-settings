package org.woridge.settings_management.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.woridge.settings_management.dto.SuccessResponse;
import org.woridge.settings_management.dto.document_template.PreviewDocumentTemplateResponse;
import org.woridge.settings_management.dto.document_template.UploadDocumentTemplatesRequest;
import org.woridge.settings_management.dto.document_template.UploadDocumentTemplatesResponse;
import org.woridge.settings_management.service.DocumentTemplateService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/document-templates")
@AllArgsConstructor
public class DocumentTemplateController {

    private final DocumentTemplateService documentTemplateService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SuccessResponse<List<UploadDocumentTemplatesResponse>>> uploadDocumentTemplates(@Valid @ModelAttribute UploadDocumentTemplatesRequest uploadDocumentTemplatesRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        SuccessResponse.createSuccessResponse(
                                "Document templates uploaded successfully",
                                documentTemplateService.uploadDocumentTemplates(uploadDocumentTemplatesRequest)
                        )
                );
    }

    @GetMapping("/{documentTemplateId}")
    public ResponseEntity<SuccessResponse<PreviewDocumentTemplateResponse>> previewDocumentTemplate(@PathVariable UUID documentTemplateId) {

        return ResponseEntity.ok(
                SuccessResponse.createSuccessResponse(
                        "Preview URL generated successfully",
                        documentTemplateService.previewDocumentTemplate(documentTemplateId)
                )
        );
    }

    @DeleteMapping("/{documentTemplateId}")
    public ResponseEntity<Void> deleteDocumentTemplate(@PathVariable UUID documentTemplateId) {

        documentTemplateService.deleteDocumentTemplate(documentTemplateId);

        return ResponseEntity
                .noContent()
                .build();
    }
}
