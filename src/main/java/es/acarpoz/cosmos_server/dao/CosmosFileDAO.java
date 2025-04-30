package es.acarpoz.cosmos_server.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import es.acarpoz.cosmos_server.entities.CosmosFile;

@Repository
public interface CosmosFileDAO extends JpaRepository<CosmosFile, Long> {
}