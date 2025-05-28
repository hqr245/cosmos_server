package es.acarpoz.cosmos_server.controllers;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;  
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.acarpoz.cosmos_server.entities.CosmosFile;
import es.acarpoz.cosmos_server.services.CosmosFileService;

@RestController
@RequestMapping("/api/v1")
public class CosmosFileController {
    // Constants for response messages
    private static final String M_STRING = "message";
    private static final String FNF_STRING = "File not found";

    // Service for handling file operations
    private final CosmosFileService cosmosFileService;

    // Constructor for dependency injection of the service
    public CosmosFileController(CosmosFileService cosmosFileService) {
        this.cosmosFileService = cosmosFileService;
    }


    // Endpoint used in order to get all the files in the database, it returns a list of CosmosFile objects.
    @GetMapping("/files")
    public ResponseEntity<Object> getAllFiles() {

        Map<String, Object> response = new HashMap<>();

        try {
            List<CosmosFile> files = cosmosFileService.findAll();

        return new ResponseEntity<>(files, HttpStatus.OK);
        } catch (Exception e) {
            response.put(M_STRING, "Error retrieving files: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint used in order to upload a file to the database.
    @PostMapping(value = "/files", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestBody CosmosFile cosmosFile) {

        Map<String, Object> response = new HashMap<>();

        try {
            CosmosFile savedFile = cosmosFileService.save(cosmosFile);

            response.put(M_STRING, "File uploaded successfully");
            response.put("file", savedFile);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.put(M_STRING, "Error uploading file: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    // Endpoint used in order to update a file based in their ID in the database.
    // It returns a ResponseEntity with the updated file or an error message.
    @PostMapping(value = "/files/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateFile(@PathVariable Long id,
                                             @RequestParam("fileName") String fileName,  
                                             @RequestParam("fileContent") byte[] fileContent,
                                             @RequestParam("lastModificationDate") LocalDate encDate,
                                             @RequestParam("encryptingType") String keyName,
                                             @RequestParam("filePath") String filePath) {

        Map<String, Object> response = new HashMap<>();

        try {
            CosmosFile existingFile = cosmosFileService.findById(id);
            if (existingFile == null) {
                response.put(M_STRING, FNF_STRING);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            existingFile.setFileName(existingFile.getFileName());
            existingFile.setFileSize(existingFile.getFileSize());
            existingFile.setFileContent(existingFile.getFileContent());
            existingFile.setEncDate(existingFile.getEncDate());
            existingFile.setKeyName(existingFile.getKeyName());
            existingFile.setFilePath(existingFile.getFilePath());

            CosmosFile updatedFile = cosmosFileService.save(existingFile);

            response.put(M_STRING, "File updated successfully");
            response.put("file", updatedFile);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put(M_STRING, "Error updating file: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Endpoint used in order to delete a file by its id
    // It returns a ResponseEntity with a success message or an error message.
    @DeleteMapping("/files/{id}")
    public ResponseEntity<Object> deleteFile(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();

        try {
            CosmosFile file = cosmosFileService.findById(id);
            if (file != null) {
                cosmosFileService.delete(file);
                response.put(M_STRING, "File deleted successfully");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put(M_STRING, FNF_STRING);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.put(M_STRING, "Error deleting file: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
