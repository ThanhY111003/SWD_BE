package com.example.Back_end.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class FirebaseStorageService {

    private final Storage storage;
    private final String BUCKET_NAME = "swdbe-ba5c3.appspot.com"; // Thay bằng tên bucket của bạn

    public FirebaseStorageService() throws IOException {
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("serviceAccountKey.json");
        if (serviceAccount == null) {
            throw new IOException("serviceAccountKey.json not found in resources");
        }

        this.storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()
                .getService();
    }

    public String uploadFile(MultipartFile file) throws IOException {
        return upload(file).url;
    }

    public Uploaded upload(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        Bucket bucket = storage.get(BUCKET_NAME);
        Blob blob = bucket.create(fileName, file.getInputStream(), file.getContentType());
        final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media";
        String url = String.format(DOWNLOAD_URL, BUCKET_NAME, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        return new Uploaded(fileName, url);
    }

    public void deleteByFileName(String fileName) {
        Bucket bucket = storage.get(BUCKET_NAME);
        Blob blob = bucket.get(fileName);
        if (blob != null) blob.delete();
    }

    public record Uploaded(String fileName, String url) {}
}
