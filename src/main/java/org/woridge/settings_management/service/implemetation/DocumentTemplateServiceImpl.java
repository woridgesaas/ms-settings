package org.woridge.settings_management.service.implemetation;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.woridge.settings_management.dto.document_template.PreviewDocumentTemplateResponse;
import org.woridge.settings_management.dto.document_template.UploadDocumentTemplateItem;
import org.woridge.settings_management.dto.document_template.UploadDocumentTemplatesRequest;
import org.woridge.settings_management.dto.document_template.UploadDocumentTemplatesResponse;
import org.woridge.settings_management.exception.document_template.DocumentTemplateNotConfiguredException;
import org.woridge.settings_management.mapper.DocumentTemplateMapper;
import org.woridge.settings_management.model.DocumentTemplate;
import org.woridge.settings_management.repository.DocumentTemplateRepository;
import org.woridge.settings_management.service.FileConversion;
import org.woridge.settings_management.service.DocumentTemplateService;
import org.woridge.settings_management.service.DocumentTemplateValidator;
import org.woridge.settings_management.service.MinioService;
import org.woridge.settings_management.utils.FileUtils;
import org.woridge.settings_management.utils.RequestUtils;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class DocumentTemplateServiceImpl implements DocumentTemplateService {

    private final DocumentTemplateRepository documentTemplateRepository;
    private final DocumentTemplateValidator documentTemplateValidator;
    private final MinioService minioService;
    private final FileConversion fileConversion;
    private final HttpServletRequest httpServletRequest;

    @Override
    public List<UploadDocumentTemplatesResponse> uploadDocumentTemplates(UploadDocumentTemplatesRequest uploadDocumentTemplatesRequest) {

        String tenantName = RequestUtils.extractSubdomainFromHeaderRequest(httpServletRequest);

        return uploadDocumentTemplatesRequest
                .documentTemplateItems()
                .stream()
                .map(uploadDocumentTemplateItem -> processItem(tenantName, uploadDocumentTemplateItem))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PreviewDocumentTemplateResponse previewDocumentTemplate(UUID documentTemplateId) {

        DocumentTemplate currentDocumentTemplate = documentTemplateRepository.findById(documentTemplateId).orElseThrow(() ->
                new DocumentTemplateNotConfiguredException("Document template with id : " + documentTemplateId + " not configured"));

        String previewUrl = minioService.generateFilePreviewUrl(currentDocumentTemplate.getPath());

        return DocumentTemplateMapper.toPreviewDocumentTemplateResponse(previewUrl);
    }

    @Override
    public void deleteDocumentTemplate(UUID documentTemplateId) {

        DocumentTemplate currentDocumentTemplate = documentTemplateRepository.findById(documentTemplateId).orElseThrow(() ->
                new DocumentTemplateNotConfiguredException("Document template with id : " + documentTemplateId + " not configured"));

        minioService.deleteFile(currentDocumentTemplate.getPath());

        documentTemplateRepository.delete(currentDocumentTemplate);
    }

    // Helper method
    private UploadDocumentTemplatesResponse processItem(String tenantName, UploadDocumentTemplateItem uploadDocumentTemplateItem) {

        documentTemplateValidator.validateDocumentTemplate(uploadDocumentTemplateItem.documentTemplate());

        String fileName = FileUtils.buildFileName(
                uploadDocumentTemplateItem.category().name(),
                ".pdf"
        );

        String documentTemplatePath = minioService.uploadFile(
                tenantName,
                fileName,
                fileConversion.convertMultipartFileToBytes(uploadDocumentTemplateItem.documentTemplate()),
                "application/pdf"
        );

        DocumentTemplate documentTemplate = DocumentTemplateMapper.toDocumentTemplate(uploadDocumentTemplateItem, documentTemplatePath);

        return DocumentTemplateMapper.toUploadDocumentTemplatesResponse(documentTemplateRepository.save(documentTemplate));
    }
}
