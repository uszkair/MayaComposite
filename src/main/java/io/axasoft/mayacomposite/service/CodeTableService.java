package io.axasoft.mayacomposite.service;

import io.axasoft.mayacomposite.constants.ApplicationConstants;
import io.axasoft.mayacomposite.exception.ServiceException;
import io.axasoft.mayacomposite.mapper.CodeTableMapper;
import io.axasoft.mayacomposite.model.CodeTable;
import io.axasoft.mayacomposite.model.CodeTableTranslation;
import io.axasoft.mayacomposite.model.CodeTableType;
import io.axasoft.mayacomposite.model.CodeTableTypeTranslation;
import io.axasoft.mayacomposite.repository.CodeTableRepository;
import io.axasoft.mayacomposite.repository.CodeTableTypeRepository;
import io.axasoft.mayacomposite.request.CodeTableTypeRequest;
import io.axasoft.mayacomposite.request.CodeTableValueRequest;
import io.axasoft.mayacomposite.response.CodeTableResponse;
import io.axasoft.mayacomposite.response.CodeTableTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CodeTableService {

    private final CodeTableTypeRepository codeTableTypeRepository;
    private final CodeTableRepository codeTableRepository;
    private final CodeTableMapper codeTableMapper;

    @Transactional
    public CodeTableTypeResponse createCodeTableType(CodeTableTypeRequest request) {
        // Check if type already exists
        if (codeTableTypeRepository.existsByCode(request.getCode())) {
            throw new ServiceException(ApplicationConstants.CODE_TABLE_TYPE_EXISTS, request.getCode());
        }

        // Create and save the code table type
        CodeTableType codeTableType = codeTableMapper.toTypeEntity(request);

        // Add translations to code table type
        request.getTranslations().forEach(translationRequest -> {
            CodeTableTypeTranslation translation = codeTableMapper.toTypeTranslationEntity(translationRequest);
            translation.setCodeTableType(codeTableType);
            codeTableType.getTranslations().add(translation);
        });

        CodeTableType savedType = codeTableTypeRepository.save(codeTableType);

        // Create code table values
        request.getValues().forEach(valueRequest -> {
            // Check if code already exists for this type
            if (codeTableRepository.existsByTypeCodeAndCode(request.getCode(), valueRequest.getCode())) {
                throw new ServiceException(ApplicationConstants.CODE_TABLE_CODE_EXISTS,
                        request.getCode(), valueRequest.getCode());
            }

            createCodeTableValue(savedType, valueRequest);
        });

        // Refresh and return the complete structure
        CodeTableType refreshedType = codeTableTypeRepository.findById(savedType.getId())
                .orElseThrow(() -> new ServiceException(ApplicationConstants.RESOURCE_NOT_FOUND, savedType.getId()));

        return codeTableMapper.toTypeResponse(refreshedType);
    }

    @Transactional
    public CodeTableResponse createCodeTableValue(CodeTableType type, CodeTableValueRequest request) {
        CodeTable codeTable = codeTableMapper.toEntity(request);
        codeTable.setType(type);

        // Add translations to code table
        request.getTranslations().forEach(translationRequest -> {
            CodeTableTranslation translation = codeTableMapper.toTranslationEntity(translationRequest);
            translation.setCodeTable(codeTable);
            codeTable.getTranslations().add(translation);
        });

        CodeTable savedCodeTable = codeTableRepository.save(codeTable);
        return codeTableMapper.toResponse(savedCodeTable);
    }

    @Transactional
    public CodeTableResponse addCodeTableToType(String typeCode, CodeTableValueRequest request) {
        CodeTableType type = codeTableTypeRepository.findByCode(typeCode)
                .orElseThrow(() -> new ServiceException(ApplicationConstants.RESOURCE_NOT_FOUND,
                        "CodeTableType: " + typeCode));

        if (codeTableRepository.existsByTypeCodeAndCode(typeCode, request.getCode())) {
            throw new ServiceException(ApplicationConstants.CODE_TABLE_CODE_EXISTS, typeCode, request.getCode());
        }

        return createCodeTableValue(type, request);
    }

    public CodeTableTypeResponse getCodeTableTypeById(String id) {
        CodeTableType codeTableType = codeTableTypeRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ApplicationConstants.RESOURCE_NOT_FOUND, id));
        return codeTableMapper.toTypeResponse(codeTableType);
    }

    public CodeTableResponse getCodeTableById(String id) {
        CodeTable codeTable = codeTableRepository.findById(id)
                .orElseThrow(() -> new ServiceException(ApplicationConstants.RESOURCE_NOT_FOUND, id));
        return codeTableMapper.toResponse(codeTable);
    }

    public Page<CodeTableTypeResponse> getAllCodeTableTypes(Pageable pageable) {
        return codeTableTypeRepository.findAll(pageable)
                .map(codeTableMapper::toTypeResponse);
    }

    public List<CodeTableResponse> getCodeTablesByType(String typeCode) {
        return codeTableRepository.findByTypeCodeAndIsActiveTrue(typeCode)
                .stream()
                .map(codeTableMapper::toResponse)
                .toList();
    }

    @Transactional
    public void deleteCodeTableType(String id) {
        if (!codeTableTypeRepository.existsById(id)) {
            throw new ServiceException(ApplicationConstants.RESOURCE_NOT_FOUND, id);
        }
        codeTableTypeRepository.deleteById(id);
    }

    @Transactional
    public void deleteCodeTable(String id) {
        if (!codeTableRepository.existsById(id)) {
            throw new ServiceException(ApplicationConstants.RESOURCE_NOT_FOUND, id);
        }
        codeTableRepository.deleteById(id);
    }

    public CodeTableTypeResponse findTypeByCode(String code) {
        return codeTableTypeRepository.findByCode(code)
                .map(codeTableMapper::toTypeResponse)
                .orElseThrow(() -> new ServiceException(ApplicationConstants.RESOURCE_NOT_FOUND, code));
    }
}