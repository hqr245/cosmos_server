package es.acarpoz.cosmos_server.services;

import org.springframework.stereotype.Service;
import es.acarpoz.cosmos_server.dao.CosmosFileDAO;
import java.util.List;
import es.acarpoz.cosmos_server.entities.CosmosFile;

@Service
public class CosmosFileServiceImplement implements CosmosFileService {
    
    private final CosmosFileDAO cosmosFileDAO;

    public CosmosFileServiceImplement(CosmosFileDAO cosmosFileDAO) {
        this.cosmosFileDAO = cosmosFileDAO;
    }

    @Override
    public List<CosmosFile> findAll() {
        return cosmosFileDAO.findAll();
    }

    @Override
    public CosmosFile save(CosmosFile cosmosFile) {
        return cosmosFileDAO.save(cosmosFile);
    }


    @Override
    public CosmosFile findById(Long id) {
        return cosmosFileDAO.findById(id).orElse(null);
    }

    @Override
    public void delete(CosmosFile cosmosFile) {
        cosmosFileDAO.delete(cosmosFile);
    }
}
