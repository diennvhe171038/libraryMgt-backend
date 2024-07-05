package swp391.learning.application.service.Implements;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import swp391.learning.exception.InvalidRequestException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Service
public class FileUploadService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String saveImage(MultipartFile file, String folderName) {
        validateFile(file);

        try {
            // Tạo thư mục lưu trữ file nếu chưa tồn tại
            Path uploadDirectory = Paths.get(uploadDir, folderName);
            if (!Files.exists(uploadDirectory)) {
                Files.createDirectories(uploadDirectory);
            }

            // Tạo tên file mới
            String fileName = file.getOriginalFilename();
            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = System.currentTimeMillis() + fileExtension;
            Path filePath = uploadDirectory.resolve(newFileName);

            // Lưu file vào thư mục
            Files.copy(file.getInputStream(), filePath);

            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + file.getOriginalFilename(), e);
        }
    }

    private void validateFile(MultipartFile file) {
        // Xác thực kích thước file
        if (file.getSize() > 2 * 1024 * 1024) { // 2MB
            throw new InvalidRequestException("Kích thước ảnh không được vượt quá 2MB");
        }

        // Xác thực định dạng file
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || !isAllowedFileExtension(originalFileName)) {
            throw new InvalidRequestException("Chỉ chấp nhận ảnh định dạng JPG, JPEG, PNG");
        }

        // Xác thực tỷ lệ ảnh
        try {
            double ratio = (double) getImageWidth(file) / (double) getImageHeight(file);
            if (Math.abs(ratio - 1.0) > 0.01 && Math.abs(ratio - (16.0 / 9.0)) > 0.01) {
                throw new InvalidRequestException("Ảnh phải có tỷ lệ 1:1 hoặc 16:9");
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read image dimensions", e);
        }
    }

    private boolean isAllowedFileExtension(String fileName) {
        String allowedFileExtensions = "jpg,jpeg,png";
        List<String> allowedExtensions = Arrays.asList(allowedFileExtensions.split(","));
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return allowedExtensions.contains(fileExtension);
    }

    private int getImageWidth(MultipartFile file) throws IOException {
        BufferedImage image = ImageIO.read(file.getInputStream());
        return image.getWidth();
    }

    private int getImageHeight(MultipartFile file) throws IOException {
        BufferedImage image = ImageIO.read(file.getInputStream());
        return image.getHeight();
    }
}
