package com.ecommerce.used_good.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CopyObjectResult;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.ecommerce.used_good.util.ConstantType;

@Service
@PropertySource("classpath:application.properties")
public class AWSService 
{
    @Value("${aws.service.access_key}")
    private String access_key;

    @Value("${aws.service.secret_key}")
    private String secret_key;

    private AWSCredentials credentials;

    private AmazonS3 s3Client;

    private Bucket defaultBucket;
    
    private AWSService()
    {

    }

    public void checkSetup()
    {
        if(this.s3Client == null)
            setUpClient();
    }

    public void setUpClient()
    {
        credentials = new BasicAWSCredentials(access_key, secret_key);
        s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1).build();
    
        List<Bucket> buckets = getAllBucket();
        for (Bucket bucket : buckets) 
        {
            if(bucket.getName().equals(ConstantType.BUCKET_NAME))
            {
                this.defaultBucket = bucket;
                break;
            }    
        }
    }

    public List<Bucket> getAllBucket()
    {
        return s3Client.listBuckets();
    }

    public Bucket getDefaultBucket() {
        return this.defaultBucket;
    }

    public void setDefaultBucket(Bucket defaultBucket) {
        this.defaultBucket = defaultBucket;
    }

    public ObjectListing getAllObject(String bucketName)
    {
        return s3Client.listObjects(bucketName);
    }
    
    public ObjectListing getAllObject()
    {
        return s3Client.listObjects(this.defaultBucket.getName());
    }

    public PutObjectResult uploadObject(String absoluteLocation, File file)
    {
        return s3Client.putObject(this.defaultBucket.getName(), absoluteLocation, file);
    }

    public PutObjectResult uploadObject(String bucketName, String absoluteLocation, File file)
    {
        return s3Client.putObject(bucketName, absoluteLocation, file);
    }

    public PutObjectResult uploadObject(String absoluteLocation, InputStream stream)
    {
        return s3Client.putObject(this.defaultBucket.getName(), absoluteLocation, stream, null);
    }

    public PutObjectResult uploadObject(String bucketName, String absoluteLocation, InputStream stream)
    {
        return s3Client.putObject(bucketName, absoluteLocation, stream, null);
    }

    public String getURL(String absoluteLocation)
    {
        return s3Client.getUrl(this.defaultBucket.getName(), absoluteLocation).toString();
    }

    public void deleteObject(String bucketName, String absoluteLocation)
    {
        s3Client.deleteObject(bucketName, absoluteLocation);
    }

    public void deleteObject(String absoluteLocation)
    {
        s3Client.deleteObject(this.defaultBucket.getName(), absoluteLocation);
    }

    public CopyObjectResult renameObject(String absoluteLocation, String newAbsoluteLocation)
    {
        return renameObject(this.defaultBucket.getName(), absoluteLocation, newAbsoluteLocation);
    }

    public CopyObjectResult renameObject(String bucketName, String absoluteLocation, String newAbsoluteLocation)
    {
        CopyObjectResult copyObjectResult =  s3Client.copyObject(bucketName, absoluteLocation, this.defaultBucket.getName(), newAbsoluteLocation);
    
        if(copyObjectResult.getETag() != null)
            deleteObject(absoluteLocation);

        return copyObjectResult;
    }
}
