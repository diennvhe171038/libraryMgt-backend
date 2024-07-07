package swp391.learning.application.service.Implements;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@Slf4j
public class ZipService {
    public byte[] createZip(List<Resource> resources) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ZipOutputStream zos = new ZipOutputStream(baos)) {
            for (Resource resource : resources) {
                ZipEntry entry = new ZipEntry(resource.getFilename());
                entry.setSize(resource.contentLength());
                zos.putNextEntry(entry);
                StreamUtils.copy(resource.getInputStream(), zos);
                zos.closeEntry();
            }
        }
        return baos.toByteArray();
    }
}
