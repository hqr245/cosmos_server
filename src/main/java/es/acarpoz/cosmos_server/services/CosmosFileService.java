package es.acarpoz.cosmos_server.services;
import es.acarpoz.cosmos_server.entities.CosmosFile;


import java.util.List;

public interface CosmosFileService {

    List<CosmosFile> findAll();
    CosmosFile save(CosmosFile cosmosFile);
    CosmosFile findById(Long id);
    void delete(CosmosFile cosmosFile);
}
