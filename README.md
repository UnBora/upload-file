# Spring Boot File Upload/Download REST API

A complete Spring Boot application that provides file upload and download functionality with database storage using MySQL and Thymeleaf for the web interface.

## Features

- **Single File Upload**: Upload individual files via REST API
- **Multiple File Upload**: Upload multiple files simultaneously
- **File Download**: Download files using unique file IDs
- **File Viewing**: View files directly in the browser
- **Database Storage**: Files are stored as BLOB data in MySQL database
- **Web Interface**: User-friendly HTML interface for file operations
- **REST API**: Complete RESTful endpoints for programmatic access

## Technology Stack

- **Backend**: Spring Boot 2.x
- **Database**: MySQL
- **ORM**: Hibernate/JPA
- **Template Engine**: Thymeleaf
- **Frontend**: HTML, CSS, JavaScript
- **Build Tool**: Maven

## Project Structure

```
src/main/java/com/example/uploadfile002/
├── UploadFile002Application.java          # Main Spring Boot application
├── controller/
│   ├── FileController.java                # REST API endpoints
│   └── WebFileController.java             # Web interface controller
├── exception/
│   ├── FileStorageException.java          # Custom file storage exception
│   └── MyFileNotFoundException.java       # Custom file not found exception
├── model/
│   └── DBFile.java                        # File entity model
├── payload/
│   └── UploadFileResponse.java            # Response DTO for file uploads
├── repository/
│   └── DBFileRepository.java              # JPA repository interface
└── service/
    └── DBFileStorageService.java          # File storage service layer

src/main/resources/
├── application.properties                 # Application configuration
└── templates/
    └── css/
        ├── index.html                     # Web interface template
        ├── css/main.css                   # Stylesheet
        └── js/main.js                     # JavaScript functionality
```

## Database Configuration

The application uses MySQL database with the following configuration:

- **Database**: `article`
- **Username**: `root`
- **Password**: `boraun`
- **Port**: `3306`

### Database Schema

The application automatically creates a `files` table with the following structure:

```sql
CREATE TABLE files (
    id VARCHAR(255) PRIMARY KEY,
    file_name VARCHAR(255),
    file_type VARCHAR(255),
    data LONGBLOB
);
```

## API Endpoints

### Upload Endpoints

| Method | Endpoint | Description | Parameters |
|--------|----------|-------------|------------|
| POST | `/uploadFile` | Upload a single file | `file` (MultipartFile) |
| POST | `/uploadMultipleFiles` | Upload multiple files | `files` (MultipartFile[]) |

### Download Endpoints

| Method | Endpoint | Description | Parameters |
|--------|----------|-------------|------------|
| GET | `/download/{fileId}` | Download file as attachment | `fileId` (String) |
| GET | `/view/{fileId}` | View file in browser | `fileId` (String) |

### Web Interface

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/bi` | Display file upload interface |

## Configuration Settings

### File Upload Limits

- **Maximum file size**: 200MB
- **Maximum request size**: 215MB
- **File size threshold**: 2KB (files larger than this are written to disk)

### Application Properties

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/article
spring.datasource.username=root
spring.datasource.password=***

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
logging.level.org.hibernate.SQL=debug

# Thymeleaf Configuration
spring.thymeleaf.cache=false

# File Upload Configuration
spring.servlet.multipart.enabled=true
spring.servlet.multipart.file-size-threshold=2KB
spring.servlet.multipart.max-file-size=200MB
spring.servlet.multipart.max-request-size=215MB
```

## Setup Instructions

### Prerequisites

- Java 8 or higher
- Maven 3.6+
- MySQL 5.7+ or MySQL 8.0+

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd uploadfile002
   ```

2. **Setup MySQL Database**
   ```sql
   CREATE DATABASE article;
   ```

3. **Configure Database Connection**
   - Update `src/main/resources/application.properties`
   - Modify database credentials if needed

4. **Build the Project**
   ```bash
   mvn clean install
   ```

5. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

6. **Access the Application**
   - Web Interface: `http://localhost:8080/bi`
   - API Base URL: `http://localhost:8080`

## Usage Examples

### Web Interface Usage

1. Navigate to `http://localhost:8080/bi`
2. Use the "Upload Single File" section to upload one file
3. Use the "Upload Multiple Files" section to upload multiple files
4. Download links will be provided after successful upload

### API Usage Examples

#### Upload Single File (cURL)

```bash
curl -X POST \
  http://localhost:8080/uploadFile \
  -H 'content-type: multipart/form-data' \
  -F file=@/path/to/your/file.pdf
```

#### Upload Multiple Files (cURL)

```bash
curl -X POST \
  http://localhost:8080/uploadMultipleFiles \
  -H 'content-type: multipart/form-data' \
  -F files=@/path/to/file1.pdf \
  -F files=@/path/to/file2.jpg
```

#### Download File

```bash
curl -X GET http://localhost:8080/download/{fileId} --output downloaded_file
```

### Response Format

#### Upload Response

```json
{
  "fileName": "example.pdf",
  "fileDownloadUri": "http://localhost:8080/download/abc123-def456-ghi789",
  "fileType": "application/pdf",
  "size": 1024000
}
```

## Error Handling

The application includes custom exception handling for:

- **FileStorageException**: Issues during file storage operations
- **MyFileNotFoundException**: When requested files are not found (returns HTTP 404)

## Security Considerations

- File names are sanitized to prevent path traversal attacks
- Invalid path sequences (containing "..") are rejected
- File size limits are enforced to prevent resource exhaustion

## Development Notes

- The application uses UUID generation for file IDs
- Files are stored as BLOB data in the database
- Hibernate SQL logging is enabled for debugging
- Thymeleaf caching is disabled for development

## Testing

Run the test suite with:

```bash
mvn test
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## License

This project is open source and available under the [MIT License](LICENSE).

## Support

For issues and questions:
1. Check the application logs for detailed error messages
2. Ensure MySQL database is running and accessible
3. Verify file size limits are not exceeded
4. Check database connection configuration
