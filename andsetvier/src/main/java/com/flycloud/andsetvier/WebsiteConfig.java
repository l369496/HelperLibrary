package com.flycloud.andsetvier;

import android.content.Context;

import com.yanzhenjie.andserver.annotation.Config;
import com.yanzhenjie.andserver.framework.config.Multipart;
import com.yanzhenjie.andserver.framework.config.WebConfig;
import com.yanzhenjie.andserver.framework.website.AssetsWebsite;
import com.yanzhenjie.andserver.framework.website.StorageWebsite;

import java.io.File;

@Config
public class WebsiteConfig implements WebConfig {
    @Override
    public void onConfig(Context context, Delegate delegate) {
        delegate.addWebsite(new StorageWebsite(PathManager.getWebDir(),"/web"));

        delegate.setMultipart(Multipart.newBuilder()
                .allFileMaxSize(1024 * 1024 * 20) // 20M
                .fileMaxSize(1024 * 1024 * 5) // 5M
                .maxInMemorySize(1024 * 10) // 1024 * 10 bytes
                .uploadTempDir(new File(context.getCacheDir(), "_server_upload_cache_"))
                .build());
    }
}
