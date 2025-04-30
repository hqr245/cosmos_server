package es.acarpoz.cosmos_server.entities;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Data;
/*This class represents a file in CosmosDB, it is used to store the information of the files that are 
uploaded to the server.*/


@Entity
@Table(name = "cosmos_file")
@Data
public class CosmosFile {

//This class' fields are id, fileName, file path, fileSize, encryptingType, creationDate and lastModificationDate

    //this is the primary key of the table, it is value is determined automatically by the database.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "File_id")
    private Long id;

    //This field stores the name of the file, it is a not nullable field and its length can be up to 260 characters.
    @Basic(optional = false)
    @Column(name = "file_name", nullable = false, length = 255)
    private String fileName;

    //This field stores the path of the file, it is a not nullable field and i's length can be up to 260 characters.
    @Basic(optional = false)
    @Column(name = "file_path", nullable = false, length = 255)
    private String filePath;

    //This field stores the type of the file, it is a not nullable field and its behaviour is the same as in a Long.
    @Basic(optional = false)
    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    //This field stores the type of the file, it is a not nullable field and its length can be up to 50 characters.
    @Basic(optional = false)
    @Column(name = "encrypting_type", nullable = false, length = 50)
    private String encryptingType;

    //This field stores the type of the file, it is a not nullable field and its behaviour is the same as in a Date.
    @Basic(optional = false)
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    //This field stores the type of the file, it is a not nullable field and its behaviour is the same as in a Date.
    @Basic(optional = false)
    @Column(name = "last_modification_date", nullable = false)
    private LocalDate lastModificationDate;

    @Lob
    @Column(name = "file_content", columnDefinition = "LONGBLOB", nullable = false)
    private byte[] fileContent;

    /*End of the class, there is no need to have a constructor, getters and setters, equals and hashcode methods,
     toString method or any other default method, because the Lombok library will generate them automatically.*/
}