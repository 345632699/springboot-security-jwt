package com.hotel.common;

import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.http.Response;

public class GetQiniuTokenUtils {
  public String getQiNiuToken(String accessKey, String secretKey, String bucket) {
    Auth auth = Auth.create(accessKey, secretKey);
    String upToken = auth.uploadToken(bucket);
    return upToken;
  }
}
