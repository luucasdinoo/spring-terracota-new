package com.terracota.infrastructure.api.controller;

import com.terracota.application.files.UploadImageCommand;
import com.terracota.application.files.UploadImageUseCase;
import com.terracota.domain.resource.Resource;
import com.terracota.infrastructure.utils.HashingUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping("images")
public class ImagesController{

    private final UploadImageUseCase uploadImageUseCase;

    public ImagesController(final UploadImageUseCase uploadImageUseCase) {
        this.uploadImageUseCase = Objects.requireNonNull(uploadImageUseCase);
    }

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Upload file")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "File uploaded successfully")
    })
    public ResponseEntity<?> upload(
            @RequestPart("file") MultipartFile file,
            @RequestPart("id") String id
    ) {
        UploadImageCommand aCmd = UploadImageCommand.with(resourceOf(file), id);
        this.uploadImageUseCase.execute(aCmd);
        return ResponseEntity.noContent().build();
    }

    private Resource resourceOf(final MultipartFile photo) {
        if (photo == null) return null;

        try {
            return Resource.with(
                    photo.getBytes(),
                    HashingUtils.checksum(photo.getBytes()),
                    photo.getContentType(),
                    photo.getOriginalFilename()
            );
        }catch (Throwable t){
            throw new RuntimeException(t);
        }
    }
}
