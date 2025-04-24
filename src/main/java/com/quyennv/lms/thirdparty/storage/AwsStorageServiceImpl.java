package com.quyennv.lms.thirdparty.storage;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.quyennv.lms.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.cloudfront.CloudFrontUtilities;
import software.amazon.awssdk.services.cloudfront.model.CannedSignerRequest;
import software.amazon.awssdk.services.cloudfront.url.SignedUrl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Objects;

@Component
@Slf4j
public class AwsStorageServiceImpl implements StorageService {
    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @Value("${aws.cloudfront.domain}")
    private String domain;

    @Value("${aws.cloudfront.key-pair-id}")
    private String keyPairId;

    private static final String cloudflarePrivateKeyPath = "cloudfront/private-key.pem";

    public AwsStorageServiceImpl(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public String generateUrl(String fileName) throws Exception {
        CloudFrontUtilities cloudFrontUtilities = CloudFrontUtilities.create();
        Instant expiration = Instant.now().plusSeconds(Constant.SIGNED_URL_EXPIRATION);
        String resourcePath = String.format("%s/%s", domain, fileName);

        File privateKeyFile = new ClassPathResource(cloudflarePrivateKeyPath).getFile();
        CannedSignerRequest cannedRequest = CannedSignerRequest.builder()
                .resourceUrl(resourcePath)
                .privateKey(privateKeyFile.getAbsoluteFile().toPath())
                .expirationDate(expiration)
                .keyPairId(keyPairId)
                .build();
        SignedUrl signedUrl = cloudFrontUtilities.getSignedUrlWithCannedPolicy(cannedRequest);
        return signedUrl.url();
    }

    public String uploadFile(MultipartFile file) {
        try {
            File fileObj = convertMultiPartFileToFile(file);
            String fileName = file.getOriginalFilename().replace(" ", "") + "_" + System.currentTimeMillis() + "."
                    + Objects.requireNonNull(file.getContentType()).split("/")[1];
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
            if (fileObj.delete()) {
                return fileName;
            } else {
                throw new RuntimeException("Error while deleting file");
            }
        } catch (Exception e) {
            log.error("Error while uploading file to S3: {}", e.getMessage());
            throw new RuntimeException("Error while uploading file to S3");
        }
    }

    @Override
    public String getProviderName() {
        return "AWS_S3";
    }


    public byte[] downloadFile(String fileName) {
        S3Object s3Object = amazonS3.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            return IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            log.info("Error while downloading file:: {}", e.getMessage());
        }
        return new byte[0];
    }


    public String deleteFile(String fileName) {
        amazonS3.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}
